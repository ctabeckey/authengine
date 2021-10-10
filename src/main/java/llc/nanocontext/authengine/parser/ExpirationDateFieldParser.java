package llc.nanocontext.authengine.parser;

import llc.nanocontext.authengine.exceptions.ExpirationDateFieldParseException;

import javax.swing.text.DateFormatter;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExpirationDateFieldParser implements FieldParser<LocalDate> {
    // shades of Y2K, need to prepend the century to build a date from the raw input
    private final String ERA_PREFIX = "20";
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMyy");

    public ExpirationDateFieldParser() {
    }

    @Override
    public FieldParserResult<LocalDate> parse(final String raw, final int offset)
    throws ExpirationDateFieldParseException {
        final String rawMonth = raw.substring(offset, offset + 2);
        final String rawYear = raw.substring(offset + 2, offset + 4);

        // to get the last day of the month, get the first day of the following month and subtract 1
        // this avoids having to account for month lengths ourselves
        LocalDate endOfMonth;
        try {
            endOfMonth = LocalDate.of(Integer.valueOf("20" + rawYear), Integer.valueOf(rawMonth), 1)
                    .plusMonths(1L)
                    .minusDays(1L);
        } catch (DateTimeException dtX) {
            throw new ExpirationDateFieldParseException(raw);
        }
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

    public String format(final LocalDate date) {
        if (date == null)
            throw new IllegalArgumentException("date must not be null");
        return date.format(dateFormatter);
    }
}
