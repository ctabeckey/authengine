package llc.nanocontext.authengine;

import llc.nanocontext.authengine.message.AuthorizationRequest;
import llc.nanocontext.authengine.message.AuthorizationResponse;
import llc.nanocontext.authengine.message.Message;
import llc.nanocontext.authengine.parser.BaseMessageParser;

import java.lang.reflect.InvocationTargetException;

/**
 * A simple, single threaded and synchronous implementation of a message processor.
 * This class, and all implementations of MessageProcessor interface, defines its
 * interface on raw String level. External to this class, the formatting and semantics
 * of messages are unknown.
 */
public class MessageProcessorImpl {
    private final BaseMessageParser parser;
    private final AuthorizationStrategy authorizationStrategy;

    public MessageProcessorImpl(
            final BaseMessageParser parser,
            final AuthorizationStrategy authorizationStrategy) {
        this.parser = parser;
        this.authorizationStrategy = authorizationStrategy;
    }

    /**
     * Process a single message as a String and returning a response, also as a String..
     *
     * @param message
     * @return
     */
    public String process(final String message) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String result = null;
        Message msg = parser.parse(message);
        if (msg instanceof AuthorizationRequest) {
            final AuthorizationRequest authRequest = (AuthorizationRequest)msg;
            AuthorizationResponse response = authorizationStrategy.authorize(authRequest);

            result = response.toString();
        }

        return result;
    }
}
