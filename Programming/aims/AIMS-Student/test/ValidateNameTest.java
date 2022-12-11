import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PlaceOrderController;
public class ValidateNameTest {
	
	private PlaceOrderController placeOrderController;
	
	@BeforeEach
	void setUp() throws Exception {
		placeOrderController = new PlaceOrderController();
	}
	
	@ParameterizedTest
	@CsvSource({
		"Luong Thi Tam, true",
		"Luong123@$, false",
		", false",
		"\' luong thi tam\', false",
	})
	public void nameTest(String name, boolean expected) {
		//when
		boolean isValided = placeOrderController.validateName(name);
		//then
		assertEquals(expected, isValided);
	}

}
