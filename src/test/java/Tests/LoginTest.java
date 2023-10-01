package Tests;

import PageObjects.LoginPage;
//import TestListeners.ExtendReports.ExtentTestManager;
import TestListeners.ExtendReports.ExtentTestManager;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @DataProvider(name = "loginDp")
    public Object[][] loginDataProvider() {
        return new Object[][]{
                {"user", "passs", "chrome", "Epic sadface: Username and password do not match any user in this service", ""},
                {"standard_user", "secret_sauce", "chrome", "", ""}
        };
    }

    @Test(dataProvider = "loginDp")
    public void login(String username, String password, String browser, String usernameErr, String passErr, Method method) {
//        ExtentTestManager.startTest(method.getName() + "(user:'" + username + "',pass:'" + password + "')", "");

//        System.out.println("Login with username:" + username + "/password:" + password + "=> on browser:" + browser);
//        ExtentTestManager.getTest().log(Status.INFO, "Login with username:" + username + "/password:" + password + "=> on browser:" + browser);

        setUpDriver(browser);
        driver.get(baseUrl2);

        loginPage = new LoginPage(driver);
        loginPage.login2(username, password);

//        System.out.println("Login Finished, verify error message");
//        ExtentTestManager.getTest().log(Status.INFO, "Login Finished, verify error message");
////                          actual results ,         expected results
//        ExtentTestManager.getTest().log(Status.INFO, "Verify username message are same: actual results:" + loginPage.geUsernameErr() +
//                " VS expected results:" + usernameErr);
        Assert.assertEquals(loginPage.geUsernameErr(), usernameErr);


    }


}

