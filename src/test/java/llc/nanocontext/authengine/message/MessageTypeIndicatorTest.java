package llc.nanocontext.authengine.message;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MessageTypeIndicatorTest {

    @DataProvider
    public Object[][] validRawAndProperties() {
        return new Object[][]{
                {"0100", (byte)0, (byte)1, (byte)0, (byte)0},
                {"0123", (byte)0, (byte)1, (byte)2, (byte)3},
                {"9876", (byte)9, (byte)8, (byte)7, (byte)6}
        };
    }

    @Test(dataProvider = "validRawAndProperties")
    public void testInstantiation(final String raw, final byte version, final byte clazz, final byte function, final byte origin) {
        MessageTypeIndicator mti = MessageTypeIndicator.builder().withRaw(raw).build();

        Assert.assertEquals(mti.getVersion(), version);
        Assert.assertEquals(mti.getClazz(), clazz);
        Assert.assertEquals(mti.getFunction(), function);
        Assert.assertEquals(mti.getOrigin(), origin);
    }

    @Test(dataProvider = "validRawAndProperties")
    public void testEquality(final String raw, final byte version, final byte clazz, final byte function, final byte origin) {
        MessageTypeIndicator mti1 = MessageTypeIndicator.builder().withRaw(raw).build();
        MessageTypeIndicator mti2 = MessageTypeIndicator.builder().withRaw(raw).build();

        Assert.assertEquals(mti1, mti2);
    }
}
