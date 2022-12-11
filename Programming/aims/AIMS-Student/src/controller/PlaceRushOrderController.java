package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import entity.cart.Cart;
import entity.cart.CartMedia;
import common.exception.InvalidDeliveryInfoException;
import common.exception.NotSupportPlaceRushOrderException;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderMedia;
import views.screen.popup.PopupScreen;
public class PlaceRushOrderController extends BaseController{
	public void placeRushOrder(Order order) throws NotSupportPlaceRushOrderException{
		boolean supportAddr = ValidAddressSupportPlaceRushOrder(order.getDeliveryInfo().get("province"));
		if(supportAddr) {
			updateCalculateShippingFee(order);
		}else {
			throw new NotSupportPlaceRushOrderException();
		}
	}
	public void updateCalculateShippingFee(Order order) {
		int supportProduct = ValidProductSupportPlaceRushOrder(order.getlstOrderMedia());
		int fee = supportProduct*10;
		order.setPlaceRushOrderShippingFees(fee);
	}

	public int ValidProductSupportPlaceRushOrder(List lstOrderMedia) {
		// TODO Auto-generated method stub
		int count=0;
		for (Object object : lstOrderMedia) {
			OrderMedia om = (OrderMedia)object;
			if(om.getMedia().getIsSupportPlaceRushOrder()) {
				count++;
			}
		}
		return count;	
	}

	public boolean ValidAddressSupportPlaceRushOrder(Object province) {
		if(!((String)province).equals("Ha Noi")) {
			return false;
		}
		return true;
	}

}
