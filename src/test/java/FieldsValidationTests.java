import Pages.AccCreationPage;
import Pages.AuthPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

import static net.andreinc.mockneat.unit.user.Emails.emails;
import static org.testng.Assert.assertNotNull;

public class FieldsValidationTests extends BeforeTests {
    private AuthPage authPage;
    private String email;
    String propertyInvalidEmailError;
    String propertyFirstNameIsRequired;
    String propertyLastNameIsRequired;
    String propertyEmailIsRequired;
    String propertyPassIsRequired;

    @Parameters({"browser"})
    public FieldsValidationTests(String browser) throws IOException {
        super(browser);
    }

    @Parameters({"browser"})
    @BeforeMethod
    public void openAuthPage(String browser) {
        openBrowsers(browser);
        setProperties();

        homePage = getHomePage();
        assertNotNull(homePage);

        authPage = homePage.getAuthPage();
        assertNotNull(authPage);
    }

    @AfterMethod
    public void closeApp() {
        this.quitBrowser();
    }

    @Test
    public void checkSignUpWithEmptyEmail() {
        authPage.submitSignUpForm();
        Assert.assertTrue(authPage.alertContainsErrorText(propertyInvalidEmailError));
    }

    @Test(dataProvider = "dataProvider")
    public void checkSignUpWithEmptyPersonalInfo(String email, String field) {
        //Enter email and submit:
        this.email = email;
        authPage.fillSignUpForm(this.email);

        AccCreationPage accCreationPage = authPage.getAccCreationPage();
        assertNotNull(accCreationPage);

        //Clear email and submit:
        accCreationPage.clearField(field, this.email);
        accCreationPage.submit();

        //Check that 4 fields* of the personal info are required in the alert:
        assertNotNull(accCreationPage.getAlert());
        Assert.assertTrue(accCreationPage.alertContainsErrorText(propertyFirstNameIsRequired));
        Assert.assertTrue(accCreationPage.alertContainsErrorText(propertyLastNameIsRequired));
        Assert.assertTrue(accCreationPage.alertContainsErrorText(propertyEmailIsRequired));
        Assert.assertTrue(accCreationPage.alertContainsErrorText(propertyPassIsRequired));
    }

    @DataProvider(name = "dataProvider")
    public Object[][] dpMethod() {
        this.email = emails().domain(testProperties.getProperty("domain")).get();
        return new Object[][]{{this.email, "email"}};
    }

    public void setProperties() {
        propertyInvalidEmailError = testProperties.getProperty("errorInvalidEmail");
        propertyEmailIsRequired = testProperties.getProperty("errorEmailIsRequired");
        propertyFirstNameIsRequired = testProperties.getProperty("errorFirstNameIsRequired");
        propertyLastNameIsRequired = testProperties.getProperty("errorLastNameIsRequired");
        propertyPassIsRequired = testProperties.getProperty("errorPassIsRequired");
    }
}
