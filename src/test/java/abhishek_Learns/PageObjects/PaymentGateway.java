package abhishek_Learns.PageObjects;

import abhishek_Learns.Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentGateway extends Utility {

    @FindBy(css = "input[placeholder='Select Country']")
    WebElement countryPlaceholder;
    @FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
    WebElement countryOption;
    @FindBy(css = "[class*='action__submit']")
    WebElement placeOrderBtn;

    By countryDropdown = By.cssSelector("[class*='ta-results list-group ng-star-inserted']");

    WebDriver driver;

    public PaymentGateway(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectCountry(String country) throws InterruptedException {
        countryPlaceholder.sendKeys(country);
        waitForElementToAppear(countryDropdown);
        Thread.sleep(2000);
        countryOption.click();
    }

    public ConfirmationPage placeOrder() {
        placeOrderBtn.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }
}
