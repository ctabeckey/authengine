package llc.nanocontext.authengine.message.ISO8583.V1987;

import llc.nanocontext.authengine.message.AuthorizationResponse;
import llc.nanocontext.authengine.message.MessageTypeIndicator;
import llc.nanocontext.authengine.message.PrimaryBitmapFields;
import llc.nanocontext.authengine.parser.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 */
public class ISO8583_V1987_AuthorizationResponseMessage
        extends ISO8583_V1987_BaseAuthorizationMessage
        implements AuthorizationResponse
{
    public static MessageTypeIndicator MESSAGE_TYPE_INDICATOR = MessageTypeIndicator.builder().withRaw("0110").build();

    @NotNull
    @Pattern(regexp = "OK|DE|ER")
    private final String responseCode;

    /**
     * Restrict access to package, only the Builder should be calling this constructor
     * @param pan
     * @param expirationDate
     * @param amountRaw
     * @param cardholderName
     * @param zipCode
     * @param responseCode
     */
    ISO8583_V1987_AuthorizationResponseMessage(
            final String pan,
            final LocalDate expirationDate,
            final Long amountRaw,
            final String cardholderName,
            final String zipCode,
            final String responseCode) {
        super(MESSAGE_TYPE_INDICATOR, pan, expirationDate, amountRaw, cardholderName, zipCode);
        this.responseCode = responseCode;
    }

    @Override
    public String getResponseCode() {
        return responseCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ISO8583_V1987_AuthorizationResponseMessage that = (ISO8583_V1987_AuthorizationResponseMessage) o;
        return Objects.equals(responseCode, that.responseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), responseCode);
    }

    @Override
    public String toString() {
        return "ISO8583_V1987_AuthorizationResponseMessage{} [" + format() + "]";
    }

    public String format() {
        StringBuilder dataFieldsSB = new StringBuilder();
        int primaryFieldBitmap = 0x0000;

        if (getPan() != null) {
            dataFieldsSB.append((new LLVarFieldParser().format(getPan())));
            primaryFieldBitmap = PrimaryBitmapFields.sum(primaryFieldBitmap, PrimaryBitmapFields.PAN);
        }

        if (getAmountRaw() != null) {
            dataFieldsSB.append((new NumericFieldParser(0).format(getAmountRaw())));
            primaryFieldBitmap = PrimaryBitmapFields.sum(primaryFieldBitmap, PrimaryBitmapFields.TransactionAmount);
        }

        if (getCardholderName() != null) {
            dataFieldsSB.append((new LLVarFieldParser().format(getCardholderName())));
            primaryFieldBitmap = PrimaryBitmapFields.sum(primaryFieldBitmap, PrimaryBitmapFields.CardholderName);
        }

        if (getExpirationDate() != null) {
            dataFieldsSB.append((new ExpirationDateFieldParser()).format(getExpirationDate()));
            primaryFieldBitmap = PrimaryBitmapFields.sum(primaryFieldBitmap, PrimaryBitmapFields.ExpirationDate);
        }

        if (getZipCode() != null) {
            dataFieldsSB.append((new ZipCodeFieldParser()).format(getZipCode()));
            primaryFieldBitmap = PrimaryBitmapFields.sum(primaryFieldBitmap, PrimaryBitmapFields.ZipCode);
        }

        if (getResponseCode() != null) {
            dataFieldsSB.append((new AlphaFieldParser(2)).format(getResponseCode()));
            primaryFieldBitmap = PrimaryBitmapFields.sum(primaryFieldBitmap, PrimaryBitmapFields.ResponseCode);
        }

        return getMessageTypeIndicator().format()
                + PrimaryBitmapFields.format(primaryFieldBitmap)
                + dataFieldsSB;
    }
}
