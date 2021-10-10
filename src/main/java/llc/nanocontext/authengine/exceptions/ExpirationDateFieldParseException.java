package llc.nanocontext.authengine.exceptions;

public class ExpirationDateFieldParseException extends FieldParseException {
    protected static String createMessage(final String value) {
        return String.format("[%s...] is not valid field content for an expiration date field",
                value.substring(0, Math.min(4, value.length()))
        );
    }

    public ExpirationDateFieldParseException(String value) {
        super(createMessage(value));
    }

    public ExpirationDateFieldParseException(String value, Throwable cause) {
        super(createMessage(value), cause);
    }
}
