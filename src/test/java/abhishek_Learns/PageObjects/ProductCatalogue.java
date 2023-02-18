package abhishek_Learns.PageObjects;

import abhishek_Learns.Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends Utility {
    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //        List<WebElement> products = driver.findElements(By.xpath("//div[@class='card']/div/h5"));
    @FindBy(xpath = "//div[@class='card']/div/h5")
    List<WebElement> productList;
    @FindBy(css = "button[routerLink*='cart']")
    WebElement cartBtn;


    By productsListBy = By.xpath("//div[@class='card']/div/h5");
    By addToCart = By.xpath("//../button[2]");
    By confirmationToast = By.cssSelector("#toast-container");
    By loader = By.cssSelector(".ng-animating");


    public List<WebElement> getProductsList() {
        waitForElementToAppear(productsListBy);
        return productList;
    }

    public WebElement getProductByName(String productName) {
        WebElement prod = getProductsList().stream().filter(product -> product.getText().contains(productName)).findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(String productName) {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(confirmationToast);
        waitForElementToDisappear(loader);
    }


}
