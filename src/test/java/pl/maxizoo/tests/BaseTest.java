package pl.maxizoo.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pl.maxizoo.utils.PropertyLoader;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    WebDriver driver;

    @BeforeMethod
    public void setDriver() throws IOException {
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(PropertyLoader.loadProperty("baseURL"));
    }

//    @AfterMethod
//    public void afterMethod(){
//        driver.quit();
//    }

}
