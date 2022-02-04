package pl.maxizoo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.maxizoo.pages.LoginPage;
import pl.maxizoo.pages.MainPage;
import pl.maxizoo.pages.MyAccountPage;
import pl.maxizoo.utils.PropertyLoader;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void LoginFromMainPageTest() throws IOException {
        String login = PropertyLoader.loadProperty("registeredMail");
        String password = PropertyLoader.loadProperty("registeredPassword");

        MainPage mainPage = new MainPage(driver);
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        mainPage.setLoginDataAndSubmit(login,password);
        Assert.assertTrue(myAccountPage.getGreetingHeaderText().contains(login));
        Assert.assertTrue(mainPage.getLoggedUserName().contains(login));
    }

    @Test
    public void LoginFromLoginPageTest() throws IOException {
        String mail = PropertyLoader.loadProperty("registeredMail");
        String password = PropertyLoader.loadProperty("registeredPassword");

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MyAccountPage myAccountPage = new MyAccountPage(driver);

        mainPage.openLoginPage();
        loginPage.LogIn(mail, password);
        Assert.assertTrue(myAccountPage.getGreetingHeaderText().contains(mail));
        Assert.assertTrue(mainPage.getLoggedUserName().contains(mail));
    }

    @Test
    public void LogoutWithDropdownButton1Test(){

    }

    @Test
    public void LogoutWithDropdownButton2Test(){

    }

    @Test
    public void LogoutFromMyAccountPageTest(){

    }

    @Test
    public void LogoutAndLoginOnTheSameAccountTest(){

    }
    @Test
    public void LogoutAndLoginOnAnotherAccountTest(){

    }
}
