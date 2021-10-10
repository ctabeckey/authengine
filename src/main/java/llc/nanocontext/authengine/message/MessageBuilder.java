package llc.nanocontext.authengine.message;

import llc.nanocontext.authengine.exceptions.MismatchedMessageTransportIndicatorException;
import llc.nanocontext.authengine.exceptions.InvalidRequestSourceMessageException;
import llc.nanocontext.authengine.parser.FieldParserResult;

/**
 *
 */
public abstract class MessageBuilder {
    // derived classes MUST have a no-arg constructor
    public MessageBuilder() {}

    /**
     *
     * @return
     */
    public abstract MessageTypeIndicator getMessageTransportIndicator();

    public abstract MessageBuilder with(final PrimaryBitmapFields key, final FieldParserResult<?> value);

    public abstract Message build();

}
