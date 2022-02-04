package pl.maxizoo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.maxizoo.pages.MainPage;
import pl.maxizoo.pages.MyAccountPage;
import pl.maxizoo.pages.RegisterPage;
import pl.maxizoo.utils.PropertyLoader;

import java.io.IOException;
import java.util.Random;

public class CreatingAccountTest extends BaseTest {
    String correctPass = "Password12#";

    @Test
    public void RegisterCorrectTest(){
        Random rand = new Random();
        Integer randomNum = rand.nextInt(1000);
        String mail = "ppp" + randomNum + "@ppp.pl";
        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        mainPage.openSignInPage();
        registerPage.setRegisterDataAndSubmit(mail,correctPass, true);
        Assert.assertTrue(myAccountPage.getGreetingHeaderText().contains(mail));
    }

    @Test
    public void RegisterEmptyFormTest(){
        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        mainPage.openSignInPage();
        registerPage.setRegisterDataAndSubmit("","",false);

        Assert.assertEquals(registerPage.getMailErrorInfo(),"To pole jest wymagane.");
        Assert.assertEquals(registerPage.getPasswordErrorInfo(),"To pole jest wymagane.");
        Assert.assertEquals(registerPage.getAgreementErrorInfo(),"To pole jest wymagane.");

    }

    @Test
    public void RegisterIncorrectMailTest(){
        String incorrectMail = "testmail";
        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        mainPage.openSignInPage();
        registerPage.setRegisterDataAndSubmit(incorrectMail,correctPass, true);
        Assert.assertEquals(registerPage.getMailErrorInfo(),"Proszę podać prawidłowy adres e-mail.");
    }

    @Test
    public void RegisterIncorrectPasswordTest(){
        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        mainPage.openSignInPage();
        registerPage.setRegisterDataAndSubmit("","",false);
        Assert.assertEquals(registerPage.getPasswordErrorInfo(),"To pole jest wymagane.");
        registerPage.addKeysToPassword("aaaa");
        Assert.assertEquals(registerPage.getPasswordErrorInfo(),"To pole musi zawierać co najmniej 8 znaków.");
        registerPage.addKeysToPassword("aaaa");
        Assert.assertEquals(registerPage.getPasswordErrorInfo(),"Hasło musi zawierać co najmniej jedną cyfrę.");
        registerPage.addKeysToPassword("1");
        Assert.assertEquals(registerPage.getPasswordErrorInfo(),"Hasło musi zawierać co najmniej jedną dużą literę.");
        registerPage.addKeysToPassword("A");
        Assert.assertEquals(registerPage.getPasswordErrorInfo(),"Hasło musi zawierać co najmniej jeden znak specjalny.");
        registerPage.clearPasswordField();
        registerPage.addKeysToPassword("AAAAAAAAA1");
        Assert.assertEquals(registerPage.getPasswordErrorInfo(),"Hasło musi zawierać co najmniej jedną małą literę.");
        registerPage.addKeysToPassword("a");
        Assert.assertEquals(registerPage.getPasswordErrorInfo(),"Hasło musi zawierać co najmniej jeden znak specjalny.");
        registerPage.addKeysToPassword("#");
        Assert.assertFalse(registerPage.isPasswordErrorInfoPresent());
    }

    @Test
    public void RegisterExistingMailTest() throws IOException {
        String registeredMail = PropertyLoader.loadProperty("registeredMail");
        String registeredPassword = PropertyLoader.loadProperty("registeredPassword");

        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        mainPage.openSignInPage();
        registerPage.setRegisterDataAndSubmit(registeredMail,registeredPassword, true);
        Assert.assertTrue(registerPage.getTakenMailErrorInfo().contains("Rejestracja za pomocą tego adresu e-mail nie jest niestety możliwa"));
    }

    @Test
    public void RegisterNoAgreementTest(){
        Random rand = new Random();
        Integer randomNum = rand.nextInt(1000);
        String mail = "ppp" + randomNum + "@ppp.pl";
        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        mainPage.openSignInPage();
        registerPage.setRegisterDataAndSubmit(mail,correctPass, false);
        Assert.assertEquals(registerPage.getAgreementErrorInfo(),"To pole jest wymagane.");
    }

}
