package Service;

import Model.Account;
import java.util.*;
import DAO.AccountDAO;

// test save 2

public class AccountService {

  private AccountDAO accountDAO;

  public AccountService(){
    accountDAO = new AccountDAO();
  }

  public AccountService(AccountDAO accountDAO){
    this.accountDAO = accountDAO;
  }

  public Account addAccount(Account account){
    if (account.getUsername() == null || account.getUsername().isBlank()) return null;
    if (account.getPassword() == null || account.getPassword().length() < 4) return null;
    if (accountDAO.getAccountByName(account.getUsername()) != null) return null;
    return accountDAO.insertAccount(account);
  }
 
  public Account verifyAccount(Account account){
    Account savedAccount = accountDAO.getAccountByName(account.getUsername());
    if(savedAccount == null
      || account.getPassword() == null
      || !savedAccount.getPassword().equals(account.getPassword())){
      return null;
    }
    return savedAccount;
  }
}
