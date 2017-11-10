package com.company.database;

import com.company.mud.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountSQLiteContext implements AccountRepository {
    @Override
    public boolean createAccount(String username, String password) {
        String command = "INSERT INTO Account (Username, Password)" +
                "VALUES (?, ?)";

        try (Connection conn = SQLiteDatabase.connection()) {
            PreparedStatement statement = conn.prepareStatement(command);
            statement.setString(1, username);
            statement.setString(2, password);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean login(String username, String password) {
        String command = "SELECT Username FROM account " +
                "WHERE username = ? " +
                "AND password = ?";

        try(Connection conn = SQLiteDatabase.connection()) {
            PreparedStatement statement = conn.prepareStatement(command);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                System.out.println(rs.getString("Username"));
                if (rs.getString("Username").equals(username)){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Account getAccountByID(int id) {
        Account account = null;
        String command = "SELECT * FROM account " +
                "WHERE ID = ?";

        try(Connection conn = SQLiteDatabase.connection()) {
            PreparedStatement statement = conn.prepareStatement(command);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                account = new Account(rs.getString("Username"), null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
}
