package llc.nanocontext.authengine.exceptions;

public class UnknownFieldException extends RuntimeException {
    public UnknownFieldException(String message) {
        super(message);
    }

    public UnknownFieldException(String message, Throwable cause) {
        super(message, cause);
    }
}
