package llc.nanocontext.authengine.exceptions;

import llc.nanocontext.authengine.message.MessageTypeIndicator;

public class UnknownMessageTypeIdentifierException
extends MessageParseException {
    protected static String createMessage(final String mti) {
        return String.format("No builder is available for MTI [%s]", mti);
    }

    public UnknownMessageTypeIdentifierException(final MessageTypeIndicator mti) {
        super(createMessage(mti.toString()));
    }

    public UnknownMessageTypeIdentifierException(final MessageTypeIndicator mti, Throwable cause) {
        super(createMessage(mti.toString()), cause);
    }

    public UnknownMessageTypeIdentifierException(final String mti) {
        super(createMessage(mti));
    }

    public UnknownMessageTypeIdentifierException(final String mti, Throwable cause) {
        super(createMessage(mti), cause);
    }
}
