package Service;
import Model.Account;
import java.util.*;
import DAO.AccountDAO;

public class AccountService {

  private AccountDAO accountDAO;

  public Account addAccount(Account account){
    if (account.getUsername().isBlank() || account.getUsername() == null) return null;
    if (account.getPassword().length() < 4) return null;
    if (accountDAO.usernameExists(account.getUsername())) return null;
    return accountDAO.insertAccount(account);
  }
  
}
