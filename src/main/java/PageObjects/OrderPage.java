package PageObjects;

import Utilities.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends Utility {

    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "tbody tr td:nth-child(3)")
    List<WebElement> productNamesList;

    public Boolean verifyOrderDisplay(String productName) {
        Boolean productMatch = productNamesList.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
        return productMatch;
    }
}
