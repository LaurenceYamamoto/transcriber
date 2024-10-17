package jp.co.gafs.transcriber.sv.model;

import java.io.File;
import java.nio.file.Paths;

import jp.co.gafs.transcriber.sv.config.Config;

public class AudioStream extends StreamWriter<byte[]> {
    public AudioStream(String sessionId, int requestId, String extension) {
        super(
            Paths.get(Config.getAudioFolderPath() + File.separator + sessionId + "-" + requestId + Config.getAudioFileSuffix() + extension),
            (byte[] array) -> array
        );
    }
}
