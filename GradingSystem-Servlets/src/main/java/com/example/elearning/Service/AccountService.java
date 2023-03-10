package com.example.elearning.Service;

import com.example.elearning.Model.Account;
import com.example.elearning.Repository.AccountDAO;
import com.example.elearning.Repository.IAccountDAO;

import java.sql.SQLException;
import java.util.List;

public class AccountService implements IAccountService {
    IAccountDAO accountDAO = new AccountDAO();

    @Override
    public void insertAccount(Account account) throws SQLException {
        accountDAO.insertAccount(account);
    }

    @Override
    public void deleteAccount(int id) throws SQLException {
        accountDAO.deleteAccount(id);
    }

    @Override
    public Account getAccount(int id) throws SQLException {
        return accountDAO.getAccount(id);
    }

    @Override
    public Account getAccount(String username) throws SQLException {
        return accountDAO.getAccount(username);
    }

    @Override
    public List<Account> getAllAccounts() throws SQLException {
        return accountDAO.getAllAccounts();
    }

    public Account checkUserCredentials(String username, String password) throws SQLException {
        return getAccount(username);
    }
}
