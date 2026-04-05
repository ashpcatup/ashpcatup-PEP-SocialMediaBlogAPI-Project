package DAO;

import Model.Account;
import Util.ConnectionUtil;
import java.sql.*;

public class AccountDAO {

  public boolean usernameExists(String username){
    Connection conn = ConnectionUtil.getConnection();
    String sql = "SELECT 1 FROM account WHERE username = ?";

    try{
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setString(1, username);
      ResultSet rs = ps.executeQuery();
      return rs.next();

    } catch(SQLException e){
      e.printStackTrace();
    }
    return false;
  }

  public Account insertAccount(Account account){
    Connection conn = ConnectionUtil.getConnection();
    String sql = "INSERT INTO account(username, password) VALUES (?,?)";

    
    return account;
  }
  
}
