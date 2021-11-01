import Pages.AccCreationPage;
import Pages.AuthPage;
import Pages.MyAccPage;
import org.testng.annotations.*;

import java.io.IOException;

import static net.andreinc.mockneat.types.enums.PassStrengthType.MEDIUM;
import static net.andreinc.mockneat.unit.address.Addresses.addresses;
import static net.andreinc.mockneat.unit.address.Cities.cities;
import static net.andreinc.mockneat.unit.types.Ints.ints;
import static net.andreinc.mockneat.unit.types.Longs.longs;
import static net.andreinc.mockneat.unit.user.Emails.emails;
import static net.andreinc.mockneat.unit.user.Names.names;
import static net.andreinc.mockneat.unit.user.Passwords.passwords;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class AccountCreationTests extends BeforeTests {
    private AuthPage authPage;
    private String email;
    private String firstName;
    private String lastName;
    private String pass;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String mobilePhone;

    @Parameters({"browser"})
    public AccountCreationTests(String browser) throws IOException {
        super(browser);
    }

    @Parameters({"browser"})
    @BeforeMethod
    public void openAuthPage(String browser) {
        openBrowsers(browser);

        homePage = getHomePage();
        assertNotNull(homePage);

        authPage = homePage.getAuthPage();
        assertNotNull(authPage);
    }

    @AfterMethod
    public void closeApp() {
        this.quitBrowser();
    }

    @Test(dataProvider = "dataProvider")
    public void createAccount(String email, String firstName, String lastName,
                              String pass, String address, String city,
                              String state, String postalCode, String mobilePhone) {
        //Enter email and submit:
        this.email = email;
        authPage.fillSignUpForm(this.email);

        AccCreationPage accCreationPage = authPage.getAccCreationPage();
        assertNotNull(accCreationPage);

        //Fill all requested fields and submit:
        this.firstName = firstName;
        this.lastName = lastName;
        this.pass = pass;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.mobilePhone = mobilePhone;
        accCreationPage.fillRequiredFields(this.firstName, this.lastName,
                this.pass, this.address, this.city, this.state,
                this.postalCode, this.mobilePhone);
        accCreationPage.submit();

        //Log out from acc:
        MyAccPage myAccPage = accCreationPage.returnMyAccPage();
        assertNotNull(myAccPage);
        myAccPage.logOut();

        //Sign in with saved email and pass:
        authPage = myAccPage.getAuthPage();
        assertNotNull(authPage);
        authPage.fillLogInForm(this.email, this.pass);
        myAccPage = accCreationPage.returnMyAccPage();
        assertNotNull(myAccPage);
        assertTrue(myAccPage.signOutIsDiplayed());
    }

    @DataProvider(name = "dataProvider")
    public Object[][] dpMethod() {
        this.email = emails().domain(testProperties.getProperty("domain")).get();
        this.firstName = names().first().get();
        this.lastName = names().last().get();
        this.pass = passwords().type(MEDIUM).get();
        this.address = addresses().line1().get();
        this.city = cities().us().get();
        this.state = testProperties.getProperty("state");
        this.postalCode = ints().rangeClosed(10000, 99999).get().toString();
        this.mobilePhone = longs().rangeClosed(900000000, 999999999)
                .get().toString();
        return new Object[][]{{this.email, this.firstName, this.lastName,
                this.pass, this.address, this.city,
                this.state, this.postalCode, this.mobilePhone}};
    }

}
