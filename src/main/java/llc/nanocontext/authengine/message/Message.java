package llc.nanocontext.authengine.message;

public abstract class Message {
    /**
     * Message Type indicator
     */
    private final MessageTypeIndicator messageTypeIndicator;

    public Message(MessageTypeIndicator messageTypeIndicator) {
        this.messageTypeIndicator = messageTypeIndicator;
    }

    public MessageTypeIndicator getMessageTypeIndicator() {
        return messageTypeIndicator;
    }
}
