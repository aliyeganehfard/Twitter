package model.repository;

import model.Entity.Account;
import model.Entity.Tweet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TweetRepository implements RepositoryInterface<Tweet> {
    private String query ="";
    @Override
    public Integer save(Tweet tweet) {
        Integer id = 0 ;
        try {
            query ="insert into Twitter_Tweet(date, description, title, account_id) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1,tweet.getDate());
            preparedStatement.setString(2,tweet.getDescription());
            preparedStatement.setString(3,tweet.getTitle());
            preparedStatement.setInt(4,tweet.getAccount().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("save operation was failed!");
        }
        return id;
    }

    @Override
    public void update(Tweet tweet) {
        try {
            query="update Twitter_Tweet set description=? , title=? where id =?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1,tweet.getDescription());
            preparedStatement.setString(2,tweet.getTitle());
            preparedStatement.setInt(3,tweet.getId());
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
            query="delete from Twitter_Tweet where id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            System.out.println("delete operation was failed!");
        }
    }

    @Override
    public Tweet findById(Integer id) {
        try {
            query="select * from Twitter_Tweet where id=?";
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
    public List<Tweet> findAll() {
        try {
            List<Tweet> tweetList = new ArrayList<>();
            query = "select * from Twitter_Tweet";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                tweetList.add(
                        getResultSet(resultSet)
                );
            }
            preparedStatement.close();
            return tweetList;
        }catch (Exception e){
            e.getStackTrace();
            System.out.println("findAll operation was failed!");
        }
        return null;
    }

    @Override
    public Tweet getResultSet(ResultSet resultSet) {
        try {
            return new Tweet(
                    resultSet.getInt("id"),
                    resultSet.getDate("date"),
                    new Account(
                            resultSet.getInt("account_id")
                    ),
                    resultSet.getString("title"),
                    resultSet.getString("description")
            );
        }catch (Exception e){
            System.out.println("get result set");
        }
        return null;
    }
}
