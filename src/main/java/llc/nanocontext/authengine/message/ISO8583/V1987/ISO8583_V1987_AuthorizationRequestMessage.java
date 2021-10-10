package llc.nanocontext.authengine.message.ISO8583.V1987;

import llc.nanocontext.authengine.message.AuthorizationRequest;
import llc.nanocontext.authengine.message.AuthorizationResponse;
import llc.nanocontext.authengine.message.Message;
import llc.nanocontext.authengine.message.MessageTypeIndicator;

import java.time.LocalDate;

/**
 *
 */
public class ISO8583_V1987_AuthorizationRequestMessage
        extends ISO8583_V1987_BaseAuthorizationMessage
        implements AuthorizationRequest {
    public static MessageTypeIndicator MESSAGE_TYPE_INDICATOR = MessageTypeIndicator.builder().withRaw("0100").build();

    /**
     * Restrict access to package, only the Builder should be calling this constructor
     *
     * @param pan
     * @param expirationDate
     * @param amountRaw
     * @param cardholderName
     * @param zipCode
     */
    ISO8583_V1987_AuthorizationRequestMessage(
            final String pan,
            final LocalDate expirationDate,
            final Long amountRaw,
            final String cardholderName,
            final String zipCode) {
        super(MESSAGE_TYPE_INDICATOR, pan, expirationDate, amountRaw, cardholderName, zipCode);
    }

    @Override
    public AuthorizationResponse createResponseMessage(final String responseCode) {
        return (new ISO8583_V1987_AuthorizationResponseMessageBuilder())
                .withPan(getPan())
                .withAmountRaw(getAmountRaw())
                .withCardholderName(getCardholderName())
                .withExpirationDate(getExpirationDate())
                .withZipCode(getZipCode())
                .withResponseCode(responseCode)
                .build();
    }
}
