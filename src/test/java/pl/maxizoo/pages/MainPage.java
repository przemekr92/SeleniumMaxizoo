package pl.maxizoo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.maxizoo.utils.SeleniumHelper;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MainPage {

    @FindBy(xpath = "//a[@data-id='header.registrationLink']")
    private WebElement openRegistrationBtn;

    @FindBy(xpath = "//button[contains(., 'Akceptuję warunki i chcę przejść dalej')]")
    private WebElement acceptCookiesBtn;

    @FindBy(xpath = "//a[@data-id='header.navLink.my-account']")
    private WebElement myAccountBtn;

    @FindBy(xpath = "//a[@data-id='header.navLink.my-account.wishlist']")
    private WebElement wishListBtn;

    @FindBy(xpath = "//a[@data-id='header.navLink.cart']")
    private WebElement cartBtn;

    @FindBy(id = "login.email")
    private WebElement loginMailInput;

    @FindBy(id = "login.password")
    private WebElement loginPasswordInput;

    @FindBy(xpath = "//button[@data-id='forms.login.button.submit']")
    private WebElement logInBtn;

    @FindBy(className = "prefix")
    private WebElement welcomePrefixSpan;

    @FindBy(id = "wa_close")
    private WebElement closeAdBtn;

    @FindBy(css = ".account-nav a[href='/logout/']")
    private WebElement logoutDropdownHref1;

    @FindBy(css = ".account .account-logout a[href='/logout/']")
    private WebElement logoutDropdownHref2;

    private WebDriver driver;

    public MainPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
        CloseCookiesInfo();
    }

    public void CloseCookiesInfo(){
        SeleniumHelper.waitForElementToBeVisible(this.driver, acceptCookiesBtn);
        acceptCookiesBtn.click();
    }

    public void openSignInPage(){
        openRegistrationBtn.click();
    }

    public void openLoginPage(){
        Actions actions = new Actions(driver);
        actions.moveToElement(myAccountBtn).perform();
        SeleniumHelper.waitForElementToBeVisible(this.driver, loginMailInput);
        myAccountBtn.click();
    }

    public void setLoginDataAndSubmit(String login, String password){
        Actions actions = new Actions(driver);
        //myAccountBtn.click();
        actions.moveToElement(myAccountBtn).perform();
        SeleniumHelper.waitForElementToBeVisible(this.driver, loginMailInput);
        loginMailInput.sendKeys(login);
        loginPasswordInput.sendKeys(password);
        logInBtn.click();
    }

    public String getLoggedUserName(){
        return welcomePrefixSpan.getText();
    }

}
