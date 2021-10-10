package llc.nanocontext.authengine.message.ISO8583.V1987;

import llc.nanocontext.authengine.exceptions.MismatchedMessageTransportIndicatorException;
import llc.nanocontext.authengine.exceptions.InvalidRequestSourceMessageException;
import llc.nanocontext.authengine.message.Message;
import llc.nanocontext.authengine.message.MessageBuilder;
import llc.nanocontext.authengine.message.MessageTypeIndicator;
import llc.nanocontext.authengine.message.PrimaryBitmapFields;
import llc.nanocontext.authengine.parser.FieldParserResult;

import java.time.LocalDate;

public final class ISO8583_V1987_AuthorizationRequestMessageBuilder
        extends MessageBuilder {
    private String pan = null;
    private LocalDate expirationDate = null;
    private Long amountRaw = null;
    private String cardholderName = null;
    private String zipCode = null;

    @Override
    public MessageTypeIndicator getMessageTransportIndicator() {
        return ISO8583_V1987_AuthorizationResponseMessage.MESSAGE_TYPE_INDICATOR;
    }

    @Override
    public ISO8583_V1987_AuthorizationRequestMessageBuilder with(final PrimaryBitmapFields key, final FieldParserResult<?> value) {
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
            default:
                break;
        }

        return this;
    }

    public ISO8583_V1987_AuthorizationRequestMessageBuilder withPan(final String pan) {
        this.pan = pan;
        return this;
    }

    public ISO8583_V1987_AuthorizationRequestMessageBuilder withExpirationDate(final LocalDate expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public ISO8583_V1987_AuthorizationRequestMessageBuilder withAmountRaw(final Long amountRaw) {
        this.amountRaw = amountRaw;
        return this;
    }

    public ISO8583_V1987_AuthorizationRequestMessageBuilder withCardholderName(final String cardholderName) {
        this.cardholderName = cardholderName;
        return this;
    }

    public ISO8583_V1987_AuthorizationRequestMessageBuilder withZipCode(final String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public ISO8583_V1987_AuthorizationRequestMessage build() {
        return new ISO8583_V1987_AuthorizationRequestMessage(
                pan,
                expirationDate,
                amountRaw,
                cardholderName,
                zipCode);
    }
}
