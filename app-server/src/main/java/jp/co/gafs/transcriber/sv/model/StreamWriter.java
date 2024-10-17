package jp.co.gafs.transcriber.sv.model;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Function;

import jp.co.gafs.transcriber.sv.component.BufferedFileWriter;
import jp.co.gafs.transcriber.sv.component.FileWritingMode;
import jp.co.gafs.transcriber.sv.config.Config;
import jp.co.gafs.transcriber.sv.exception.ApplicationServerErrorException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

class StreamWriter<E> {
    private final Path filePath;
    private final Flux<E> flux;
    private FluxSink<E> sink;
    private BufferedFileWriter writer;
    private final Function<E, byte[]> converter;
    private boolean isClosed;

    public StreamWriter(Path filePath, Function<E, byte[]> converter) {
        this.filePath = filePath;
        this.converter = converter;
        this.isClosed = false;
        try {
            writer = new BufferedFileWriter(
                filePath,
                FileWritingMode.CREATE,
                Config.getIoBufferSize()
                );
        } catch (IOException e) {
            throw new ApplicationServerErrorException("IO Exception Detected while accessing a Transcription File", e);
        }

        flux = Flux.<E>create(emitter -> this.sink = emitter)
                .doOnNext(text -> writer.next(this.converter.apply(text)));
        }

    public Path getFilePath() {
        return filePath;
    }

    public Flux<E> getFlux() {
        return flux;
    }

    public void next(E element) {
        sink.next(element);
    }

    public Future<Void> close() {
        return CompletableFuture.runAsync(writer::close)
        .thenRun(() -> isClosed = true);
    }

    public boolean isClosed() {
        return this.isClosed;
    }
}