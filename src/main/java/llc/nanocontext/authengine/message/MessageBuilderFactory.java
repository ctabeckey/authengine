package llc.nanocontext.authengine.message;

import llc.nanocontext.authengine.message.ISO8583.V1987.ISO8583_V1987_AuthorizationRequestMessage;
import llc.nanocontext.authengine.message.ISO8583.V1987.ISO8583_V1987_AuthorizationRequestMessageBuilder;
import llc.nanocontext.authengine.message.ISO8583.V1987.ISO8583_V1987_AuthorizationResponseMessage;
import llc.nanocontext.authengine.message.ISO8583.V1987.ISO8583_V1987_AuthorizationResponseMessageBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 */
public class MessageBuilderFactory {
    private static final Map<MessageTypeIndicator, Class<? extends MessageBuilder>> builderMap;

    static {
        Map<MessageTypeIndicator, Class<? extends MessageBuilder>> temp = new HashMap<>();

        temp.put(ISO8583_V1987_AuthorizationRequestMessage.MESSAGE_TYPE_INDICATOR, ISO8583_V1987_AuthorizationRequestMessageBuilder.class);
        temp.put(ISO8583_V1987_AuthorizationResponseMessage.MESSAGE_TYPE_INDICATOR, ISO8583_V1987_AuthorizationResponseMessageBuilder.class);

        builderMap = Collections.unmodifiableMap(temp);
    }

    /**
     *
     * @param mti
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static MessageBuilder getMessageBuilder(final MessageTypeIndicator mti)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<? extends MessageBuilder> builderClass = builderMap.get(mti);
        return builderClass.getConstructor().newInstance();
    }
}
