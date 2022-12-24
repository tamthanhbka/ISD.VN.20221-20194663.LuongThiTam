package entity.invoice;

import entity.order.Order;

/**
 * this class is ...
 * 
 * @author tamth
 */
public class Invoice {

  private Order order;
  private int amount;

  /**
   * 
   */
  public Invoice() {

  }

  /**
   * hhghv.
   * 
   * @param order
   */
  public Invoice(Order order) {
    this.order = order;
  }

  /**
   * this method is ..
   * 
   * @return order is
   */
  public Order getOrder() {
    return order;
  }

  /**
   * this method .
   * 
   * @param amount is ..
   */

  public void setAmount(int amount) {
    this.amount = amount;
  }
  
  /**
   * this method is 
   * 
   * @return amount is
   */

  public int getAmount() {
    return amount;
  }
  
  /**
   * this method.
   */

  public void saveInvoice() {

  }
}
