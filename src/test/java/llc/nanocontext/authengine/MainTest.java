package llc.nanocontext.authengine;

import org.testng.annotations.Test;

public class MainTest {
    @Test
    public void testCLI() throws Exception {
        Main.main(new String[]{
                // 0100 0e 16(4111111111111111) 1225 0000001000
                "0100e016411111111111111112250000001000",
                "0100e016401288888888188112250000011000",
                "0100ec1651051051051051001225000001100011MASTER YODA90089",
                "0100e016411111111111111112180000001000",
                "01006012250000001000"
        });
    }
}
