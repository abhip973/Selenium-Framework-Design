package abhishek_Learns;

import PageObjects.*;

import abhishek_Learns.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class StandAloneTest1 extends BaseTest {
    //    String productName = "ZARA COAT 3";
    String productName;

    @Test(dataProvider = "getData")
    public void submitOrder(HashMap<String, String> map) throws IOException, InterruptedException {
//        String productName;
        String confirmationMsg = "THANKYOU FOR THE ORDER.";
        String country = "India";


        ProductCatalogue productCatalogue = landingPage.loginToApplication(map.get("email"), map.get("password"));


        productName = map.get("product");
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

    /*DataProvider can send the data in 2 ways:
    1. Using 2-dimensional object
    2. Using Hashmaps
     */

    //Using 2-D Objects implementation
//    @DataProvider
//    public Object[][] getData() {
//        Object[][] obj = new Object[2][3];
//
//        obj[0][0] = "punj.abhishek1@gmail.com";
//        obj[0][1] = "Abhi@123";
//        obj[0][2] = "ADIDAS ORIGINAL";
//
//        obj[1][0] = "ap5@hotmail.com";
//        obj[1][1] = "Abhi1234";
//        obj[1][2] = "ZARA COAT 3";
//        return obj;
//    }

    //Using Hash-Maps Implementation
//    @DataProvider
//    public Object[][] getData() throws IOException {
//        HashMap<String, String> map1 = new HashMap<String, String>();
//        map1.put("email", "punj.abhishek1@gmail.com");
//        map1.put("password", "Abhi@123");
//        map1.put("product", "ZARA COAT 3");
//
//        HashMap<String, String> map2 = new HashMap<String, String>();
//        map2.put("email", "ap5@hotmail.com");
//        map2.put("password", "Abhi1234");
//        map2.put("product", "ADIDAS ORIGINAL");
//
//        return new Object[][]{{map1}, {map2}};
//    }

    //Using Json File
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToHashMap("src/test/java/abhishek_Learns/data/PurchaseOrder/PurchaseOrder.json");

        return new Object[][] {{data.get(0)}, {data.get(1)}};
    }
}
