package com.example.elearning.Repository;

import com.example.elearning.Model.Account;

import java.sql.SQLException;
import java.util.List;

public interface IAccountDAO {
    public void insertAccount(Account account) throws SQLException;

    public void deleteAccount(int id) throws SQLException;

    public Account getAccount(int id) throws SQLException;
    public Account getAccount(String username) throws SQLException;

    public List<Account> getAllAccounts() throws SQLException;
}
