package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthPage extends BasePage {

    @FindBy(id = "email_create")
    private WebElement emailCreateTextArea;
    @FindBy(id = "SubmitCreate")
    private WebElement buttonCreateAcc;

    public AuthPage(WebDriver driver) {
        super(driver);
    }

    public void createAcc(String email) {
        typeInEmail(email);
        submit();
    }

    public AccCreationPage returnAccCreationPage() {
        return PageFactory.initElements(driver, AccCreationPage.class);
    }

    private void typeInEmail(String email) {
        waitForVisibilityOf(emailCreateTextArea);
        sendKeys(emailCreateTextArea, email);
    }

    private void submit() {
        buttonCreateAcc.click();
    }

}
