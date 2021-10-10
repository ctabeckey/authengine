package llc.nanocontext.authengine.message;

public abstract class Message {
    /**
     * Message Type indicator
     */
    private final MessageTypeIndicator messageTypeIndicator;

    public Message(final MessageTypeIndicator messageTypeIndicator) {
        if (messageTypeIndicator == null)
            throw new IllegalArgumentException("MessageTypeIndicator may not be null");
        this.messageTypeIndicator = messageTypeIndicator;
    }

    public MessageTypeIndicator getMessageTypeIndicator() {
        return messageTypeIndicator;
    }

    public String format() {
        return messageTypeIndicator.format();
    }
}
