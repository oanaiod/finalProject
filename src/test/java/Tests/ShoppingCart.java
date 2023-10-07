package Tests;

import PageObjects.LoginPage;
import PageObjects.ShoppingCartPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
    public void increaseQuantity() throws InterruptedException {
        shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.searchAppleProd();
        shoppingCartPage.increaseQuantityProd();
        Assert.assertTrue(shoppingCartPage.quantProd2());
        Reporter.log("Verify that the user  can increase the quantity of a product");
    }

    @Test(description = "Verify that the user can add multiple products to cart")
    public void addMultipleProducts() throws InterruptedException {
        shoppingCartPage = new ShoppingCartPage(driver);
        loginPage = new LoginPage(driver);
        loginPage.termsAndCond();
        shoppingCartPage.searchAppleProd();
        shoppingCartPage.addMultipleProductsToCart();
        Assert.assertTrue(shoppingCartPage.shortcutToCart());
        shoppingCartPage.goToCartByShortcut();
        shoppingCartPage.emptyCart();
        Reporter.log("Verify that the user can add multiple products to cart");
    }

    @Test(description = "Verify that the quantity is updated after removing a duplicate product")
    public void updateCart() throws InterruptedException {
        shoppingCartPage = new ShoppingCartPage(driver);
        loginPage = new LoginPage(driver);
        loginPage.termsAndCond();
        shoppingCartPage.searchAppleProd();
        shoppingCartPage.addMultipleProductsToCart();
        Assert.assertTrue(shoppingCartPage.shortcutToCart());
        shoppingCartPage.goToCartByShortcut();
        shoppingCartPage.deleteDuplicateProd();
        Assert.assertTrue(shoppingCartPage.cartAlert());
        Assert.assertTrue(shoppingCartPage.prodUpdated());
        Reporter.log("Verify that the quantity is updated after removing a duplicate product");
    }

    @Test(description = "Verify that the cart is empty after deleting all products")
    public void deleteOneProdTest() throws InterruptedException {
        shoppingCartPage = new ShoppingCartPage(driver);
        loginPage = new LoginPage(driver);
        loginPage.termsAndCond();
        shoppingCartPage.searchAppleProd();
        shoppingCartPage.addProductsToCart();
        shoppingCartPage.closeCartPopUp();
        shoppingCartPage.addMultipleProductsToCart();
        shoppingCartPage.goToCartByShortcut();
        shoppingCartPage.deleteFirstProd();
        Assert.assertTrue(shoppingCartPage.prodDeleted());
        Reporter.log("Verify that the cart is empty after deleting all products");
    }

    @Test(description = "Verify that free shipping is preselected after adding product to cart that are over 99.00 lei")
    public void freeShippingTest() throws InterruptedException {
        shoppingCartPage = new ShoppingCartPage(driver);
        loginPage = new LoginPage(driver);
        loginPage.termsAndCond();
        shoppingCartPage.searchAppleProd();
        shoppingCartPage.addProductsToCart();
        Assert.assertFalse(shoppingCartPage.freeShipping());
        shoppingCartPage.closeCartPopUp();
        shoppingCartPage.addMultipleProductsToCart();
        Assert.assertTrue(shoppingCartPage.freeShipping());
        Reporter.log("Verify that free shipping is preselected after adding product to cart that are over 99.00 lei ");
    }

}
