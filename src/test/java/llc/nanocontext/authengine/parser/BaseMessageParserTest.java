package llc.nanocontext.authengine.parser;

import llc.nanocontext.authengine.message.Message;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BaseMessageParserTest {

    @DataProvider
    public Object[][] validRawMessageText() {
        return new Object[][] {
                {"0100e016411111111111111112250000001000"},
                {"0100e016401288888888188112250000011000"},
                {"0100ec1651051051051051001225000001100011MASTER YODA90089"},
                {"0100e016411111111111111112180000001000"},
                {"01006012250000001000"},
        };
    }

    public void testJunk() {
        Assert.assertNotNull(new Object());
    }

    @Test(dataProvider = "validRawMessageText")
    public void testParse(final String rawMessage) {
        BaseMessageParser parser = new BaseMessageParser();

        Message msg = parser.parse(rawMessage);

        Assert.assertNotNull(msg);
    }
}
