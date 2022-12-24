import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceOrderController;
import controller.PlaceRushOrderController;
public class ValidateAddressSupportPlaceRushOrder {
private PlaceRushOrderController placeRushOrderController;
	
	@BeforeEach	
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}
	
	@ParameterizedTest
	@CsvSource({
		"Ha Noi, true",
		"Phu Tho, false"
	})
	public void addressTest(String province, boolean expected) {
		//when
		boolean isValided = placeRushOrderController.ValidAddressSupportPlaceRushOrder(province);
		//then
		assertEquals(expected, isValided);
	}	
}
