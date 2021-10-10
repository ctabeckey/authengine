package llc.nanocontext.authengine.exceptions;

public class MessageParseException extends RuntimeException {
    protected static String createMessage(final String value) {
        return String.format("[%s...] is not valid message content",
                value == null ? "<null>"
                        : value.isEmpty() ? "<empty>"
                        : value.substring(0, Math.min(3, value.length()))
        );
    }

    public MessageParseException(final String rawMessage) {
        super(rawMessage);
    }

    public MessageParseException(final String rawMessage, Throwable cause) {
        super(rawMessage, cause);
    }
}
