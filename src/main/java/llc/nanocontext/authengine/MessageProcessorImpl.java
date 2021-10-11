package llc.nanocontext.authengine;

import llc.nanocontext.authengine.message.AuthorizationRequest;
import llc.nanocontext.authengine.message.AuthorizationResponse;
import llc.nanocontext.authengine.message.Message;
import llc.nanocontext.authengine.parser.BaseMessageParser;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/**
 * A simple, single threaded and synchronous implementation of a message processor.
 * This class, and all implementations of MessageProcessor interface, defines its
 * interface on raw String level. External to this class, the formatting and semantics
 * of messages are unknown.
 */
public class MessageProcessorImpl implements MessageProcessor{
    private final BaseMessageParser parser;
    private final AuthorizationStrategy authorizationStrategy;
    private final Validator validator;

    public MessageProcessorImpl(
            final BaseMessageParser parser,
            final AuthorizationStrategy authorizationStrategy,
            final Validator validator) {
        this.parser = parser;
        this.authorizationStrategy = authorizationStrategy;
        this.validator = validator;
    }

    /**
     * Process a single message as a String and returning a response, also as a String..
     *
     * @param message
     * @return
     */
    @Override
    public String process(final String message) {
        String result = null;
        Message msg = parser.parse(message);

        if (msg instanceof AuthorizationRequest) {
            AuthorizationResponse response = null;

            final AuthorizationRequest authRequest = (AuthorizationRequest) msg;
            Set<ConstraintViolation<AuthorizationRequest>> constraintViolations = validator.validate(authRequest);
            if (constraintViolations == null || constraintViolations.isEmpty()) {
                response = authorizationStrategy.authorize(authRequest);
            } else {
                response = authRequest.createResponseMessage(AuthorizationResponse.RESPONSE_ERROR);
            }
            result = response.toString();
        }
        return result;
    }
}
