package llc.nanocontext.authengine;

import llc.nanocontext.authengine.message.AuthorizationRequest;
import llc.nanocontext.authengine.message.AuthorizationResponse;

import java.time.LocalDate;

/**
 * A simple AuthorizationStrategy which implements the following rules:
 *
 * Expiration Date is greater than the current date
 * When Zip code is provided, a transaction is approved if amount is less than $200
 * When Zip code is not provided, a transaction is approved if amount is less than $100
 */
public class NaiveAuthorizationStrategy implements AuthorizationStrategy {
    // values are in pennies (dollars * 100)
    private static final long MAX_WITHOUT_ZIPCODE = 10000L;
    private static final long MAX_WITH_ZIPCODE = 20000L;

    /**
     *
     * @param request - a validated instance of AuthorizationRequest
     * @return
     */
    @Override
    public AuthorizationResponse authorize(final AuthorizationRequest request) {
        if (request == null)
            throw new IllegalArgumentException("'request' MUST NOT be null, ignoring");

        AuthorizationResponse response = null;

        boolean rulesAuth = runRules(request);
        if (rulesAuth) {
            // success, the transaction MUST be authorized
            response = request.createResponseMessage(AuthorizationResponse.RESPONSE_OK);

        } else {
            // no joy, the transaction MUST NOT be authorized
            response = request.createResponseMessage(AuthorizationResponse.RESPONSE_DECLINE);
        }

        return response;
    }

    protected boolean runRules(final AuthorizationRequest request) {
        if (request == null)
            throw new IllegalArgumentException("'request' MUST NOT be null, ignoring");

        return request.getExpirationDate().isAfter(LocalDate.now())
                && ((request.getZipCode() == null && request.getAmountRaw() < MAX_WITHOUT_ZIPCODE)
                || (request.getZipCode() != null && request.getAmountRaw() < MAX_WITH_ZIPCODE));
    }
}
