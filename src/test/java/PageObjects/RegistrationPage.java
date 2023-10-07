package PageObjects;

import Exceptions.CustomException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    @FindBy(id = "reg_username")
    private WebElement regUserName;

    @FindBy(id = "reg_email")
    private WebElement regEmail;

    @FindBy(id = "reg_password")
    private WebElement regPassword;

    @FindBy(name = "register")
    private WebElement register;

    @FindBy(xpath = "/html/body/div[2]/div[1]/div[1]/div[2]/div/div/div/div[1]/div[1]/ul/li/strong")
    private WebElement emailError;

    @FindBy(xpath = "//*[@id=\"cmplz-cookiebanner-container\"]/div/div[6]/button[1]")
    private WebElement cookieAccept;

    WebDriverWait wait;

    WebDriver driver;

    public RegistrationPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    //It will type the provided data (user, email, password)
    public void  regPage(String user, String email, String password) throws CustomException {
        wait.until(ExpectedConditions.elementToBeClickable(regUserName));
        regUserName.clear();
        regUserName.sendKeys(user);
        regEmail.clear();
        regEmail.sendKeys(email);
        regPassword.clear();
        regPassword.sendKeys(password);
        if(cookieAccept.isEnabled())
            cookieAccept.click();

        if(regPassword.isEnabled())
            register.click();
        else
            throw new CustomException("The password is too easy");

    }

    //it will check if the provided error is the same with the error I got
    public String emailError(){
        try {
            return emailError.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }
}