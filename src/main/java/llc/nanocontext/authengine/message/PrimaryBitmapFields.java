package llc.nanocontext.authengine.message;

import llc.nanocontext.authengine.parser.*;

/**
 * The list of fields whose presence is indicated in the primary bitmap field.
 * These MUST be in the order that they are expected in any message.
 */
public enum PrimaryBitmapFields {
    PAN(0b10000000, new LLVarFieldParser()),
    ExpirationDate(0b01000000, new ExpirationDateFieldParser()),
    TransactionAmount(0b00100000, new NumericFieldParser(10)),
    ResponseCode(0b00010000, new AlphaFieldParser(2)),
    CardholderName(0b00001000, new LLVarFieldParser()),
    ZipCode(0b00000100, new ZipCodeFieldParser()),
    Unused(0b00000010, new NullFieldParser()),
    AdditionalData(0b00000001, new NullFieldParser());

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
