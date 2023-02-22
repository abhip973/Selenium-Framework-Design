package abhishek_Learns;

import PageObjects.*;

import abhishek_Learns.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class StandAloneTest1 extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test
    public void submitOrder() throws IOException, InterruptedException {
//        String productName = "ZARA COAT 3";
        String confirmationMsg = "THANKYOU FOR THE ORDER.";
        String country = "India";


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
        System.out.println(confirmationMsgText);
        Assert.assertTrue(confirmationMsgText.equals(confirmationMsg));
    }

    //To verify ZARA Coat 3 is displaying in orders page
    //It will only be displayed once the order is submitted successfully
    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest() {

        ProductCatalogue productCatalogue = landingPage.loginToApplication("punj.abhishek1@gmail.com", "Abhi@123");
        OrderPage orderPage = productCatalogue.navigateToOrderHistory();

        Boolean orderFoundActual = orderPage.verifyOrderDisplay(productName);
        Assert.assertTrue(orderFoundActual);

    }
}
