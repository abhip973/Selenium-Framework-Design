package abhishek_Learns;

import abhishek_Learns.PageObjects.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;


public class StandAloneTest1 {

    public static void main(String[] args) throws InterruptedException {

        String productName = "ZARA COAT 3";
        String confirmationMsg = "THANKYOU FOR THE ORDER.";
        String country = "India";

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        LandingPage landingPage = new LandingPage(driver);


        landingPage.goTo();
        ProductCatalogue productCatalogue = landingPage.loginToApplication("punj.abhishek1@gmail.com", "Abhi@123");

        //Applying implicit wait of 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

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
