package views.screen.shipping;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

import common.exception.NotSupportPlaceRushException;
import controller.PlaceOrderController;
import common.exception.InvalidDeliveryInfoException;
import controller.PlaceRushOrderController;
import entity.invoice.Invoice;
import entity.order.Order;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.invoice.InvoiceScreenHandler;
import views.screen.popup.PopupScreen;

public class ShippingScreenHandler extends BaseScreenHandler implements Initializable {

  @FXML
  private Label screenTitle;

  @FXML
  private TextField name;

  @FXML
  private TextField phone;

  @FXML
  private TextField address;

  @FXML
  private TextField instructions;

  @FXML
  private ComboBox<String> province;

  @FXML
  private CheckBox placeRush;

  private Order order;

  public ShippingScreenHandler(Stage stage, String screenPath, Order order) throws IOException {
    super(stage, screenPath);
    this.order = order;
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load
    name.focusedProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue && firstTime.get()) {
        content.requestFocus(); // Delegate the focus to container
        firstTime.setValue(false); // Variable value changed for future references
      }
    });
    this.province.getItems().addAll(Configs.PROVINCES);
  }

  @FXML
  void submitDeliveryInfo(MouseEvent event) throws IOException, InterruptedException, SQLException {

    // add info to messages
    HashMap<String, String> messages = new HashMap<>();
    messages.put("name", name.getText());
    messages.put("phone", phone.getText());
    messages.put("address", address.getText());
    messages.put("instructions", instructions.getText());
    messages.put("province", province.getValue());
    try {
      // process and validate delivery info
      getBController().processDeliveryInfo(messages);
    } catch (IOException e) {
      notifyError(e.getMessage());
      throw new InvalidDeliveryInfoException(e.getMessage());
    }
    order.setDeliveryInfo(messages);
    if (placeRush.isSelected()) {
      try {
        BaseScreenHandler deliveryRushFormHandler = new DeliveryRushFormHandler(this.stage,
            Configs.DELIVERY_RUSH_FORM_PATH, order);
        deliveryRushFormHandler.setPreviousScreen(this);
        deliveryRushFormHandler.setHomeScreenHandler(homeScreenHandler);
        deliveryRushFormHandler.setScreenTitle("Delivery Rush Form");
        deliveryRushFormHandler.setBController(new PlaceRushOrderController());
        deliveryRushFormHandler.show();
      } catch (NotSupportPlaceRushException ex) {
        PopupScreen.error("Not Support Place Rush");
      }
    } else {
      // calculate shipping fees
      int shippingFees = getBController().calculateShippingFee(order);
      order.setShippingFees(shippingFees);
      // create invoice screen
      Invoice invoice = getBController().createInvoice(order);
      BaseScreenHandler InvoiceScreenHandler = new InvoiceScreenHandler(this.stage, Configs.INVOICE_SCREEN_PATH,
          invoice);
      InvoiceScreenHandler.setPreviousScreen(this);
      InvoiceScreenHandler.setHomeScreenHandler(homeScreenHandler);
      InvoiceScreenHandler.setScreenTitle("Invoice Screen");
      InvoiceScreenHandler.setBController(getBController());
      InvoiceScreenHandler.show();
    }
  }

  @Override
  public PlaceOrderController getBController() {
    return (PlaceOrderController) super.getBController();
  }

  public void notifyError(String message) throws IOException {
    // TODO: implement later on if we need
    PopupScreen.error(message);
  }
}
