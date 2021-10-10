package llc.nanocontext.authengine.parser;

import llc.nanocontext.authengine.exceptions.FieldParseException;

/**
 * All implementations MUST be thread safe
 *
 * @param <T> - the resulting type after parsing
 */
public interface FieldParser<T> {
    FieldParserResult<T> parse(final String raw, final int offset) throws FieldParseException;
}
