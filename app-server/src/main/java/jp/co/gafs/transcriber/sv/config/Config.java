package jp.co.gafs.transcriber.sv.config;
import java.io.File;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    static String baseFolderPath;
    static String baseDataFolderPath;
    static String logFolderPath;
    static String audioFolderPath;
    static String transcriptionFolderPath;
    static String summaryFolderPath;
    static String audioFileSuffix;
    static String transcriptionFileSuffix;
    static String summaryFileSuffix;

    public void init() {
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
        
    public static String getBaseFolderPath() {
        return baseFolderPath;
    }

    public static String getBaseDataFolderPath() {
        return baseDataFolderPath;
    }

    public static String getLogFolderPath() {
        return logFolderPath;
    }

    public static String getAudioFolderPath() {
        return audioFolderPath;
    }

    public static String getTranscriptionFolderPath() {
        return transcriptionFolderPath;
    }

    public static String getSummaryFolderPath() {
        return summaryFolderPath;
    }

    public static String getAudioFileSuffix() {
        return audioFileSuffix;
    }

    public static String getTranscriptionFileSuffix() {
        return transcriptionFileSuffix;
    }

    public static String getSummaryFileSuffix() {
        return summaryFileSuffix;
    }
}
