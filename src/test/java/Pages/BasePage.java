package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

abstract public class BasePage {
    public WebDriver driver;
    private final static int DEFAULT_TIMEOUT = 10;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForVisibilityOf(WebElement webElement) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT)).until(ExpectedConditions.visibilityOf(webElement));
    }

/*    public void waitForVisibilityOfElementLocated(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForInvisibilityOfElementLocated(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }*/

    public void signOut(WebElement element) {
        element.click();
    }

    public void sendKeys(WebElement field, String text) {
        field.click();
        field.clear();
        field.sendKeys(text);
    }

    public HomePage returnHomePage(WebElement element) {
        element.click();
        return PageFactory.initElements(driver, HomePage.class);
    }

    public HomePage returnHomePage() {
        return PageFactory.initElements(driver, HomePage.class);
    }
}
