package model.repository;

import model.Entity.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository implements AccountInterface<Account> {
    private String query = "";

    @Override
    public Integer save(Account account) {
        Integer id = null;
        try {
            query ="insert into Twitter_Account(user_name, password, account_name) VALUES (?,?,?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,account.getUserName());
            preparedStatement.setString(2,account.getPassword());
            preparedStatement.setString(3,account.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next())
            id = resultSet.getInt(1);
            preparedStatement.close();
            return id;
        } catch (Exception e) {
            System.out.println("account save operation was failed!");
        }
        return null;
    }

    @Override
    public void update(Account account) {
        try {
            query="update Twitter_Account set user_name = ?, password =? , account_name=? where id =?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1,account.getUserName());
            preparedStatement.setString(2,account.getPassword());
            preparedStatement.setString(3,account.getName());
            preparedStatement.setInt(4,account.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            e.getStackTrace();
            System.out.println("update operation was failed!");
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            query="delete from Twitter_Account where id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            System.out.println("delete operation was failed!");
        }
    }

    @Override
    public Account findById(Integer id) {
        try {
            query="select * from Twitter_Account where id =?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return getResultSet(resultSet);
            preparedStatement.close();
        }catch (Exception e){
            e.getStackTrace();
            System.out.println("findById operation was failed!");
        }
        return null;
    }

    @Override
    public List<Account> findAll() {
        try {
            List<Account> accountList = new ArrayList<>();
            query = "select * from Twitter_Account";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                accountList.add(
                        getResultSet(resultSet)
                );
            }
            preparedStatement.close();
            return accountList;
        }catch (Exception e){
            e.getStackTrace();
            System.out.println("findAll operation was failed!");
        }
        return null;
    }

    @Override
    public Account findByName(String name) {
        try {
            query="select * from Twitter_Account where account_name =?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return getResultSet(resultSet);
            preparedStatement.close();
        }catch (Exception e){
            e.getStackTrace();
            System.out.println("findById operation was failed!");
        }
        return null;
    }

    @Override
    public Account login(String userName, String password) {
        try {
            query="select * from Twitter_Account where user_name=? and password=?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return getResultSet(resultSet);
            preparedStatement.close();
        }catch (Exception e){
            e.getStackTrace();
            System.out.println("findById operation was failed!");
        }
        return null;
    }

    @Override
    public Account getResultSet(ResultSet resultSet) {
        try {
            return new Account(
                    resultSet.getInt("id"),
                    resultSet.getString("user_name"),
                    resultSet.getString("password"),
                    resultSet.getString("account_name")
            );
        }catch (Exception e){
            System.out.println("get result set");
        }
        return null;
    }
}
