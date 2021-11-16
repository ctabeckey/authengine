package llc.nanocontext.authengine;

import llc.nanocontext.authengine.message.AuthorizationRequest;
import llc.nanocontext.authengine.message.AuthorizationResponse;
import llc.nanocontext.authengine.message.ISO8583.V1987.ISO8583_V1987_AuthorizationRequestMessageBuilder;
import llc.nanocontext.authengine.message.ISO8583.V1987.ISO8583_V1987_AuthorizationResponseMessageBuilder;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Arrays;

public class AccountBalanceAuthorizationStrategyTest {
    @DataProvider
    public Object[][] authorizationDataProvider() {
        return new Object[][] {
                {new AuthorizationRequest[] {
                    (new ISO8583_V1987_AuthorizationRequestMessageBuilder())
                        .withPan("1234567890123456789")
                        .withAmountRaw(19999L)
                        .withZipCode("12345")
                        .withExpirationDate(LocalDate.of (2030, 12, 31))
                        .build(),
                },
                (new ISO8583_V1987_AuthorizationResponseMessageBuilder())
                        .withPan("1234567890123456789")
                        .withAmountRaw(19999L)
                        .withZipCode("12345")
                        .withResponseCode(AuthorizationResponse.RESPONSE_OK)
                        .withExpirationDate(LocalDate.of (2030, 12, 31))
                        .build(),
                },
                {new AuthorizationRequest[] {
                        (new ISO8583_V1987_AuthorizationRequestMessageBuilder())
                                .withPan("1234567890123456789")
                                .withAmountRaw(19999L)
                                .withZipCode("12345")
                                .withExpirationDate(LocalDate.of (2030, 12, 31))
                                .build(),
                        (new ISO8583_V1987_AuthorizationRequestMessageBuilder())
                                .withPan("1234567890123456789")
                                .withAmountRaw(19999L)
                                .withZipCode("12345")
                                .withExpirationDate(LocalDate.of (2030, 12, 31))
                                .build(),
                        (new ISO8583_V1987_AuthorizationRequestMessageBuilder())
                                .withPan("1234567890123456789")
                                .withAmountRaw(19999L)
                                .withZipCode("12345")
                                .withExpirationDate(LocalDate.of (2030, 12, 31))
                                .build(),
                        (new ISO8583_V1987_AuthorizationRequestMessageBuilder())
                                .withPan("1234567890123456789")
                                .withAmountRaw(19999L)
                                .withZipCode("12345")
                                .withExpirationDate(LocalDate.of (2030, 12, 31))
                                .build(),
                        (new ISO8583_V1987_AuthorizationRequestMessageBuilder())
                                .withPan("1234567890123456789")
                                .withAmountRaw(19999L)
                                .withZipCode("12345")
                                .withExpirationDate(LocalDate.of (2030, 12, 31))
                                .build(),
                },
                        (new ISO8583_V1987_AuthorizationResponseMessageBuilder())
                                .withPan("1234567890123456789")
                                .withAmountRaw(19999L)
                                .withZipCode("12345")
                                .withResponseCode(AuthorizationResponse.RESPONSE_OK)
                                .withExpirationDate(LocalDate.of (2030, 12, 31))
                                .build(),
                },
                {new AuthorizationRequest[] {
                        (new ISO8583_V1987_AuthorizationRequestMessageBuilder())
                                .withPan("1234567890123456789")
                                .withAmountRaw(19999L)
                                .withZipCode("12345")
                                .withExpirationDate(LocalDate.of (2030, 12, 31))
                                .build(),
                        (new ISO8583_V1987_AuthorizationRequestMessageBuilder())
                                .withPan("1234567890123456789")
                                .withAmountRaw(19999L)
                                .withZipCode("12345")
                                .withExpirationDate(LocalDate.of (2030, 12, 31))
                                .build(),
                        (new ISO8583_V1987_AuthorizationRequestMessageBuilder())
                                .withPan("1234567890123456789")
                                .withAmountRaw(19999L)
                                .withZipCode("12345")
                                .withExpirationDate(LocalDate.of (2030, 12, 31))
                                .build(),
                        (new ISO8583_V1987_AuthorizationRequestMessageBuilder())
                                .withPan("1234567890123456789")
                                .withAmountRaw(19999L)
                                .withZipCode("12345")
                                .withExpirationDate(LocalDate.of (2030, 12, 31))
                                .build(),
                        (new ISO8583_V1987_AuthorizationRequestMessageBuilder())
                                .withPan("1234567890123456789")
                                .withAmountRaw(19999L)
                                .withZipCode("12345")
                                .withExpirationDate(LocalDate.of (2030, 12, 31))
                                .build(),
                        (new ISO8583_V1987_AuthorizationRequestMessageBuilder())
                                .withPan("1234567890123456789")
                                .withAmountRaw(19999L)
                                .withZipCode("12345")
                                .withExpirationDate(LocalDate.of (2030, 12, 31))
                                .build(),
                },
                        (new ISO8583_V1987_AuthorizationResponseMessageBuilder())
                                .withPan("1234567890123456789")
                                .withAmountRaw(19999L)
                                .withZipCode("12345")
                                .withResponseCode(AuthorizationResponse.RESPONSE_DECLINE)
                                .withExpirationDate(LocalDate.of (2030, 12, 31))
                                .build(),
                },
        };
    }

    @Test(dataProvider = "authorizationDataProvider")
    public void testAuthorization(final AuthorizationRequest[] requests, final AuthorizationResponse expected) {
        final AuthorizationStrategy authorizationStrategy = new AccountBalanceAuthorizationStrategy();

        AuthorizationResponse actual = null;
        for (int index = 0; index < requests.length; ++index) {
            // this will only save the last response, that is intentional
            actual = authorizationStrategy.authorize(requests[index]);
        }


        Assert.assertEquals(actual.getResponseCode(), expected.getResponseCode());
    }
}
