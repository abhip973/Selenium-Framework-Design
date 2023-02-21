package abhishek_Learns;

import abhishek_Learns.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidations extends BaseTest {

    @Test
    public void invalidCredentials() {
        landingPage.loginToApplication("punj.abhi@gmail.com", "Abhi@1234");
        String errorMsgText = landingPage.getErrorMsg();
        Assert.assertEquals(errorMsgText, "Incorrect email or password.");
    }
}
