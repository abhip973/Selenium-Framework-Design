package PageObjects;

import Utilities.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MyCart extends Utility {
    WebDriver driver;


    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProductsList;


    @FindBy(css = "div.subtotal.cf.ng-star-inserted ul li button")
    WebElement checkoutBtn;


    public MyCart(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean verifyProductInCart(String productName) {

        Boolean match = cartProductsList.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }

    public PaymentGateway proceedToCheckout() {
        checkoutBtn.click();
        PaymentGateway paymentGateway = new PaymentGateway(driver);
        return paymentGateway;
    }
}
