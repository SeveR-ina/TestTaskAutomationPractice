package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccCreationPage extends BasePage {
    @FindBy(id = "customer_firstname")
    private WebElement firstNameCTextArea;
    @FindBy(id = "customer_lastname")
    private WebElement lastNameCTextArea;
    @FindBy(id = "passwd")
    private WebElement passTextArea;

    @FindBy(id = "address1")
    private WebElement address1TextArea;
    @FindBy(id = "city")
    private WebElement cityTextArea;
    @FindBy(id = "id_state")
    private WebElement stateSelect; //50 options
    //@FindBy(xpath = "//select[@id = 'id_state']/option[contains(text(), 'Alabama')]")
    @FindBy(xpath = "//select[@id = 'id_state']/option[text()='Alabama']")
    private WebElement firstStateOption;
    @FindBy(id = "postcode")
    private WebElement postCodeTextArea;
    @FindBy(id = "phone_mobile")
    private WebElement mobPhoneTextArea;

    @FindBy(id = "submitAccount")
    private WebElement buttonRegister;

    @FindBy(className = "page-heading")
    private WebElement formHeader;
    @FindBy(css = "div[class='alert alert-danger']")
    private WebElement alert;

    public AccCreationPage(WebDriver driver) {
        super(driver);
    }

    public void fillRequiredFields(String fNameC, String lastNameC, String pass,
                                   String address, String city, String state,
                                   String postalCode, String mobilePhone) {
        waitForVisibilityOf(firstNameCTextArea);
        sendKeys(firstNameCTextArea, fNameC);
        sendKeys(lastNameCTextArea, lastNameC);
        sendKeys(passTextArea, pass);
        sendKeys(address1TextArea, address);
        sendKeys(cityTextArea, city);
        selectOption(state);
        sendKeys(postCodeTextArea, postalCode);
        sendKeys(mobPhoneTextArea, mobilePhone);
    }

    public void submit() {
        buttonRegister.click();
    }

    public MyAccPage returnMyAccPage() {
        return PageFactory.initElements(driver, MyAccPage.class);
    }

    private void selectOption(String state) {
        openSelector();
        clickOption(state);
    }

    private void openSelector() {
        scrollToElement(mobPhoneTextArea);
        stateSelect.click();
        waitForVisibilityOf(firstStateOption);
        while (!firstStateOption.isDisplayed()) {
            openSelector();
        }
    }

    private void clickOption(String state) {
        WebElement option = driver.findElement(By.xpath("//select[@id = 'id_state']/option[text()='" + state + "']"));
        option.click();
    }

    public void clearField(String field, String value) {
        switch (field) {
            case "email":
            case "Email":
                WebElement emailTextArea = driver.findElement(By.xpath("//input[@value='" + value + "']"));
                scrollToElement(emailTextArea);
                emailTextArea.clear();
                break;
            default:
                break;
        }
    }

    public boolean alertContainsErrorText(String error) {
        if (alert.isDisplayed()) {
            return alert.getText().contains(error);
        }
        return false;
    }

    public void viewAlert() {
        scrollToElement(formHeader);
        waitForVisibilityOf(alert);
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
