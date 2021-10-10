package llc.nanocontext.authengine.message;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public interface AuthorizationRequest {
    @NotNull
    String getPan();

    @NotNull
    LocalDate getExpirationDate();

    @NotNull
    Long getAmountRaw();

    String getCardholderName();

    String getZipCode();

    AuthorizationResponse createResponseMessage(final String responseCode);
}
