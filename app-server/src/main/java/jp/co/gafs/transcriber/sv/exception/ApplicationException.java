package jp.co.gafs.transcriber.sv.exception;

public class ApplicationException extends RuntimeException {

    private int httpStatusCode;
    private String internalMessage;

    public ApplicationException(String message) {
        this(message, null, 500, message);
    }

    public ApplicationException(String message, Throwable cause) {
        this(message, cause, 500, message);
    }

    public ApplicationException(String message, Throwable cause, int httpStatusCode) {
        this(message, cause, httpStatusCode, message);
    }

    public ApplicationException(String message, Throwable cause, int httpStatusCode, String internalMessage) {
        super(message, cause);
        this.httpStatusCode = httpStatusCode;
        this.internalMessage = internalMessage;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getInternalMessage() {
        return internalMessage;
    }
}
