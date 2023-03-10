package Repository;


import Model.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements IAccountDAO {

    private static final String INSERT_ACCOUNT_SQL = "INSERT INTO ACCOUNT" + "  (student_id,username,password) VALUES "
            + " (?,?,?)";
    private static final String SELECT_ACCOUNT_BY_ID_SQL = "select * from ACCOUNT where account_id =?";
    private static final String SELECT_ALL_ACCOUNTS_SQL = "select * from ACCOUNT";
    private static final String DELETE_ACCOUNT_SQL = "delete from ACCOUNT where account_id = ?";
    private static final String SELECT_ACCOUNT_BY_USERNAME_SQL = "select * from ACCOUNT where username =?";

    @Override
    public void insertAccount(Account account) throws SQLException {
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(INSERT_ACCOUNT_SQL);
            preparedStatement.setInt(1, account.getStudent_id());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.setString(3, account.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void deleteAccount(int id) throws SQLException {
        try {
            PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(DELETE_ACCOUNT_SQL);
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public Account getAccount(int id) throws SQLException {
        Account account = null;
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SELECT_ACCOUNT_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                account=new Account();
                account.setAccountId(resultSet.getInt("account_id"));
                account.setStudent_id(resultSet.getInt("student_id"));
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return account;
    }

    @Override
    public Account getAccount(String username) throws SQLException {
        Account account = null;
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SELECT_ACCOUNT_BY_USERNAME_SQL);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                account=new Account();
                account.setAccountId(resultSet.getInt("account_id"));
                account.setStudent_id(resultSet.getInt("student_id"));
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return account;
    }

    @Override
    public List<Account> getAllAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(SELECT_ALL_ACCOUNTS_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Account account = new Account();
                account.setAccountId(resultSet.getInt("account_id"));
                account.setStudent_id(resultSet.getInt("student_id"));
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));

                accounts.add(account);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return accounts;
    }
}
