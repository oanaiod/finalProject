package Tests;

import ObjectModel.LoginModel;
import PageObjects.LoginPage;
import Utils.Tools;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class LoginDataSourceTests extends BaseTest {
    String browser = "chrome";
    LoginPage loginPage;
////////////////////
///   JSON Use   ///
////////////////////
    @DataProvider(name = "jsonDp")
    public Iterator<Object[]> jsonDpCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
//      here we start json deserialization of json into LoginModel obj
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src\\test\\resources\\Data\\testdata.json");

        LoginModel[] lms = objectMapper.readValue(file, LoginModel[].class);

        for (LoginModel lm : lms)
            dp.add(new Object[]{lm});

        return dp.iterator();
    }

    // For Login features with json
    @Test(dataProvider = "jsonDp")
    public void loginWithJsonAsDataSource(LoginModel lm) {
        loginLm(lm);
    }
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
        ResultSet resultSet = statement.executeQuery("SELECT * FROM login");
        while (resultSet.next()) {
            LoginModel lm = new LoginModel(getEscapedElement(resultSet, "username"),
                    getEscapedElement(resultSet, "password"),
                    getEscapedElement(resultSet, "userErr"));
            dp.add(new Object[]{lm});
        }
        return dp.iterator();
    }

    // For Login features with sql
    @Test(dataProvider = "mysql")
    public void loginWithSQLAsDataSource(LoginModel lm) {
        loginLm(lm);
    }

    //   login with loginModel
    private void loginLm(LoginModel lm) {
        System.out.println(lm);
        login(lm.getAccount().getUsername(), lm.getAccount().getPassword(), lm.getUserError());
    }

    private void login(String username, String password, String usernameErr) {
        System.out.println("Login with username:" + username + "/password:" + password + "=> on browser:" + browser);
        driver.get(baseUrl2);

        loginPage = new LoginPage(driver);
        loginPage.login2(username, password);
        System.out.println("Login Finished, verify error message");
        Assert.assertEquals(loginPage.geUsernameErr(), usernameErr);
        Reporter.log("Verify that an error message is displayed when trying to login with invalid credentials");

    }

    private String getEscapedElement(ResultSet resultSet, String element) throws SQLException {
        return Tools.replaceElements(resultSet.getString(element), "''", "");
    }
}
