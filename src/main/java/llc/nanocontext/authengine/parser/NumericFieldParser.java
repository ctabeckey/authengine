package llc.nanocontext.authengine.parser;

import llc.nanocontext.authengine.exceptions.NumericFieldParseException;

import java.math.BigInteger;

/**
 * Number field with fixed length n. This type can only contain 0-9 characters and
 * must be left padded to n characters with zero(s).
 * For example, NUMERIC(6) field with a value of 10, 000010 will be populated into this field.
 */
public class NumericFieldParser implements FieldParser<Long> {
    private final int rawLength;

    // a default no-arg constructor is required
    public NumericFieldParser(final int rawLength) {
        this.rawLength = rawLength;
    }

    /**
     *
     * @param raw
     * @return
     */
    public Result parse(final String raw, final int offset)
    throws NumericFieldParseException {
        final String fieldRaw = raw.substring(offset, offset + rawLength);
        try {
            return new Result(Long.parseLong(fieldRaw), rawLength);
        } catch (NumberFormatException nfX) {
            throw new NumericFieldParseException(raw);
        }
    }

    @Override
    public String format(final Long value) {
        if (value == null)
            throw new IllegalArgumentException("value must not be null");

        // if the rawLength is zero then format without left filling
        final String fieldFormat = rawLength == 0
                ? "%d"
                : "%0" + rawLength + "d";
        return String.format(fieldFormat, value);
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
