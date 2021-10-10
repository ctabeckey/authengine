package llc.nanocontext.authengine.message;

import java.time.LocalDate;

public interface AuthorizationResponse {
    String RESPONSE_OK = "OK";
    String RESPONSE_DECLINE = "DE";
    String RESPONSE_ERROR = "ER";

    String getPan();

    LocalDate getExpirationDate();

    Long getAmountRaw();

    String getCardholderName();

    String getZipCode();

    String getResponseCode();

}
