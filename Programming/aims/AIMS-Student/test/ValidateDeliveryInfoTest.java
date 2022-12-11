import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ ValidateAddressTest.class, ValidateNameTest.class, ValidatePhoneNumberTest.class })
public class ValidateDeliveryInfoTest {

}
