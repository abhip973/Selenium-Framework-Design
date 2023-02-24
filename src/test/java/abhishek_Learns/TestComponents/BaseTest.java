package abhishek_Learns.TestComponents;

import PageObjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;

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

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();

        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        driver.close();
    }

    public List<HashMap<String, String>> getJsonDataToHashMap(String filePath) throws IOException {

        //Step 1: Read JSON to string
        String jsonContent = FileUtils.readFileToString(new File(filePath));

        //Step 2: String to HashMap Jackson Databind
        //Creating an object of ObjectMapper class
        ObjectMapper objectMapper = new ObjectMapper();

        //readValue helps us to convert our string to List consisting of Hashmap values
        List<HashMap<String,String>> data = objectMapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});

        return data;
    }


    public File getScreenshot(String testCaseName) throws IOException {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File("src//Screenshots"+testCaseName+"png");

        FileUtils.copyFile(screenshotFile,destinationFile);
        return destinationFile;
    }
}
