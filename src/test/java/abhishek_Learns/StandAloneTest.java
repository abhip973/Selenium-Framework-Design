package abhishek_Learns;

import abhishek_Learns.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.time.Duration;
import java.util.List;


public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {

        String productName = "ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        LandingPage landingPage = new LandingPage(driver);
        //Declaring explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));


        //Applying implicit wait of 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.cssSelector("input#userEmail")).sendKeys("punj.abhishek1@gmail.com");
        driver.findElement(By.cssSelector("input#userPassword")).sendKeys("Abhi@123");
        driver.findElement(By.cssSelector("input#login")).click();

        List<WebElement> products = driver.findElements(By.xpath("//div[@class='card']/div/h5"));

        //Using streams we are finding our element and storing the web element to later click on that.
        WebElement prod = products.stream().filter(product -> product.getText().contains(productName)).findFirst().orElse(null);

        //Now we will click on the Add to Cart button by narrowing our scope using prod.
        prod.findElement(By.xpath("//../button[2]")).click();

        //Applying explicit wait to confirmation toast
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

        //Applying explicit wait to loader
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

        WebElement cartBtn = driver.findElement(By.cssSelector("button[routerLink*='cart']"));

        //We are using JavaScriptExecutor because while clicking the link, we get the IllegalStateException
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", cartBtn);


        //This gets us all the products added in the cart
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

        //anyMatch function in stream tells us if there is any product with our product Name
        Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);

        driver.findElement(By.cssSelector("div.subtotal.cf.ng-star-inserted ul li button")).click();

        driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("india");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='ta-results list-group ng-star-inserted']")));

        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();

        js.executeScript("scrollBy(200,300)");
        driver.findElement(By.cssSelector("[class*='action__submit']")).click();

        String orderConfirmation = driver.findElement(By.cssSelector("h1.hero-primary")).getText();
        Assert.assertTrue(orderConfirmation.equals("THANKYOU FOR THE ORDER."));
        driver.close();
    }
}
