package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccCreationPage extends BasePage {
    @FindBy(id = "customer_firstname")
    private WebElement firstNameCTextArea;
    @FindBy(id = "customer_lastname")
    private WebElement lastNameCTextArea;
    @FindBy(id = "email")
    private WebElement emailTextArea;
    @FindBy(id = "passwd")
    private WebElement passTextArea;

    @FindBy(id = "firstname")
    private WebElement firstNameTextArea;
    @FindBy(id = "lastname")
    private WebElement lastNameTextArea;

    @FindBy(id = "address1")
    private WebElement address1TextArea;
    @FindBy(id = "city")
    private WebElement cityTextArea;
    @FindBy(id = "id_state")
    private WebElement stateSelect; //50 options
    @FindBy(id = "postcode")
    private WebElement postCodeTextArea;
    @FindBy(id = "id_country")
    private WebElement countrySelect;
    @FindBy(id = "phone_mobile")
    private WebElement mobPhoneTextArea;
    @FindBy(id = "alias")
    private WebElement aliasTextArea;

    @FindBy(id = "submitAccount")
    private WebElement buttonRegister;

    @FindBy(css = "div[class='alert alert-danger']")
    private WebElement alert;

    @FindBy(xpath = "//li[contains(text(), 'lastname is invalid.')]")
    //@FindBy(xpath = "//*[contains(text(), 'lastname is invalid.')]")
    //@FindBy(xpath = "//ol[contains(@tagName,'li') and contains(., 'lastname is invalid.')]")
    //@FindBy(xpath = "//ol[contains(@tagName,'li') and text()='lastname is invalid.']")
    private WebElement lastNameIsInvalidError;

    public AccCreationPage(WebDriver driver) {
        super(driver);
    }
}
