package llc.nanocontext.authengine.message;

public enum SecondaryBitmapFields {
    AdditionalData(0x0080);

    private final int mask;

    SecondaryBitmapFields(final int mask) {
        this.mask = mask;
    }

    public boolean isPresent(final int bitmap) {
        return (bitmap & mask) > 0;
    }

}
