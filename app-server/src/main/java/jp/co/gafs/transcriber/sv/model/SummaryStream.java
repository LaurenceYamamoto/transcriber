package jp.co.gafs.transcriber.sv.model;

import java.io.File;
import java.nio.file.Paths;

import jp.co.gafs.transcriber.sv.config.Config;

public class SummaryStream extends StreamWriter<String> {
    public SummaryStream(String sessionId, int requestId){
        super(
            Paths.get(Config.getSummaryFolderPath() + File.separator + sessionId + "-" + requestId + Config.getSummaryFileSuffix() + ".txt"),
            (String text) -> text.getBytes()
        );
    }
}