package Tests;

import PageObjects.LoginPage;
import PageObjects.RegistrationPage;
import PageObjects.ShoppingCartPage;
import PageObjects.HoverPage;
import Exceptions.CustomException;
import PageObjects.ShoppingCartPage;
import TestListeners.ExtendReports.ExtentTestManager;
import com.aventstack.extentreports.Status;
import org.apache.regexp.RE;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import java.util.List;

public class ShoppingCart extends BaseTest {

    LoginPage loginPage;
    ShoppingCartPage shoppingCartPage;

    @DataProvider(name = "Login")
    public Object[][] loginDataProvider() {
        return new Object[][]{
                {"Oanatst", "password123456723*"}
        };
    }

    @Test(description = "Verify that the user  can increase the quantity of a product")
    public void IncreaseQuantity() throws InterruptedException {

        driver.get(baseUrl);
        shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.SearchAppleProd();
        shoppingCartPage.IncreaseQuantityProd();
        Assert.assertTrue(shoppingCartPage.QuantProd2());
        Reporter.log("Verify that the user  can increase the quantity of a product");
    }

    @Test(description = "Verify that the user can add multiple products to cart")
    public void addMultipleProducts() throws InterruptedException {
        driver.get(baseUrl);
        shoppingCartPage = new ShoppingCartPage(driver);
        loginPage = new LoginPage(driver);
        loginPage.TermsAndCond();
        shoppingCartPage.SearchAppleProd();
        shoppingCartPage.AddMultipleProductsToCart();
        Assert.assertTrue(shoppingCartPage.ShortcutToCart());
        shoppingCartPage.GoToCartByShortcut();
        shoppingCartPage.EmptyCart();
        Reporter.log("Verify that the user can add multiple products to cart");
    }

    @Test(description = "Verify that the quantity is updated after removing a duplicate product")
    public void updateCart() throws InterruptedException {
        driver.get(baseUrl);
        shoppingCartPage = new ShoppingCartPage(driver);
        loginPage = new LoginPage(driver);
        loginPage.TermsAndCond();
        shoppingCartPage.SearchAppleProd();
        shoppingCartPage.AddMultipleProductsToCart();
        Assert.assertTrue(shoppingCartPage.ShortcutToCart());
        shoppingCartPage.GoToCartByShortcut();
        shoppingCartPage.DeleteDuplicateProd();
        Assert.assertTrue(shoppingCartPage.CartAlert());
        Assert.assertTrue(shoppingCartPage.ProdUpdated());
        Reporter.log("Verify that the quantity is updated after removing a duplicate product");
    }

    @Test(description = "Verify that the cart is empty after deleting all products")
    public void deleteOneProd() throws InterruptedException {
        driver.get(baseUrl);
        shoppingCartPage = new ShoppingCartPage(driver);
        loginPage = new LoginPage(driver);
        loginPage.TermsAndCond();
        shoppingCartPage.SearchAppleProd();
        shoppingCartPage.AddProductsToCart();
        shoppingCartPage.CloseCartPopUp();
        shoppingCartPage.AddMultipleProductsToCart();
        shoppingCartPage.GoToCartByShortcut();
        shoppingCartPage.DeleteFirstProd();
        Assert.assertTrue(shoppingCartPage.ProdDeleted());
        Reporter.log("Verify that the cart is empty after deleting all products");
    }

    @Test(description = "Verify that free shipping is preselected after adding product to cart that are over 99.00 lei")
    public void FreeShipping() throws InterruptedException {
        driver.get(baseUrl);
        shoppingCartPage = new ShoppingCartPage(driver);
        loginPage = new LoginPage(driver);
        loginPage.TermsAndCond();
        shoppingCartPage.SearchAppleProd();
        shoppingCartPage.AddProductsToCart();
        Assert.assertFalse(shoppingCartPage.FreeShipping());
        shoppingCartPage.CloseCartPopUp();
        shoppingCartPage.AddMultipleProductsToCart();
        Assert.assertTrue(shoppingCartPage.FreeShipping());
        Reporter.log("Verify that free shipping is preselected after adding product to cart that are over 99.00 lei ");
    }

}
