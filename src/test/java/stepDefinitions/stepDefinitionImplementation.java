package stepDefinitions;

import PageObjects.*;
import abhishek_Learns.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class stepDefinitionImplementation extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;

    ConfirmationPage confirmationPage;

    @Given("I landed on ecommerce page")
    public void I_landed_on_eCommerce_Page() throws IOException {
        landingPage = launchApplication();
    }
    @Given("^Logged in with email (.+) and password (.+)$")
    public void loginWithCreds(String email, String password){
        productCatalogue = landingPage.loginToApplication(email, password);
    }

    @When("^I add product (.+) to cart$")
    public void addProductToCart(String product){
        productCatalogue.getProductsList();
        productCatalogue.getProductByName(product);
        productCatalogue.addProductToCart(product);
    }

    @And("^Checkout (.+) and submit the order$")
    public void checkout(String product) throws InterruptedException {
        MyCart myCart = productCatalogue.clickOnCartBtn();

        Boolean match = myCart.verifyProductInCart(product);
        Assert.assertTrue(match);
        PaymentGateway paymentGateway = myCart.proceedToCheckout();

        paymentGateway.selectCountry("India");
        confirmationPage = paymentGateway.placeOrder();
    }

    @Then("{string} message is displayed on confirmationPage")
    public void confirmationPage(String message){
        String confirmationMsgText = confirmationPage.getConfirmationMsg();
        System.out.println(confirmationMsgText);
        Assert.assertTrue(confirmationMsgText.equals(message));
    }
}
