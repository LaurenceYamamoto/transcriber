package jp.co.gafs.transcriber.sv.model;

import java.io.File;
import java.nio.file.Paths;

import jp.co.gafs.transcriber.sv.config.Config;

public class TranscriptionStream extends StreamWriter<String> {
    public TranscriptionStream(String sessionId, int requestId){
        super(
            Paths.get(Config.getTranscriptionFolderPath() + File.separator + sessionId + "-" + requestId + Config.getTranscriptionFileSuffix() + ".txt"),
            (String text) -> text.getBytes()
        );
    }
}
