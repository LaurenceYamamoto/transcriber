package jp.co.gafs.config;
import java.io.File;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    private final String baseFolderPath;
    private final String baseDataFolderPath;
    private final String logFolderPath;
    private final String audioFolderPath;
    private final String transcriptionFolderPath;
    private final String summaryFolderPath;
    private final String audioFileSuffix;
    private final String transcriptionFileSuffix;
    private final String summaryFileSuffix;

    public Config() {
        final char separator = File.pathSeparatorChar;
        baseFolderPath = System.getenv().getOrDefault("BASE_FOLDER_PATH", "transcriber");
        baseDataFolderPath = baseFolderPath + separator + System.getenv().getOrDefault("DATA_FOLDER_NAME", "data");
        logFolderPath = baseFolderPath + separator + System.getenv().getOrDefault("LOG_FOLDER_NAME", "log");
        audioFolderPath = baseDataFolderPath + separator + System.getenv().getOrDefault("AUDIO_FOLDER_NAME", "audio");
        transcriptionFolderPath = baseDataFolderPath + separator + System.getenv().getOrDefault("TRANSCRIPTION_FOLDER_NAME", "transcription");
        summaryFolderPath = baseDataFolderPath + separator + System.getenv().getOrDefault("SUMMARY_FOLDER_NAME", "summary");
        audioFileSuffix = System.getenv().getOrDefault("AUDIO_FILE_SUFFIX", "_audio");
        transcriptionFileSuffix = System.getenv().getOrDefault("TRANSCRIPTION_FILE_SUFFIX", "_transcription");
        summaryFileSuffix = System.getenv().getOrDefault("SUMMARY_FILE_SUFFIX", "_summary");
    }
        
    public String getBaseFolderPath() {
        return baseFolderPath;
    }

    public String getBaseDataFolderPath() {
        return baseDataFolderPath;
    }

    public String getLogFolderPath() {
        return logFolderPath;
    }

    public String getAudioFolderPath() {
        return audioFolderPath;
    }

    public String getTranscriptionFolderPath() {
        return transcriptionFolderPath;
    }

    public String getSummaryFolderPath() {
        return summaryFolderPath;
    }

    public String getAudioFileSuffix() {
        return audioFileSuffix;
    }

    public String getTranscriptionFileSuffix() {
        return transcriptionFileSuffix;
    }

    public String getSummaryFileSuffix() {
        return summaryFileSuffix;
    }
}
