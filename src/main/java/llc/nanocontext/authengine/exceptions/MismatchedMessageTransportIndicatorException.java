package llc.nanocontext.authengine.exceptions;

import llc.nanocontext.authengine.message.ISO8583.V1987.ISO8583_V1987_BaseMessage;
import llc.nanocontext.authengine.message.MessageBuilder;

/**
 * An exception that should never happen (logically) which indicates that a ISO8583_V1987_AuthorizationRequestMessageBuilder
 * was passed a raw String starting withn an MTI that it cannot build an instance for.
 */
public class MismatchedMessageTransportIndicatorException extends RuntimeException {
    private static String createMsg(final String mti, final Class<? extends MessageBuilder> builderClass) {
        return String.format("ISO8583_V1987_AuthorizationRequestMessageBuilder class [%s] is unable to build a message with MTI [%s]",
                builderClass == null ? "unknown" : builderClass.getName(),
                mti);
    }

    public MismatchedMessageTransportIndicatorException(final String mti, final Class<? extends MessageBuilder> builderClass) {
        super(createMsg(mti, builderClass));
    }

    public MismatchedMessageTransportIndicatorException(final String mti, final Class<? extends MessageBuilder> builderClass, Throwable cause) {
        super(createMsg(mti, builderClass), cause);
    }
}
