package llc.nanocontext.authengine;

import llc.nanocontext.authengine.message.AuthorizationRequest;
import llc.nanocontext.authengine.message.AuthorizationResponse;
import llc.nanocontext.authengine.message.ISO8583.V1987.ISO8583_V1987_AuthorizationRequestMessageBuilder;
import llc.nanocontext.authengine.message.ISO8583.V1987.ISO8583_V1987_AuthorizationResponseMessageBuilder;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class NaiveAuthorizationStrategyTest {
    private final NaiveAuthorizationStrategy authorizationStrategy = new NaiveAuthorizationStrategy();

    @DataProvider
    public Object[][] authorizationDataProvider() {
        return new Object[][] {
                {(new ISO8583_V1987_AuthorizationRequestMessageBuilder())
                        .withPan("1234567890123456789")
                        .withAmountRaw(100L)
                        .withZipCode("12345")
                        .withExpirationDate(LocalDate.of (2030, 12, 31))
                        .build(),
                (new ISO8583_V1987_AuthorizationResponseMessageBuilder())
                        .withPan("1234567890123456789")
                        .withAmountRaw(100L)
                        .withZipCode("12345")
                        .withResponseCode(AuthorizationResponse.RESPONSE_OK)
                        .withExpirationDate(LocalDate.of (2030, 12, 31))
                        .build(),
                },
                {(new ISO8583_V1987_AuthorizationRequestMessageBuilder())
                        .withPan("1234567890123456789")
                        .withAmountRaw(1000000L)
                        .withZipCode("12345")
                        .withExpirationDate(LocalDate.of (2030, 12, 31))
                        .build(),
                (new ISO8583_V1987_AuthorizationResponseMessageBuilder())
                        .withPan("1234567890123456789")
                        .withAmountRaw(1000000L)
                        .withZipCode("12345")
                        .withResponseCode(AuthorizationResponse.RESPONSE_DECLINE)
                        .withExpirationDate(LocalDate.of (2030, 12, 31))
                        .build(),
                },
                {(new ISO8583_V1987_AuthorizationRequestMessageBuilder())
                        .withPan("1234567890123456789")
                        .withAmountRaw(100L)
                        .withZipCode("12345")
                        .withExpirationDate(LocalDate.of (2020, 12, 31))
                        .build(),
                (new ISO8583_V1987_AuthorizationResponseMessageBuilder())
                        .withPan("1234567890123456789")
                        .withAmountRaw(100L)
                        .withZipCode("12345")
                        .withResponseCode(AuthorizationResponse.RESPONSE_DECLINE)
                        .withExpirationDate(LocalDate.of (2020, 12, 31))
                        .build(),
                },
        };
    }

    @Test(dataProvider = "authorizationDataProvider")
    public void testAuthorization(final AuthorizationRequest request, final AuthorizationResponse expected) {
        AuthorizationResponse actual = authorizationStrategy.authorize(request);

        Assert.assertEquals(expected.getResponseCode(), actual.getResponseCode());
    }
}
