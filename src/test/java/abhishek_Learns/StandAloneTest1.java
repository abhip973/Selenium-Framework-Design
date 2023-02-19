package abhishek_Learns;

import PageObjects.*;

import abhishek_Learns.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;


public class StandAloneTest1 extends BaseTest {

    @Test
    public void submitOrder() throws IOException, InterruptedException {
        String productName = "ZARA COAT 3";
        String confirmationMsg = "THANKYOU FOR THE ORDER.";
        String country = "India";


        LandingPage landingPage = launchApplication();
        ProductCatalogue productCatalogue = landingPage.loginToApplication("punj.abhishek1@gmail.com", "Abhi@123");


        productCatalogue.getProductsList();
        productCatalogue.getProductByName(productName);
        productCatalogue.addProductToCart(productName);
        MyCart myCart = productCatalogue.clickOnCartBtn();

        Boolean match = myCart.verifyProductInCart(productName);
        Assert.assertTrue(match);
        PaymentGateway paymentGateway = myCart.proceedToCheckout();

        paymentGateway.selectCountry(country);
        ConfirmationPage confirmationPage = paymentGateway.placeOrder();

        String confirmationMsgText = confirmationPage.getConfirmationMsg();
        Assert.assertTrue(confirmationMsgText.equals(confirmationMsg));
        driver.close();
    }
}
