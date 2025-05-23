package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;


public class AccountDAO {
    
    public Account insertAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "INSERT INTO account (username, password) VALUES (?, ?);" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //write preparedStatement's setString and setInt methods here.
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_account_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_account_id, account.getUsername(), account.getPassword());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account getAccountByLogin(String username, String password){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?;";
                
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                
            //write preparedStatement's setString and setInt methods here.
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"),
                        rs.getString("password"));
                return account;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account getAccountByID(int id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM message WHERE account_id = ?;";
                
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                
            //write preparedStatement's setString and setInt methods here.
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Account account = new Account(rs.getInt("account_id"), rs.getString("username"),
                        rs.getString("password"));
                return account;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
