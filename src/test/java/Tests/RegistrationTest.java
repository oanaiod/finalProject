package Tests;

import PageObjects.LoginPage;
import PageObjects.RegistrationPage;
import Exceptions.CustomException;
import TestListeners.ExtendReports.ExtentTestManager;
import com.aventstack.extentreports.Status;
import org.apache.regexp.RE;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class RegistrationTest extends BaseTest {
    LoginPage loginPage;
    RegistrationPage registrationPage;

    @DataProvider(name = "register")
    public Object[][] loginDataProvider() {
        return new Object[][]{
                {"name", "email", "password", ""},
                {"name2", "", "password2233241251251", "Eroare:"}
        };
    }
    @Test(dataProvider = "register")
    public void registerTest(String name, String email, String password, String error, Method method) throws CustomException
    {
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
        loginPage.goToRegistrationPage();
        registrationPage = new RegistrationPage(driver);
        registrationPage.RegPage(name, email, password);

//        System.out.println("Login Finished, verify error message");

        Assert.assertEquals(registrationPage.emailError(), error);
        Reporter.log("Verify that an error message is displayed when trying to register without an email");
    }

}
