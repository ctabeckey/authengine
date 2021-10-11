package llc.nanocontext.authengine;

import llc.nanocontext.authengine.message.PrimaryBitmapFields;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PrimaryBitmapFieldsTest {

    @DataProvider
    public Object[][] testSumDataProvider() {
        return new Object[][] {
                {new PrimaryBitmapFields[]{PrimaryBitmapFields.PAN}, "80"},
                {new PrimaryBitmapFields[]{PrimaryBitmapFields.PAN, PrimaryBitmapFields.CardholderName}, "88"},
                {new PrimaryBitmapFields[]{PrimaryBitmapFields.PAN, PrimaryBitmapFields.ExpirationDate}, "c0"},
                {new PrimaryBitmapFields[]{PrimaryBitmapFields.PAN, PrimaryBitmapFields.TransactionAmount}, "a0"},
                {new PrimaryBitmapFields[]{PrimaryBitmapFields.PAN, PrimaryBitmapFields.ExpirationDate, PrimaryBitmapFields.TransactionAmount}, "e0"},
        };
    }

    @Test(dataProvider = "testSumDataProvider")
    public void testSum(final PrimaryBitmapFields[] fields, final String expected) {
        int map = 0x00;

        if (fields != null)
            for (int index=0; index < fields.length; ++index)
                map = PrimaryBitmapFields.sum(map, fields[index]);
        final String actual = PrimaryBitmapFields.format(map);
        Assert.assertEquals(actual, expected);
    }
}
