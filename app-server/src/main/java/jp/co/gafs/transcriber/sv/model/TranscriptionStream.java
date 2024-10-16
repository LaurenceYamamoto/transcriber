package jp.co.gafs.transcriber.sv.model;

import java.io.File;
import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.gafs.transcriber.sv.config.Config;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public class TranscriptionStream {
    private final Flux<String> flux;
    private FluxSink<String> sink;
    private AsynchronousFileChannel fileChannel;
    
    @Autowired
    private Config config;

    public TranscriptionStream(String sessionId, int requestId) {
        try {
            fileChannel = AsynchronousFileChannel.open(
                Paths.get(
                    Config.getTranscriptionFolderPath() + File.separator + sessionId + "-" + requestId + config.getTranscriptionFileSuffix() + ".txt"),
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
}
