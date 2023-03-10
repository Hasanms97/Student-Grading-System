package Service;


import Model.Account;

import java.sql.SQLException;
import java.util.List;

public interface IAccountService {
    public void insertAccount(Account account) throws SQLException;

    public void deleteAccount(int id) throws SQLException;

    public Account getAccount(int id) throws SQLException;
    public Account getAccount(String username) throws SQLException;

    public List<Account> getAllAccounts() throws SQLException;
    public Account checkUserCredentials(String username, String password) throws SQLException;
}
