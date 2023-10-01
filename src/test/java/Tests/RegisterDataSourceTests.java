package Tests;

import Exceptions.CustomException;
import ObjectModel.LoginModel;
import ObjectModel.RegisterModel;
import PageObjects.LoginPage;
import PageObjects.RegistrationPage;
import TestListeners.ExtendReports.ExtentTestManager;
import Utils.Tools;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
public class RegisterDataSourceTests extends BaseTest{
    String browser = "chrome";
    LoginPage loginPage;

    RegistrationPage registrationPage;
///////////////////
///   SQL Use   ///
///////////////////
    @DataProvider(name = "mysql")
    public Iterator<Object[]> mysqlDpCollection() throws Exception {
//        show DB connection details
        System.out.println("Use dbHostname:" + dbHostname);
        System.out.println("Use dbUser:" + dbUser);
        System.out.println("Use dbPort:" + dbPort);
        System.out.println("Use dbSchema:" + dbSchema);
        Collection<Object[]> dp = new ArrayList<>();
//        db connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://" + dbHostname + ":" + dbPort +
                "/" + dbSchema, dbUser, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM register");
        while (resultSet.next()) {
            RegisterModel rm = new RegisterModel(getEscapedElement(resultSet, "name"),
                    getEscapedElement(resultSet, "email"),
                    getEscapedElement(resultSet, "password"),
                    getEscapedElement(resultSet, "error"));
            dp.add(new Object[]{rm});
        }
        return dp.iterator();
    }

    // For Login features with sql
    @Test(dataProvider = "mysql")
    public void loginWithSQLAsDataSource(RegisterModel rm) throws CustomException {
        Registrationlm(rm);
    }

    //   login with loginModel
    private void Registrationlm(RegisterModel rm) throws CustomException {
        System.out.println(rm);
        registerTest(rm.getAccount().getUsername(), rm.getAccount().getEmail(), rm.getAccount().getPassword(), rm.getError());
    }

    public void registerTest(String name, String email, String password, String error) throws CustomException
    {
       System.out.println("Login with username:" + name + "/password:" + password);

        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
        loginPage.goToRegistrationPage();
        registrationPage = new RegistrationPage(driver);
        registrationPage.RegPage(name, email, password);

        System.out.println("Login Finished, verify error message");
        Assert.assertEquals(registrationPage.emailError(), error);
        Reporter.log("Verify that an error message is displayed when trying to register without an email");
    }


    private String getEscapedElement(ResultSet resultSet, String element) throws SQLException {
        return Tools.replaceElements(resultSet.getString(element), "''", "");
    }
}
