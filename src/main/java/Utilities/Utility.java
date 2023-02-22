package Utilities;

import PageObjects.MyCart;
import PageObjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utility {

    WebDriver driver;

    public Utility(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[routerLink*='cart']")
    WebElement cartBtn;

    @FindBy(xpath = "//button[@routerLink = '/dashboard/myorders']")
    WebElement orderHistoryLink;

    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForElementToDisappear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
    }

    public MyCart clickOnCartBtn() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", cartBtn);
        MyCart myCart = new MyCart(driver);
        return myCart;
    }

    public void scroll() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(1078,653)");
    }

    public OrderPage navigateToOrderHistory() {
        orderHistoryLink.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;

    }
}
