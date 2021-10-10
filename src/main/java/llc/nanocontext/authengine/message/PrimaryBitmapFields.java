package llc.nanocontext.authengine.message;

import llc.nanocontext.authengine.parser.*;

/**
 * The list of fields whose presence is indicated in the primary bitmap field.
 * These MUST be in the order that they are expected in any message.
 */
public enum PrimaryBitmapFields {
    PAN(0x0001, new LLVarFieldParser()),
    ExpirationDate(0x0002, new ExpirationDateFieldParser()),
    TransactionAmount(0x0004, new NumericFieldParser(10)),
    ResponseCode(0x0008, new AlphaFieldParser(2)),
    CardholderName(0x0010, new LLVarFieldParser()),
    ZipCode(0x0020, new ZipCodeFieldParser()),
    Unused(0x0040, new NullFieldParser()),
    AdditionalData(0x0080, new NullFieldParser());

    private final int mask;
    private final FieldParser<?> fieldParser;

    PrimaryBitmapFields(final int mask, final FieldParser<?> fieldParser) {
        this.mask = mask;
        this.fieldParser = fieldParser;
    }

    public boolean isPresent(final int bitmap) {
        return (bitmap & mask) > 0;
    }

    public FieldParser<?> getFieldParser() {
        return fieldParser;
    }

    /**
     * Builds a map from an existing map plus a field to add
     *
     * @param map
     * @param field
     * @return
     */
    public static int sum(final int map, PrimaryBitmapFields field) {
        return map | field.mask;
    }

    public static String format(final int map) {
        return String.format("%02x", (map & 0xFF));
    }
}
