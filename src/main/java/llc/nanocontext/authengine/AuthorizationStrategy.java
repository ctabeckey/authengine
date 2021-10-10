package llc.nanocontext.authengine;

import llc.nanocontext.authengine.message.AuthorizationRequest;
import llc.nanocontext.authengine.message.AuthorizationResponse;

/**
 *
 */
public interface AuthorizationStrategy {
    AuthorizationResponse authorize(AuthorizationRequest request);
}
