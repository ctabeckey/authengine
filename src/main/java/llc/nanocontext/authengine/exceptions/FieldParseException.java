package llc.nanocontext.authengine.exceptions;

import llc.nanocontext.authengine.message.PrimaryBitmapFields;

public class FieldParseException extends RuntimeException {
    private static String createMessage(final String value) {
        return String.format("[%s] is not valid field content", value);
    }

    public FieldParseException(String fieldName) {
        super(createMessage(fieldName));
    }

    public FieldParseException(String fieldName, String value, Throwable cause) {
        super(createMessage(fieldName), cause);
    }
}
