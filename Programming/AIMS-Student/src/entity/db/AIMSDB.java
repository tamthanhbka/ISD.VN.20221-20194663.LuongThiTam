package entity.db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import java.sql.Connection;
import utils.*;

/**
 * this class is ...
 * 
 * @author tamth
 */
public class AIMSDB {

  private static Logger LOGGER = Utils.getLogger(Connection.class.getName());
  private static Connection connect;

  /**
   * this method is.
   * 
   * @return connect
   */
  public static Connection getConnection() {
    if (connect != null) {
      return connect;
    }
    try {
      Class.forName("org.sqlite.JDBC");
      String url = "jdbc:sqlite:assets/db/aims.db";
      connect = DriverManager.getConnection(url);
      LOGGER.info("Connect database successfully");
    } catch (Exception e) {
      LOGGER.info(e.getMessage());
    }
    return connect;
  }

  /**
   * this method is...
   * 
   * @param args
   */
  public static void main(String[] args) {
    AIMSDB.getConnection();
  }
}
