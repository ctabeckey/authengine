package llc.nanocontext.authengine.exceptions;

public class NumericFieldParseException extends FieldParseException {
    protected static String createMessage(final String value) {
        return String.format("[%s...] is not valid field content for a numeric field",
                value.substring(0, Math.min(4, value.length()))
        );
    }

    public NumericFieldParseException(String value) {
        super(createMessage(value));
    }

    public NumericFieldParseException(String value, Throwable cause) {
        super(createMessage(value), cause);
    }
}
