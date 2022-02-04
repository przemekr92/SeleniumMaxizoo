package pl.maxizoo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.maxizoo.utils.SeleniumHelper;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class LoginPage {

    @FindBy(xpath = "//div[@class='p-main']//*[@id='login.email']")
    private WebElement loginMailInput; //id from dropdown login would be the same

    @FindBy(xpath = "//div[@class='p-main']//*[@id='login.password']")
    private WebElement loginPasswordInput;

    @FindBy(xpath = "//div[@class='p-main']//button[@data-id='forms.login.button.submit']")
    private WebElement logInBtn;

    WebDriver driver;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public void LogIn(String mail, String password){
        SeleniumHelper.waitForElementToBeVisible(this.driver, loginMailInput);
        loginMailInput.sendKeys(mail);
        loginPasswordInput.sendKeys(password);
        logInBtn.click();
    }
}
