package jp.co.gafs.transcriber.sv.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Paths;
import java.io.File;
import java.nio.file.StandardOpenOption;
import java.nio.channels.AsynchronousFileChannel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import jp.co.gafs.config.Config;

public class TranscriptionStream {
    private final Flux<String> flux;
    private FluxSink<String> sink;
    private final AsynchronousFileChannel fileChannel;
    
    @Autowired
    private final Config config;


    public TranscriptionStream(String sessionId, int requestId) {
        try {
            fileChannel = AsynchronousFileChannel.open(
                Paths.get(
                    config.getTranscriptionFolderPath() + File.separator + sessionId + "-" + requestId + config.getTranscriptionFileSuffix() + ".txt"),
                    StandardOpenOption.READ
                    );
        } catch (IOException e) {
            e.printStackTrace(); // TODO: Implement exception handler here
        }

        flux = Flux.<String>create(emitter -> this.sink = emitter)
            .doOnNext(text -> {});
    }

    public Flux<String> getFlux() {
        return flux;
    }

    public void write(String text) {
}
