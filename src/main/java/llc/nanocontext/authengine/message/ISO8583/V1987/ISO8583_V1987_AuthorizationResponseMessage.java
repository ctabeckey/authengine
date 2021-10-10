package llc.nanocontext.authengine.message.ISO8583.V1987;

import llc.nanocontext.authengine.message.AuthorizationResponse;
import llc.nanocontext.authengine.message.MessageTypeIndicator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

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

}
