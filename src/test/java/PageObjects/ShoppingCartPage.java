package PageObjects;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShoppingCartPage {

        private WebDriver driver;
        private WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"header\"]/div[1]/div[2]/div/div/div/div[3]/div/div/a")
    private WebElement shoppingCart;
    @FindBy(xpath = "//*[@id=\"header\"]/div[1]/div[2]/div/div/div/div[3]/div/div/div/div/div[1]/a/span[2]")
    private WebElement shoppingCart1;
    @FindBy(xpath = "//*[@id=\"header\"]/div[1]/div[2]/div/div/div/div[2]/div/div[2]/form/div[1]/div/div[1]")
    private WebElement categories;
    @FindBy(xpath = "//*[@id=\"header\"]/div[1]/div[2]/div/div/div/div[2]/div/div[2]/form/div[1]/div/div[2]/ul/li[5]")
    private WebElement apple;
    @FindBy(xpath = "//*[@id=\"header\"]/div[1]/div[2]/div/div/div/div[2]/div/div[2]/form/div[1]/span/button")
    private WebElement searchBtn;
    @FindBy(xpath = "/html/body/div[2]/div[1]/div[1]/div[2]/div/div/div/div[2]/div[2]/div[1]/div/div[3]/div[3]/a")
    private WebElement addCartBtn;
    @FindBy(xpath = "/html/body/div[2]/div[1]/div[1]/div[3]/div/div/div/div[1]/div[2]/div[1]/form/div[1]/table/tbody/tr[1]/td[2]/div/a[2]")
    private WebElement deleteFirstProd;
    @FindBy(xpath = "/html/body/div[2]/div[1]/div[1]/div[3]/div/div/div/div[1]/div[1]/div")
    private WebElement prodDelAlert;
    @FindBy(id = "menu-item-37661")
    private WebElement closeCartPopUp;
    @FindBy(xpath = "/html/body/div[2]/div[1]/div[1]/div[3]/div/div/div/div[1]/div[2]/div[1]/form/div[2]/div[2]/a")
    private WebElement emptyCartBtn;
    @FindBy(xpath = "/html/body/div[2]/div[1]/div[1]/div[3]/div/div/div/div[1]/div[2]/div[1]/form/div[1]/table/tbody/tr/td[1]")
    private WebElement prod1InCart;
    @FindBy(xpath = "/html/body/div[2]/div[1]/div[1]/div[2]/div/div/div/div[2]/div[2]/div[2]/div/div[3]/div[3]/div/span[2]")
    private WebElement plusProduct;
    @FindBy(xpath = "/html/body/div[2]/div[1]/div[1]/div[2]/div/div/div/div[2]/div[2]/div[2]/div/div[3]/div[3]/a")
    private WebElement addCartBtn2;
    @FindBy(xpath = "//*[@id=\"header\"]/div[1]/div[2]/div/div/div/div[3]/div/div/div")
    private WebElement shortcutToCart;
    @FindBy(xpath = "/html/body/div[2]/div[1]/div[1]/div[3]/div/div/div/div[1]/div[2]/div[1]/form/div[1]/table/tbody/tr/td[5]/div/span[1]")
    private WebElement minusProd;
    @FindBy(xpath = "/html/body/div[2]/div[1]/div[1]/div[3]/div/div/div/div[1]/div[1]/div")
    private WebElement cartAlert;
    @FindBy(name = "cart[48e0ebce5727b744f207aae35ed8c768][qty]")
    private WebElement prodValue;
    @FindBy(xpath = "//*[@id=\"header\"]/div[1]/div[2]/div/div/div/div[3]/div/div/div/div/div[3]/div[2]/progress")
    private WebElement freeShipping;
    @FindBy(xpath = "/html/body/div[2]/div[1]/div[1]/div[2]/div/div/div/div[2]/div[2]/div[2]/div/div[3]/div[3]/a")
    private WebElement quantityProd2;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    //It will go the shopping cart page
    public void goToShoppingPage()
    {
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCart));
        shoppingCart.click();
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCart1));
        shoppingCart1.click();
    }

    //It will search all the apple products on store
    public void searchAppleProd() {
        wait.until(ExpectedConditions.elementToBeClickable(categories));
        categories.click();
        wait.until(ExpectedConditions.elementToBeClickable(apple));
        apple.click();
        searchBtn.click();
    }

    //It will add the first product to cart
    public void addProductsToCart()
    {
        wait.until(ExpectedConditions.elementToBeClickable(addCartBtn));
        addCartBtn.click();
    }

    //It will delete the first product from cart
    public void deleteFirstProd()
    {
        wait.until(ExpectedConditions.elementToBeClickable(deleteFirstProd));
        deleteFirstProd.click();
    }

    //It will refresh the page
    public void closeCartPopUp()
    {
        driver.navigate().refresh();

    }

    //It will erase all the products from cart
    public void emptyCart()
    {
        wait.until(ExpectedConditions.elementToBeClickable(emptyCartBtn));
        emptyCartBtn.click();
        driver.switchTo().alert().accept();
    }

    //It will add 2 products of the same type in cart
    public void addMultipleProductsToCart()
    {
        wait.until(ExpectedConditions.elementToBeClickable(plusProduct));
        plusProduct.click();
        wait.until(ExpectedConditions.elementToBeClickable(addCartBtn2));
        addCartBtn2.click();

    }

    public void increaseQuantityProd()
    {
        wait.until(ExpectedConditions.elementToBeClickable(plusProduct));
        plusProduct.click();
        wait.until(ExpectedConditions.elementToBeClickable(plusProduct));
        plusProduct.click();
    }

    //It will go to cart by accessing the shortcut cart display
    public void goToCartByShortcut()
    {
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCart1));
        shoppingCart1.click();
    }

    //It will delete one of the 2 products of the same type by pressing "-"
    public void deleteDuplicateProd()
    {
        wait.until(ExpectedConditions.elementToBeClickable(minusProd));
        minusProd.click();

    }

    //It will return true if the product is visible in the cart
    public boolean productIsInCart(){
        wait.until(ExpectedConditions.visibilityOf(prod1InCart));
        return prod1InCart.isDisplayed();
    }

    //It will return true if the shortcut cart display is visible
    public boolean shortcutToCart() {
        wait.until(ExpectedConditions.visibilityOf(shortcutToCart));
        return shortcutToCart.isDisplayed();
    }

    //It will return a true value if the alert is present
    public boolean prodDeleted() {
        wait.until(ExpectedConditions.visibilityOf(prodDelAlert));
        return prodDelAlert.isDisplayed();
    }

    //It will return true if the alert message "cart was updated" after deleting a product is visible
    public boolean cartAlert() {
        wait.until(ExpectedConditions.visibilityOf(cartAlert));
        return cartAlert.isDisplayed();
    }

    //It will return true if the quantity of the product in 3
    public boolean quantProd2() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(quantityProd2));
        Thread.sleep(8000);
        String prodVl = quantityProd2.getAttribute("data-quantity");
        int a = Integer.parseInt(prodVl);
        if(a == 3)
            return true;
        else return false;
    }

    //It will return true if the product quantity is 1
    public boolean prodUpdated() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(prodValue));
        Thread.sleep(5000);
        String prodVl = prodValue.getAttribute("value");
        int a = Integer.parseInt(prodVl);
        if(a == 1)
        return true;
        else return false;
    }

    //It will return true if the value is more or equal to 99 which means you have free shipping
    public boolean freeShipping() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(freeShipping));
        Thread.sleep(8000);
        String prodVl = freeShipping.getAttribute("value");
        int a = Integer.parseInt(prodVl);
        if(a >= 99)
        return true;
        else return false;
    }
}
