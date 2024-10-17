package jp.co.gafs.transcriber.sv.exception;

public class ApplicationServerErrorException extends ApplicationException {
    public ApplicationServerErrorException(String message) {
        super(message);
    }

    public ApplicationServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationServerErrorException(String message, Throwable cause, int httpStatusCode) {
        super(message, cause, httpStatusCode);
    }

    public ApplicationServerErrorException(String message, Throwable cause, int httpStatusCode, String internalMessage) {
        super(message, cause, httpStatusCode, internalMessage);
    }
}
