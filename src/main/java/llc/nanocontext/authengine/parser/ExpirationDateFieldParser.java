package llc.nanocontext.authengine.parser;

import java.time.LocalDate;

public class ExpirationDateFieldParser implements FieldParser<LocalDate> {
    public ExpirationDateFieldParser() {
    }

    @Override
    public FieldParserResult<LocalDate> parse(final String raw, final int offset) {
        final String rawMonth = raw.substring(offset, offset + 2);
        final String rawYear = raw.substring(offset + 2, offset + 4);

        // to get the last day of the month, get the first day opf the following month and subtract 1
        // this avoids having to account for month lengths ourselves
        LocalDate endOfMonth = LocalDate.of(Integer.valueOf("20" + rawYear), Integer.valueOf(rawMonth), 1)
                .plusMonths(1L)
                .minusDays(1L);

        return new Result(endOfMonth, 4);
    }

    public static class Result implements FieldParserResult<LocalDate> {
        private final LocalDate value;
        private final int length;

        private Result(LocalDate value, int length) {
            this.value = value;
            this.length = length;
        }

        @Override
        public LocalDate getValue() {
            return value;
        }

        @Override
        public int getLength() {
            return length;
        }
    }
}
