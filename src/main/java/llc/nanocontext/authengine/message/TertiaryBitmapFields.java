package llc.nanocontext.authengine.message;

import llc.nanocontext.authengine.parser.NullFieldParser;

public enum TertiaryBitmapFields {
    AdditionalData(0b00000001);

    private final int mask;

    TertiaryBitmapFields(final int mask) {
        this.mask = mask;
    }

    public boolean isPresent(final int bitmap) {
        return (bitmap & mask) > 0;
    }

    /**
     * Builds a map from an existing map plus a field to add
     *
     * @param map
     * @param field
     * @return
     */
    public static int sum(final int map, TertiaryBitmapFields field) {
        return map | field.mask;
    }

}
