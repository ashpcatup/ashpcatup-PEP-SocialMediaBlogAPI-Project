import Model.Account;
import Util.ConnectionUtil;
import Util.ResourceCloser;

import java.sql.*;

public class AccountDAO {

  public Account getAccountByName(String username){
    Connection conn = ConnectionUtil.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try{
      String sql = "SELECT * FROM account WHERE username = ?";
      ps = conn.prepareStatement(sql);
      ps.setString(1, username);
      rs = ps.executeQuery();
      if(rs.next()){
        return new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
      }

    } catch(SQLException e){
      e.printStackTrace();
    } finally {
      ResourceCloser.closeResources(rs, ps);
    }
    return null;
  }

  public Account insertAccount(Account account){
    Connection conn = ConnectionUtil.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try{
      String sql = "INSERT INTO account(username, password) VALUES (?,?)";
      ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
      ps.setString(1, account.getUsername());
      ps.setString(2, account.getPassword());
      int result = ps.executeUpdate();
      if(result != 1) return null;

      rs = ps.getGeneratedKeys();
      if(rs.next()){
        int id = rs.getInt(1); // account id returned from database
        return new Account(id, account.getUsername(), account.getPassword());
      }
      

    } catch(SQLException e){
      e.printStackTrace();
    } finally {
      ResourceCloser.closeResources(rs, ps);
    }
    return null;
  }
 
}
