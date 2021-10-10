package llc.nanocontext.authengine.message.ISO8583.V1987;

import llc.nanocontext.authengine.message.MessageBuilder;
import llc.nanocontext.authengine.message.MessageTypeIndicator;
import llc.nanocontext.authengine.message.PrimaryBitmapFields;
import llc.nanocontext.authengine.parser.FieldParserResult;

import java.time.LocalDate;

public final class ISO8583_V1987_AuthorizationResponseMessageBuilder
        extends MessageBuilder {
    private String pan = null;
    private LocalDate expirationDate = null;
    private Long amountRaw = null;
    private String cardholderName = null;
    private String zipCode = null;
    private String responseCode;

    @Override
    public MessageTypeIndicator getMessageTransportIndicator() {
        return ISO8583_V1987_AuthorizationResponseMessage.MESSAGE_TYPE_INDICATOR;
    }

    @Override
    public ISO8583_V1987_AuthorizationResponseMessageBuilder with(PrimaryBitmapFields key, FieldParserResult<?> value) {
        Object v = value.getValue();

        switch(key) {
            case PAN:
                withPan((String)v);
                break;
            case ExpirationDate:
                withExpirationDate((LocalDate)v);
                break;
            case TransactionAmount:
                withAmountRaw((Long)v);
                break;
            case CardholderName:
                withCardholderName((String)v);
                break;
            case ZipCode:
                withZipCode((String)v);
                break;
            case ResponseCode:
                withResponseCode((String)v);
            default:
                break;
        }

        return this;
    }

    public ISO8583_V1987_AuthorizationResponseMessageBuilder withPan(final String pan) {
        this.pan = pan;
        return this;
    }

    public ISO8583_V1987_AuthorizationResponseMessageBuilder withExpirationDate(final LocalDate expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public ISO8583_V1987_AuthorizationResponseMessageBuilder withAmountRaw(final Long amountRaw) {
        this.amountRaw = amountRaw;
        return this;
    }

    public ISO8583_V1987_AuthorizationResponseMessageBuilder withCardholderName(final String cardholderName) {
        this.cardholderName = cardholderName;
        return this;
    }

    public ISO8583_V1987_AuthorizationResponseMessageBuilder withZipCode(final String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public ISO8583_V1987_AuthorizationResponseMessageBuilder withResponseCode(final String responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    public ISO8583_V1987_AuthorizationResponseMessage build() {
        return new ISO8583_V1987_AuthorizationResponseMessage(
                pan,
                expirationDate,
                amountRaw,
                cardholderName,
                zipCode,
                responseCode);
    }
}
