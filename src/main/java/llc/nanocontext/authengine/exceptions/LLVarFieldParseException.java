package llc.nanocontext.authengine.exceptions;

public class LLVarFieldParseException extends RuntimeException {
    private static String createMessage(final String value) {
        return String.format("[%s...] is not valid field content for an LLVAR field",
                value.substring(0, Math.min(4, value.length()))
        );
    }

    public LLVarFieldParseException(String value) {
        super(createMessage(value));
    }

    public LLVarFieldParseException(String value, Throwable cause) {
        super(createMessage(value), cause);
    }
}
