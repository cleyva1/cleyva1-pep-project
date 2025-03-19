package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService(){
        this.accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account addAccount(Account account) {
        if (accountDAO.getAccountByLogin(account.getUsername(), account.getPassword()) == account) {
            return null;
        }
        if (account.getUsername() == "") {
            return null;
        }
        if (account.getPassword().length() < 4) {
            return null;
        }
        return accountDAO.insertAccount(account);
        
    }

    public boolean loginIsSuccessful(Account account) {
        if (accountDAO.getAccountByLogin(account.getUsername(), account.getPassword()) != null){
            return true;
        } else {
            return false;
        }
    }

    public Account getAccount(int id) {
        return accountDAO.getAccountByID(id);
    }

    public Account getAccount(Account account) {
        return accountDAO.getAccountByLogin(account.getUsername(), account.getPassword());
    }


}
