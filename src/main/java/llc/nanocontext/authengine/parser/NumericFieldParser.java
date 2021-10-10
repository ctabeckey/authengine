package llc.nanocontext.authengine.parser;

import java.math.BigInteger;

/**
 * Number field with fixed length n. This type can only contain 0-9 characters and
 * must be left padded to n characters with zero(s).
 * For example, NUMERIC(6) field with a value of 10, 000010 will be populated into this field.
 */
public class NumericFieldParser implements FieldParser<Long> {
    private final int sourceLength;

    // a default no-arg constructor is required
    public NumericFieldParser(final int sourceLength) {
        this.sourceLength = sourceLength;
    }

    /**
     *
     * @param raw
     * @return
     */
    public Result parse(final String raw, final int offset) {
        final String fieldRaw = raw.substring(offset, offset + sourceLength);
        return new Result (Long.parseLong(fieldRaw), sourceLength);
    }

    /**
     *
     * @param <Long>
     */
    public static class Result<Long> implements FieldParserResult<Long> {
        private final Long value;
        private final int length;

        private Result(final Long value, final int length) {
            this.value = value;
            this.length = length;
        }

        public Long getValue() {
            return value;
        }

        public int getLength() {
            return length;
        }
    }
}
