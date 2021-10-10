package llc.nanocontext.authengine.message.ISO8583.V1987;

import llc.nanocontext.authengine.message.PrimaryBitmapFields;
import llc.nanocontext.authengine.parser.AlphaFieldParser;
import llc.nanocontext.authengine.parser.ExpirationDateFieldParser;
import llc.nanocontext.authengine.parser.LLVarFieldParser;
import llc.nanocontext.authengine.parser.NumericFieldParser;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class ISO8583_V1987_AuthorizationMessageTest {
    final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    @DataProvider
    public Object[][] validAuthorizationMessageProperties() {
        return new Object[][] {
                {"12345678901234", Long.valueOf(1000L), LocalDate.of(2030, 12, 31), "MICKEY MOUSE", "65532", "OK"},
                {"12345678901234", Long.valueOf(1000L), LocalDate.of(2030, 12, 31), null, null, "OK"},
        };
    }

    @Test(dataProvider = "validAuthorizationMessageProperties")
    public void testValidAuthorizationRequestMessageConstructionByProperty(
            final String pan,
            final Long amount,
            final LocalDate expirationDate,
            final String cardholderName,
            final String zipCode,
            final String responseCode) {
        ISO8583_V1987_AuthorizationRequestMessage msg =
                (new ISO8583_V1987_AuthorizationRequestMessageBuilder())
                .withPan(pan)
                .withAmountRaw(amount)
                .withExpirationDate(expirationDate)
                .withCardholderName(cardholderName)
                .withZipCode(zipCode)
                .build();

        Set<ConstraintViolation<ISO8583_V1987_AuthorizationRequestMessage>> constraintViolations = validator.validate(msg);
        Assert.assertTrue(constraintViolations == null || constraintViolations.isEmpty(), constraintViolations.toString());
    }

    @Test(dataProvider = "validAuthorizationMessageProperties")
    public void testValidAuthorizationResponseMessageConstructionByProperty(
            final String pan,
            final Long amount,
            final LocalDate expirationDate,
            final String cardholderName,
            final String zipCode,
            final String responseCode) {
        ISO8583_V1987_AuthorizationResponseMessage msg =
                (new ISO8583_V1987_AuthorizationResponseMessageBuilder())
                        .withPan(pan)
                        .withAmountRaw(amount)
                        .withExpirationDate(expirationDate)
                        .withCardholderName(cardholderName)
                        .withZipCode(zipCode)
                        .withResponseCode(responseCode)
                        .build();

        Set<ConstraintViolation<ISO8583_V1987_AuthorizationResponseMessage>> constraintViolations = validator.validate(msg);
        Assert.assertTrue(constraintViolations == null || constraintViolations.isEmpty(), constraintViolations.toString());
    }

    @Test(dataProvider = "validAuthorizationMessageProperties")
    public void testValidAuthorizationRequestMessageConstructionByParsedField(
            final String pan,
            final Long amount,
            final LocalDate expirationDate,
            final String cardholderName,
            final String zipCode,
            final String responseCode) {
        final String amountAsString = String.format("%010d", amount);
        final String expirationDateAsString = expirationDate.format(DateTimeFormatter.ofPattern("MMyy"));

        ISO8583_V1987_AuthorizationRequestMessageBuilder msgBuilder =
                (new ISO8583_V1987_AuthorizationRequestMessageBuilder())
                        .with(
                                PrimaryBitmapFields.PAN,
                                new LLVarFieldParser().parse("" + pan.length() + pan, 0)
                        )
                        .with(PrimaryBitmapFields.TransactionAmount, new NumericFieldParser(10).parse(amountAsString, 0))
                        .with(PrimaryBitmapFields.ExpirationDate, new ExpirationDateFieldParser().parse(expirationDateAsString, 0));
        if (cardholderName != null)
            msgBuilder.with(
                    PrimaryBitmapFields.CardholderName,
                    new LLVarFieldParser().parse("" + cardholderName.length() + cardholderName, 0)
            );

        if (zipCode != null)
            msgBuilder.with(PrimaryBitmapFields.ZipCode, new AlphaFieldParser(5).parse(zipCode, 0));

        ISO8583_V1987_AuthorizationRequestMessage msg = msgBuilder.build();

        Set<ConstraintViolation<ISO8583_V1987_AuthorizationRequestMessage>> constraintViolations = validator.validate(msg);
        Assert.assertTrue(constraintViolations == null || constraintViolations.isEmpty(), constraintViolations.toString());
    }

    @Test(dataProvider = "validAuthorizationMessageProperties")
    public void testValidAuthorizationResponseMessageConstructionByParsedField(
            final String pan,
            final Long amount,
            final LocalDate expirationDate,
            final String cardholderName,
            final String zipCode,
            final String responseCode) {
        final String amountAsString = String.format("%010d", amount);
        final String expirationDateAsString = expirationDate.format(DateTimeFormatter.ofPattern("MMyy"));

        ISO8583_V1987_AuthorizationResponseMessageBuilder msgBuilder =
                (new ISO8583_V1987_AuthorizationResponseMessageBuilder())
                        .with(
                                PrimaryBitmapFields.PAN,
                                new LLVarFieldParser().parse("" + pan.length() + pan, 0)
                        )
                        .with(PrimaryBitmapFields.TransactionAmount, new NumericFieldParser(10).parse(amountAsString, 0))
                        .with(PrimaryBitmapFields.ExpirationDate, new ExpirationDateFieldParser().parse(expirationDateAsString, 0))
                        .with(PrimaryBitmapFields.ResponseCode, new AlphaFieldParser(2).parse(responseCode, 0));
        if (cardholderName != null)
            msgBuilder.with(
                    PrimaryBitmapFields.CardholderName,
                    new LLVarFieldParser().parse("" + cardholderName.length() + cardholderName, 0)
            );

        if (zipCode != null)
            msgBuilder.with(PrimaryBitmapFields.ZipCode, new AlphaFieldParser(5).parse(zipCode, 0));

        ISO8583_V1987_AuthorizationResponseMessage msg = msgBuilder.build();

        Set<ConstraintViolation<ISO8583_V1987_AuthorizationResponseMessage>> constraintViolations = validator.validate(msg);
        Assert.assertTrue(constraintViolations == null || constraintViolations.isEmpty(), constraintViolations.toString());
    }
}
