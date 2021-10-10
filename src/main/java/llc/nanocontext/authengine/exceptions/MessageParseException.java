package llc.nanocontext.authengine.exceptions;

public class MessageParseException extends RuntimeException {
    private static String createMessage(final String rawMessage) {
        return String.format("Unable to parse message [%s]", rawMessage);
    }

    public MessageParseException(final String rawMessage) {
        super(rawMessage);
    }

    public MessageParseException(final String rawMessage, Throwable cause) {
        super(rawMessage, cause);
    }
}
