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
    public void createAccount(AccInfo accInfo) {
        //Enter email and submit:
        authPage.fillSignUpForm(accInfo.email);

        AccCreationPage accCreationPage = authPage.getAccCreationPage();
        assertNotNull(accCreationPage);

        //Fill all requested fields and submit:
        accCreationPage.fillRequiredFields(accInfo.firstName, accInfo.lastName,
                accInfo.pass, accInfo.address, accInfo.city, accInfo.state,
                accInfo.postalCode, accInfo.mobilePhone);
        accCreationPage.submit();

        //Log out from acc:
        MyAccPage myAccPage = accCreationPage.returnMyAccPage();
        assertNotNull(myAccPage);
        myAccPage.logOut();

        //Sign in with saved email and pass:
        authPage = myAccPage.getAuthPage();
        assertNotNull(authPage);
        authPage.fillLogInForm(accInfo.email, accInfo.pass);
        myAccPage = accCreationPage.returnMyAccPage();
        assertNotNull(myAccPage);
        assertTrue(myAccPage.signOutIsDiplayed());
    }

    @DataProvider(name = "dataProvider")
    public Object[][] dpMethod() {
        AccInfo accInfo = AccInfo.builder()
                .email(emails().domain(testProperties.getProperty("domain")).get())
                .firstName(names().first().get())
                .lastName(names().last().get())
                .pass(passwords().type(MEDIUM).get())
                .address(addresses().line1().get())
                .city(cities().us().get())
                .state(testProperties.getProperty("state"))
                .postalCode(ints().rangeClosed(10000, 99999).get().toString())
                .mobilePhone("+" +
                        ints().rangeClosed(10, 99).get().toString() +
                        longs().rangeClosed(900000000, 999999999).get().toString())
                .build();
        return new Object[][]{{accInfo}};
    }

}
