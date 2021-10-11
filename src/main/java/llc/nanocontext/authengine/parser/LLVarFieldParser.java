package llc.nanocontext.authengine.parser;

import llc.nanocontext.authengine.exceptions.LLVarFieldParseException;

/**
 * ALPHA field with variable length.
 * This field has two separates section, the first two characters are NUMERIC(2) indicating
 * length of actual following ALPHA value.
 *
 * For example:
 * LLVAR has value 04CITY. Where "CITY" is the value and "04" is its length, 4.
 */
public class LLVarFieldParser implements FieldParser<String> {

    @Override
    public FieldParserResult<String> parse(final String raw, final int offset)
    throws LLVarFieldParseException {
        int length = 0;
        try {
            final String lengthAsString = raw.substring(offset, offset + 2);
            length = Integer.valueOf(lengthAsString);
        } catch(NumberFormatException nfX) {
            throw new LLVarFieldParseException(raw.substring(offset));
        }
        String value = raw.substring(offset + 2, offset + 2 + length);

        // the length of the data plus 2 characters for the length
        return new Result(value, length+2);
    }

    public static class Result implements FieldParserResult<String> {
        private final String value;
        private final int length;

        private Result(final String value, final int length) {
            this.value = value;
            this.length = length;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getLength() {
            return length;
        }
    }

    public String format(final String value) {
        return String.format("%02d%s", value.length(), value);
    }
}
