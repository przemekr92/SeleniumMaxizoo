package pl.maxizoo.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class SeleniumHelper {
    private static int defaultWaitInSeconds = 5;

    public static void waitForElementToBeVisible(WebDriver driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(defaultWaitInSeconds));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
}
