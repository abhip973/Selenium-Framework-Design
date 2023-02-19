package PageObjects;

import Utilities.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends Utility {
    WebDriver driver;

    public LandingPage(WebDriver driver) {

        super(driver);

        //this.driver points to the driver object in our current instance of class
        this.driver = driver;

        //To tell factory about the driver, we have to implement in following way:
        //This will initialize all our web elements.
        PageFactory.initElements(driver, this);

    }


//    WebElement userEmail = driver.findElement(By.cssSelector("input#userEmail"));

    //Page Factory Logic
    @FindBy(css = "input#userEmail")
    WebElement userEmail;

    @FindBy(css = "input#userPassword")
    WebElement password;

    @FindBy(css = "input#login")
    WebElement loginBtn;

    public ProductCatalogue loginToApplication(String email, String pwd) {
        userEmail.sendKeys(email);
        password.sendKeys(pwd);
        loginBtn.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");
    }


}
