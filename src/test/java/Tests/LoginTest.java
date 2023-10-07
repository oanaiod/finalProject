package Tests;

import PageObjects.LoginPage;
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

        setUpDriver(browser);
        driver.get(baseUrl2);

        loginPage = new LoginPage(driver);
        loginPage.login2(username, password);

        Assert.assertEquals(loginPage.geUsernameErr(), usernameErr);


    }


}

