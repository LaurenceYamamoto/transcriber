package jp.co.gafs.transcriber.sv.component;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Deque;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class BufferedFileWriter {
    private final AsynchronousFileChannel channel;
    private final Deque<ByteBuffer> buffers = new ConcurrentLinkedDeque<>();
    private long pos;
    private final int bufferSize;
    private final int threshold;
    private boolean isClosed;

    public BufferedFileWriter(Path path, FileWritingMode mode, int bufferSize) throws IOException {
        StandardOpenOption[] options;
        switch (mode) {
            case CREATE -> options = new StandardOpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.WRITE};
            case WRITE -> options = new StandardOpenOption[]{StandardOpenOption.WRITE};
            default -> throw new IllegalArgumentException("Invalid FileWritingMode: " + mode);
        }

        channel = AsynchronousFileChannel.open(path, options);
        buffers.add(ByteBuffer.allocate(bufferSize));
        this.pos = 0;
        this.bufferSize = bufferSize;
        this.threshold = bufferSize / 2;
        this.isClosed = false;
    }

    public void next(byte... array) {
        int p = 0;
        int remaining = array.length;
        do {
            final int bufferRemaining = buffers.getLast().remaining();
            if (bufferRemaining >= remaining) {
                buffers.getLast().put(array, p, remaining);
            } else {
                buffers.getLast().put(array, p, bufferRemaining);
                p += bufferRemaining;
                remaining -= bufferRemaining;
                flushForce();
            }
        } while (remaining > 0);
    }

    public void flush() {
        final int p = buffers.getFirst().position();
        if (p > threshold) {
            channel.write(buffers.peekFirst(), pos);
            pos += p;
            buffers.addLast(ByteBuffer.allocate(bufferSize));
        }
    }

    public void flushForce() {
        final int p = buffers.getFirst().position();
        channel.write(buffers.peekFirst(), pos);
        pos += p;
        buffers.addLast(ByteBuffer.allocate(bufferSize));
    }

    public Future<Void> close() {
        isClosed = true;
        
        return CompletableFuture.<Integer>supplyAsync(
            () -> {
                try {
                   return channel.write(buffers.peekFirst(), pos).get(); 
                } catch (InterruptedException|ExecutionException e) {
                    // Ignore Interupted Exception
                }
            })
        .thenRun(
            () -> {
                try {
                    channel.close();
                } catch (IOException e) {
                    // Ignore IOException
                }
                isClosed = true;
            })
        .ignoreElement();

        

    }

    public boolean isClosed() {
        return isClosed;
    }

}
