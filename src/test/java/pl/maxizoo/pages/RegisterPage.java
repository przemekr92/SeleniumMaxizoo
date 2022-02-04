package pl.maxizoo.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.maxizoo.utils.SeleniumHelper;

public class RegisterPage {

    @FindBy(id = "register.userData.email")
    private WebElement registrationEmailInput;

    @FindBy(id = "register.userData.password")
    private WebElement registrationPasswordInput;

    @FindBy(className = "checkbox")
    private WebElement registrationAgreementCheckbox;

    @FindBy(xpath = "//button[@data-id='register.userData.submit']")
    private WebElement registerBtn;

    //@FindBy(xpath = "(//*[@class='ffw-input'])[1]//div[@class='em-content']")
    @FindBy(xpath = "(//*[@class='form-section'])[1]//div[@class='em-content']")
    private WebElement mailErrorInfo;

    //@FindBy(xpath = "//div[@class='password-field']//div[@class='em-content']")
    @FindBy(xpath = "(//*[@class='form-section'])[2]//div[@class='em-content']")
    private WebElement passwordErrorInfo;

    @FindBy(xpath = "(//*[@class='form-section'])[3]//div[@class='em-content']")
    private WebElement agreementErrorInfo;

    @FindBy(css = ".ab-main p")
    private WebElement takenMailErrorInfo;

    private WebDriver driver;

    public RegisterPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public void setRegisterDataAndSubmit(String mail, String password, Boolean agreement) {
        SeleniumHelper.waitForElementToBeVisible(this.driver, registrationEmailInput);
        registrationEmailInput.sendKeys(mail);
        registrationPasswordInput.sendKeys(password);
        if(agreement){
            registrationAgreementCheckbox.click();
        }
        submitForm();
    }

    public String getMailErrorInfo(){
        return mailErrorInfo.getText();
    }

    public String getPasswordErrorInfo(){
        return passwordErrorInfo.getText();
    }

    public String getAgreementErrorInfo(){
        return agreementErrorInfo.getText();
    }

    public String getTakenMailErrorInfo() {
        SeleniumHelper.waitForElementToBeVisible(driver, takenMailErrorInfo);
        return takenMailErrorInfo.getText();
    }

    public void addKeysToPassword(String keys) {
        registrationPasswordInput.sendKeys(keys);
    }

    public void clearPasswordField(){
        registrationPasswordInput.clear();
    }

    public void submitForm(){
        registerBtn.click();
    }

    public Boolean isPasswordErrorInfoPresent(){
        try{
            return passwordErrorInfo.isDisplayed();
        } catch(NoSuchElementException e){
            return false;
        }
    }
}
