package llc.nanocontext.authengine.exceptions;

public class MessageTypeIndicatorParseException extends MessageParseException {

    public MessageTypeIndicatorParseException(final String message) {
        super(message);
    }

    public MessageTypeIndicatorParseException(final String message, Throwable cause) {
        super(message, cause);
    }
}
