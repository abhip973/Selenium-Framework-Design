package abhishek_Learns.PageObjects;

import abhishek_Learns.Utilities.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ConfirmationPage extends Utility {
    WebDriver driver;

    @FindBy(css = "h1.hero-primary")
    WebElement confirmationMessage;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getConfirmationMsg() {
        String confirmationMsgText = confirmationMessage.getText();
        return confirmationMsgText;
    }
}
