package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HoverPage {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @FindBy(xpath = "//*[@id=\"header\"]/div[1]/div[3]/div/div/div/div[1]/div/div/div[1]/span")
    private WebElement Categorii;

    @FindBy(xpath = "span.clicked")
    private WebElement selectedDropDownOptionElement;

    @FindBy(xpath = "//*[@id=\"menu-item-92064\"]/a")
    private List<WebElement> dropDownElements;

    public HoverPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }

    public boolean hoverMeButtonIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(Categorii));
        return Categorii.isDisplayed();
    }

    public List<String> getDropDownOptions() {
        List<String> dropDownOptions = new ArrayList<>();
        for (WebElement dropDownOptionElement : dropDownElements) {
            dropDownOptions.add(dropDownOptionElement.getText());
        }
        return dropDownOptions;
    }

    public void moveToButton() {
        actions.moveToElement(Categorii).perform();
    }

    public void clickDropDownOption(int dropDownOptionIndex) {
        actions.moveToElement(dropDownElements.get(dropDownOptionIndex)).click()
                .build().perform();
//        dropDownElements.get(dropDownOptionIndex).click();
    }

    public String getDropDownOption(int dropDownOptionIndex) {
        return dropDownElements.get(dropDownOptionIndex).getText();
    }

    public String getSelectedDropDownOption() {
        return selectedDropDownOptionElement.getText();
    }
}