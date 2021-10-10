package llc.nanocontext.authengine.message.ISO8583.V1987;

import llc.nanocontext.authengine.message.MessageTypeIndicator;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Objects;

public abstract class ISO8583_V1987_BaseAuthorizationMessage
extends ISO8583_V1987_BaseMessage {
    @NotNull
    @Pattern(regexp = "[0-9]{13,19}")
    private final String pan;

    @NotNull
    private final LocalDate expirationDate;

    @NotNull
    @Min(0L)
    private final Long amountRaw;

    // carholder name is all caps, spaces allowed
    @Pattern(regexp = "[A-Z\\x20]{1,256}")
    private final String cardholderName;

    @Pattern(regexp = "[0-9]{5}")
    private final String zipCode;

    protected ISO8583_V1987_BaseAuthorizationMessage(
            final MessageTypeIndicator mti,
            final String pan,
            final LocalDate expirationDate,
            final Long amountRaw,
            final String cardholderName,
            final String zipCode) {
        super(mti);
        this.pan = pan;
        this.expirationDate = expirationDate;
        this.amountRaw = amountRaw;
        this.cardholderName = cardholderName;
        this.zipCode = zipCode;
    }

    public String getPan() {
        return pan;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public Long getAmountRaw() {
        return amountRaw;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ISO8583_V1987_BaseAuthorizationMessage that = (ISO8583_V1987_BaseAuthorizationMessage) o;
        return Objects.equals(pan, that.pan) && Objects.equals(expirationDate, that.expirationDate) && Objects.equals(amountRaw, that.amountRaw) && Objects.equals(cardholderName, that.cardholderName) && Objects.equals(zipCode, that.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pan, expirationDate, amountRaw, cardholderName, zipCode);
    }
}
