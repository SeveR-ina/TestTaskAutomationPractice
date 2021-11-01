package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccPage extends BasePage {

    @FindBy(className = "logout")
    private WebElement logOutLink;

    public MyAccPage(WebDriver driver) {
        super(driver);
        waitForVisibilityOf(logOutLink);
    }

    public void logOut() {
        logOutLink.click();
    }

    public AuthPage getAuthPage() {
        return PageFactory.initElements(driver, AuthPage.class);
    }

    public boolean signOutIsDiplayed() {
        return logOutLink.isDisplayed();
    }

}
