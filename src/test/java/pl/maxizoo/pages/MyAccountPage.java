package pl.maxizoo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.maxizoo.utils.SeleniumHelper;

public class MyAccountPage {

    @FindBy(css = "h1.heading")
    private WebElement greetingHeader;

    @FindBy(css = "div.grid .account-logout a[href='/logout/']")
    private WebElement logoutHref;

    WebDriver driver;

    public String getGreetingHeaderText(){
        SeleniumHelper.waitForElementToBeVisible(driver, greetingHeader);
        return greetingHeader.getText();
    }

    public MyAccountPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }
}
