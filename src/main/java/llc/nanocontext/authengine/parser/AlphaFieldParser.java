package llc.nanocontext.authengine.parser;

public class AlphaFieldParser implements FieldParser<String> {
    private final int rawLength;

    public AlphaFieldParser(final int rawLength) {
        this.rawLength = rawLength;
    }

    @Override
    public FieldParserResult<String> parse(final String raw, final int offset) {
        return new Result(raw.substring(offset, offset + rawLength), rawLength);
    }

    public static class Result implements FieldParserResult<String> {
        private final String value;
        private final int length;

        private Result(String value, int length) {
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
}
