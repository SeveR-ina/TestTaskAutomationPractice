import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccInfo {

    protected String email;
    protected String firstName;
    protected String lastName;
    protected String pass;
    protected String address;
    protected String city;
    protected String state;
    protected String postalCode;
    protected String mobilePhone;
}
