import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceOrderController;

class ValidatePhoneNumberTest {
	
	private PlaceOrderController placeOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}
	
	@ParameterizedTest
	@CsvSource({
		"0123456789, true",
		"01234, false",
		"a123, false",
		"1234567890, false"
	})
	public void phoneNumberTest(String phone, boolean expected) {
		//when
		boolean isValided = placeOrderController.validatePhoneNumber(phone);
		//then
		assertEquals(expected, isValided);
	}

}
