package abhishek_Learns.TestComponents;

import PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;

    public WebDriver initializeDriver() throws IOException {

        Properties properties = new Properties();

        FileInputStream globalFile = new FileInputStream("src/main/java/Resources/GlobalData/GlobalData.properties");
        properties.load(globalFile);

        String browserName = properties.getProperty("browser");


        if (browserName.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();

        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }
}
