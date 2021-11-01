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
    @FindBy(id = "email")
    private WebElement emailTextArea;
    @FindBy(id = "passwd")
    private WebElement passTextArea;
    @FindBy(id = "SubmitLogin")
    private WebElement buttonLogin;
    @FindBy(id = "create_account_error")
    private WebElement alert;

    public AuthPage(WebDriver driver) {
        super(driver);
    }

    public void fillSignUpForm(String email) {
        waitForVisibilityOf(emailCreateTextArea);
        typeInField(emailCreateTextArea, email);
        submitSignUpForm();
    }

    public void fillLogInForm(String email, String pass) {
        waitForVisibilityOf(emailTextArea);
        typeInField(emailTextArea, email);
        typeInField(passTextArea, pass);
        buttonLogin.click();
    }

    public void submitSignUpForm() {
        buttonCreateAcc.click();
    }

    public AccCreationPage getAccCreationPage() {
        return PageFactory.initElements(driver, AccCreationPage.class);
    }

    private void typeInField(WebElement field, String value) {
        sendKeys(field, value);
    }

    public boolean alertContainsErrorText(String error) {
        waitForVisibilityOf(alert);
        if (alert.isDisplayed()) {
            return alert.getText().contains(error);
        }
        return false;
    }

}
