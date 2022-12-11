import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceOrderController;
public class ValidateAddressTest {
	
private PlaceOrderController placeOrderController;
	
	@BeforeEach	
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}
	
	@ParameterizedTest
	@CsvSource({
		"\'20 Tran Dai Nghia, Hai Ba Trung\', true",
		"@13 bach mai, false",
		", false",
		"\' 20 giai phong\', false",
	})
	public void addressTest(String name, boolean expected) {
		//when
		boolean isValided = placeOrderController.validateAddress(name);
		//then
		assertEquals(expected, isValided);
	}	

}
