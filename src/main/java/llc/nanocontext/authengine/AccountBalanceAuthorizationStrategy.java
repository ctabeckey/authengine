package llc.nanocontext.authengine;

import llc.nanocontext.authengine.message.AuthorizationRequest;
import llc.nanocontext.authengine.message.AuthorizationResponse;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccountBalanceAuthorizationStrategy
extends NaiveAuthorizationStrategy
implements AuthorizationStrategy {
    // values are in pennies (dollars * 100)
    private final static Long DEFAULT_BALANCE = Long.valueOf(100000L);
    private final ConcurrentHashMap<String, Long> accountBalances = new ConcurrentHashMap<>();

    @Override
    public AuthorizationResponse authorize(final AuthorizationRequest request) {
        final boolean rulesAuth = runRules(request);
        final Long transactionAmount = request.getAmountRaw();
        if (transactionAmount == null || transactionAmount <= 0l)

        AuthorizationResponse response = null;

        accountBalances.compute(request.getPan(), (pan, startingBalance) -> {
            if (startingBalance == null) {
                return DEFAULT_BALANCE - transactionAmount;
            }
            if (startingBalance >= transactionAmount) {
                return startingBalance - transactionAmount;
            }
            return Long.valueOf(0l);
        });

        Long currentBalance = accountBalances.get(request.getPan());
        if (currentBalance == null) {
            currentBalance = DEFAULT_BALANCE;
        }

        if (rulesAuth && currentBalance >= request.getAmountRaw()) {
            accountBalances.put(request.getPan(), currentBalance - request.getAmountRaw());
            // success, the transaction MUST be authorized
            response = request.createResponseMessage(AuthorizationResponse.RESPONSE_OK);
        } else {
            // no joy, the transaction MUST NOT be authorized
            response = request.createResponseMessage(AuthorizationResponse.RESPONSE_DECLINE);
        }

        return response;
    }
}
