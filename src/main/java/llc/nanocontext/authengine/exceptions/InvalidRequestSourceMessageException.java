package llc.nanocontext.authengine.exceptions;

import llc.nanocontext.authengine.message.ISO8583.V1987.ISO8583_V1987_BaseMessage;
import llc.nanocontext.authengine.message.Message;

/**
 * An exception that should never happen (logically) which indicates that a ISO8583_V1987_AuthorizationRequestMessageBuilder
 * was passed a source message which it cannot copy/build from.
 */
public class InvalidRequestSourceMessageException extends RuntimeException {
    private static String createMsg(final Class<? extends Message> sourceMessageClass,
                                    final Class<? extends Message> targetMessageClass) {
        return String.format("Unable to build [%s] from source class [%s]",
                targetMessageClass.getName(), sourceMessageClass.getName(),
                targetMessageClass.getName(), sourceMessageClass.getName());
    }

    public InvalidRequestSourceMessageException(
            final Class<? extends Message> sourceMessageClass,
            final Class<? extends Message> targetMessageClass) {
        super(createMsg(sourceMessageClass, targetMessageClass));
    }

    public InvalidRequestSourceMessageException(
            final Class<? extends Message> sourceMessageClass,
            final Class<? extends Message> targetMessageClass,
            final Throwable cause) {
        super(createMsg(sourceMessageClass, targetMessageClass), cause);
    }
}
