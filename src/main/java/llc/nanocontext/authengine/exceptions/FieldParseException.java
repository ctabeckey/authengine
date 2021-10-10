package llc.nanocontext.authengine.exceptions;

import llc.nanocontext.authengine.message.PrimaryBitmapFields;

public class FieldParseException extends MessageParseException {
    protected static String createMessage(final String value) {
        return String.format("[%s...] is not valid field content",
                value == null ? "<null>"
                : value.isEmpty() ? "<empty>"
                : value.substring(0, Math.min(3, value.length()))
        );
    }

    public FieldParseException(String value) {
        super(createMessage(value));
    }

    public FieldParseException(String value, Throwable cause) {
        super(createMessage(value), cause);
    }
}
