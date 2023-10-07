package PageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"header\"]/div[1]/div[1]/div/div/div/div[2]/div/div[1]/a/span/span[2]")
    private WebElement contulMeu;
//
    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(name = "login")
    private WebElement loginBtn;


    @FindBy(id = "user-name")
    private WebElement usernameInput2;

    @FindBy(id = "password")
    private WebElement passwordInput2;
    @FindBy(name = "login-button")
    private WebElement loginBtn2;
    @FindBy(xpath = "//*[@id=\"login_button_container\"]/div/form/div[3]/h3")
    private WebElement usernameErr;

    @FindBy(xpath = "//*[@id=\"header\"]/div[1]/div[1]/div/div/div/div[2]/div/div[1]/a/span/span[2]")
    private WebElement signUpButtonElement;

    @FindBy(xpath = "//*[@id=\"cmplz-cookiebanner-container\"]/div/div[6]/button[1]")
    private WebElement cookieAccept;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    //It will go to the login page (Contul meu). This is for foliidingel.ro
    public void goToLoginPage() {
        wait.until(ExpectedConditions.visibilityOf(contulMeu));
        contulMeu.click();
    }
    //It will type the provided data (user, password). This is used for the website foliidingel.ro, but you get banned if you type incorrect data
    public void login(String username, String pass) {
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput));
        usernameInput.click();
        usernameInput.sendKeys(username);
        passwordInput.click();
        passwordInput.sendKeys(pass);
        if(cookieAccept.isEnabled())
            cookieAccept.click();
        loginBtn.click();
    }

    //This will the used for login tests only
    public void login2(String username, String pass) {
        wait.until(ExpectedConditions.elementToBeClickable(usernameInput2));
        usernameInput2.click();
        usernameInput2.sendKeys(username);
        passwordInput2.click();
        passwordInput2.sendKeys(pass);
        loginBtn2.click();
    }

    //It will accept the terms and conditions popup
    public void termsAndCond() throws InterruptedException {
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(cookieAccept));
        if(cookieAccept.isEnabled())
            cookieAccept.click();
    }

    //it will check if the provided error is the same with the error I got
    public String geUsernameErr() {
        try {
            return usernameErr.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

    //It will go to the registration page
    public void goToRegistrationPage() {
        signUpButtonElement.click();
    }


}
