package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    /*    @FindBy(id = "firstname")
    private WebElement firstNameTextArea;
    @FindBy(id = "lastname")
    private WebElement lastNameTextArea;
    @FindBy(id = "email")
    private WebElement emailTextArea;
    @FindBy(id = "id_country")
    private WebElement countrySelect;
    @FindBy(id = "alias")
    private WebElement aliasTextArea;
    @FindBy(css = "div[class='alert alert-danger']")
    private WebElement alert;*/
/*    @FindBy(xpath = "//li[contains(text(), 'lastname is invalid.')]")
    //@FindBy(xpath = "//*[contains(text(), 'lastname is invalid.')]")
    //@FindBy(xpath = "//ol[contains(@tagName,'li') and contains(., 'lastname is invalid.')]")
    //@FindBy(xpath = "//ol[contains(@tagName,'li') and text()='lastname is invalid.']")
    private WebElement lastNameIsInvalidError;*/

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
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", mobPhoneTextArea);
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
}
