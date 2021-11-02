package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccCreationPage extends BasePage {
    @FindBy(id = "id_gender1")
    private WebElement mrRadioBtn;
    @FindBy(id = "id_gender2")
    private WebElement mrsRadioBtn;
    @FindBy(id = "customer_firstname")
    private WebElement firstNameCTextArea;
    @FindBy(id = "customer_lastname")
    private WebElement lastNameCTextArea;
    @FindBy(id = "passwd")
    private WebElement passTextArea;
    @FindBy(id = "days")
    private WebElement daysSelect;
    @FindBy(xpath = "//select[@id = 'days']/option[text()='1  ']")
    private WebElement firstDayOption;
    @FindBy(id = "months")
    private WebElement monthsSelect;
    @FindBy(xpath = "//select[@id = 'months']/option[text()='January ']")
    private WebElement firstMonthOption;
    @FindBy(id = "years")
    private WebElement yearsSelect;
    @FindBy(xpath = "//select[@id = 'years']/option[text()='2021  ']")
    private WebElement firstYearOption;
    @FindBy(id = "newsletter")
    private WebElement newsLetterCheckBox;
    @FindBy(id = "optin")
    private WebElement optinCheckBox;

    @FindBy(id = "company")
    private WebElement companyTextArea;
    @FindBy(id = "address1")
    private WebElement address1TextArea;
    @FindBy(id = "address2")
    private WebElement address2TextArea;
    @FindBy(id = "city")
    private WebElement cityTextArea;
    @FindBy(id = "id_state")
    private WebElement stateSelect;
    @FindBy(xpath = "//select[@id = 'id_state']/option[text()='Alabama']")
    private WebElement firstStateOption;
    @FindBy(id = "postcode")
    private WebElement postCodeTextArea;
    @FindBy(id = "other")
    private WebElement otherTextArea;
    @FindBy(id = "phone")
    private WebElement phoneTextArea;
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
        selectOption("states", state);
        sendKeys(postCodeTextArea, postalCode);
        sendKeys(mobPhoneTextArea, mobilePhone);
    }

    public void fillOptionalFields(boolean mr, String day, String month, String year,
                                   boolean newsLetter, String company, String address2,
                                   String otherInfo, String homePhone) {
        waitForVisibilityOf(mrRadioBtn);
        clickOnRadioButton(mr);
        selectOption("days", day);
        selectOption("months", month);
        selectOption("years", year);
        clickOnCheckBox(newsLetter);
        clickOnCheckBox(!newsLetter);
        sendKeys(companyTextArea, company);
        sendKeys(address2TextArea, address2);
        sendKeys(otherTextArea, otherInfo);
        sendKeys(phoneTextArea, homePhone);
    }

    public void submit() {
        buttonRegister.click();
    }

    public MyAccPage returnMyAccPage() {
        return PageFactory.initElements(driver, MyAccPage.class);
    }

    private void selectOption(String selectName, String optionText) {
        openSelector(selectName);
        switch (selectName) {
            case "states":
                clickOption("//select[@id = 'id_state']/option[text()='", optionText);
                break;
            case "days":
                clickOption("//select[@id = 'days']/option[text()='", optionText); //"1  "
                break;
            case "months":
                clickOption("//select[@id = 'months']/option[text()='", optionText); //"January "
                break;
            case "years":
                clickOption("//select[@id = 'years']/option[text()='", optionText); //"2021  "
                break;
            default:
                break;
        }
    }

    private void openSelector(String selectName) {
        WebElement whereToScroll = null;
        WebElement select = null;
        WebElement firstOption = null;
        switch (selectName) {
            case "states":
                whereToScroll = mobPhoneTextArea;
                select = stateSelect;
                firstOption = firstStateOption;
                break;
            case "days":
                whereToScroll = daysSelect;
                select = daysSelect;
                firstOption = firstDayOption;
                break;
            case "months":
                whereToScroll = monthsSelect;
                select = daysSelect;
                firstOption = firstMonthOption;
                break;
            case "years":
                whereToScroll = yearsSelect;
                select = daysSelect;
                firstOption = firstYearOption;
                break;
            default:
                break;
        }
        scrollToElement(whereToScroll);
        assert select != null;
        select.click();
        waitForVisibilityOf(firstOption);
        while (!firstOption.isDisplayed()) {
            openSelector(selectName);
        }

    }

    private void clickOption(String xpath, String optionText) {
        WebElement option = driver.findElement(By.xpath(xpath + optionText + "']"));
        option.click();
    }

    private void clickOnRadioButton(boolean mr) {
        if (mr) {
            mrRadioBtn.click();
        } else {
            mrsRadioBtn.click();
        }
    }

    private void clickOnCheckBox(boolean newsLetter) {
        if (newsLetter) {
            newsLetterCheckBox.click();
        } else {
            optinCheckBox.click();
        }
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

    public WebElement getAlert() {
        scrollToElement(formHeader);
        waitForVisibilityOf(alert);
        return alert;
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
