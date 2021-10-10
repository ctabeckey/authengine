package llc.nanocontext.authengine.parser;

/**
 * Fields parsers return the parsed value and the number of characters that the parsing has consumed.
 * @param <T>
 */
public interface FieldParserResult<T> {
    T getValue();
    int getLength();
}
