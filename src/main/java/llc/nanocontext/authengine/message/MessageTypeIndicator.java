package llc.nanocontext.authengine.message;

import llc.nanocontext.authengine.exceptions.MessageTypeIndicatorParseException;

import java.util.Objects;
import java.util.regex.Pattern;

public class MessageTypeIndicator {
    private final byte version;
    private final byte clazz;
    private final byte function;
    private final byte origin;

    private final static Pattern mtiPattern = Pattern.compile("[0-9]{4}");

    /**
     * Private constructor to discourage direct instantiation, use the Builder.
     * @param version
     * @param clazz
     * @param function
     * @param origin
     */
    private MessageTypeIndicator(byte version, byte clazz, byte function, byte origin) {
        this.version = version;
        this.clazz = clazz;
        this.function = function;
        this.origin = origin;
    }

    public int getVersion() {
        return version;
    }

    public int getClazz() {
        return clazz;
    }

    public int getFunction() {
        return function;
    }

    public int getOrigin() {
        return origin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageTypeIndicator that = (MessageTypeIndicator) o;
        return version == that.version &&
                clazz == that.clazz &&
                function == that.function &&
                origin == that.origin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, clazz, function, origin);
    }

    public String toString() {
        return String.format("%1d%1d%1d%1d", version, clazz, function, origin);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private byte version;
        private byte clazz;
        private byte function;
        private byte origin;

        private Builder() {
        }

        public Builder withVersion(byte version) {
            this.version = version;
            return this;
        }

        public Builder withClazz(byte clazz) {
            this.clazz = clazz;
            return this;
        }

        public Builder withFunction(byte function) {
            this.function = function;
            return this;
        }

        public Builder withOrigin(byte origin) {
            this.origin = origin;
            return this;
        }

        /**
         * Create an instance from the first 4 characters of a String
         * @param raw
         */
        public Builder withRaw(final String raw) {
            if (raw == null || raw.length() < 4)
                throw new MessageTypeIndicatorParseException("MessageTypeIndicator must have 4 characters");

            try {
                this.version = Byte.parseByte(raw.substring(0, 1));
                this.clazz = Byte.parseByte(raw.substring(1, 2));
                this.function = Byte.parseByte(raw.substring(2, 3));
                this.origin = Byte.parseByte(raw.substring(3, 4));
            } catch (NumberFormatException nfX) {
                throw new MessageTypeIndicatorParseException("Invalid format in message type indicator", nfX);
            }

            return this;
        }

        public MessageTypeIndicator build() {
            return new MessageTypeIndicator(version, clazz, function, origin);
        }
    }
}
