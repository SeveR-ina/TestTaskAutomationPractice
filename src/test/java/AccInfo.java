import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccInfo {

    protected boolean mr;
    protected String email;
    protected String firstName;
    protected String lastName;
    protected String pass;
    protected String day;
    protected String month;
    protected String year;
    protected boolean newsLetter;
    protected String company;
    protected String address1;
    protected String address2;
    protected String city;
    protected String state;
    protected String postalCode;
    protected String otherInfo;
    protected String homePhone;
    protected String mobilePhone;
}
