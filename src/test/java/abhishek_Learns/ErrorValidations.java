package abhishek_Learns;

import abhishek_Learns.TestComponents.BaseTest;
import abhishek_Learns.TestComponents.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidations extends BaseTest {

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void loginErrorValidations() {
        landingPage.loginToApplication("punj.abhi@gmail.com", "Abhi@1234");
        String errorMsgText = landingPage.getErrorMsg();
        Assert.assertEquals(errorMsgText, "Incorrect email  password.");
    }
}
