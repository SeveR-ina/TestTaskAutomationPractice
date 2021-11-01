package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    @FindBy(className = "login")
    private WebElement signInLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public AuthPage getAuthPage() {
        waitForVisibilityOf(signInLink);
        signInLink.click();
        return PageFactory.initElements(driver, AuthPage.class);
    }

/*    public void signOut() {
        signOut(signOutLink);
    }*/

}
