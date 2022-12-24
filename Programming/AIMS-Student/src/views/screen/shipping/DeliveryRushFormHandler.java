package views.screen.shipping;

import common.exception.NotSupportPlaceRushException;
import controller.BaseController;
import controller.PlaceOrderController;
import controller.PlaceRushOrderController;
import entity.invoice.Invoice;
import entity.order.Order;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.invoice.InvoiceScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/*
*@author Luong Thi Tam
*@Dec 24, 2022
*@AIMS-Student
*@teacher's name : Nguyen Thi Thu Trang
*@TA's name : Nguyen Thi Thu Giang
*@ISD.VN.20221
*/
public class DeliveryRushFormHandler extends BaseScreenHandler {
  private Order order;

  public DeliveryRushFormHandler(Stage stage, String screenPath, Order order) throws IOException {
    super(stage, screenPath);
    this.order = order;
  }

  @Override
  public PlaceRushOrderController getBController() {
    return (PlaceRushOrderController) super.getBController();
  }

  @FXML
  public void submitForm() throws IOException {
    getBController().placeRushOrder(order);
    PlaceOrderController ctrl = (PlaceOrderController) this.getPreviousScreen().getBController();
    int shippingFees = ctrl.calculateShippingFee(order);
    order.setShippingFees(shippingFees);
    // create invoice screen
    Invoice invoice = ctrl.createInvoice(order);
    BaseScreenHandler InvoiceScreenHandler = new InvoiceScreenHandler(this.stage, Configs.INVOICE_SCREEN_PATH, invoice);
    InvoiceScreenHandler.setPreviousScreen(this);
    InvoiceScreenHandler.setHomeScreenHandler(homeScreenHandler);
    InvoiceScreenHandler.setScreenTitle("Invoice Screen");
    InvoiceScreenHandler.setBController(ctrl);
    InvoiceScreenHandler.show();
  }

  @FXML
  public void onCancel() {
    this.getPreviousScreen().show();
  }

  @Override
  public void show() {
    if (!getBController().ValidAddressSupportPlaceRushOrder(order.getDeliveryInfo().get("province"))) {
      this.getPreviousScreen().show();
      throw new NotSupportPlaceRushException("Not Support Place Rush");
    }
    super.show();
  }

}
