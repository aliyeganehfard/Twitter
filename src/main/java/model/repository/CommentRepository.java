package model.repository;

import model.Entity.Account;
import model.Entity.Comment;
import model.Entity.Tweet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommentRepository implements CommentInterface {
    private String query = "";

    @Override
    public Integer save(Comment comment) {
        Integer id = null;
        try {
            query = "insert into Twitter_Comment(comment, account_id, tweet_id) VALUES (?,?,?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, comment.getComment());
            preparedStatement.setInt(2, comment.getAccount().getId());
            preparedStatement.setInt(3, comment.getTweet().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next())
            id = resultSet.getInt(1);
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("save operation was failed!");
        }
        return id;
    }

    @Override
    public void update(Comment comment) {
        try {
            query = "update Twitter_Comment set comment=? where id=?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, comment.getComment());
            preparedStatement.setInt(2, comment.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("update operation was failed!");
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            query = "delete from Twitter_Comment where id=?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println("delete operation was failed!");
        }
    }

    @Override
    public Comment findById(Integer id) {
        try {
            query="select * from Twitter_Comment where id = ?";
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
    public List<Comment> findAll() {
        try {
            List<Comment> commentList = new ArrayList<>();
            query = "select * from Twitter_Comment";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                commentList.add(
                        getResultSet(resultSet)
                );
            }
            preparedStatement.close();
            return commentList;
        }catch (Exception e){
            e.getStackTrace();
            System.out.println("findAll operation was failed!");
        }
        return null;
    }
    @Override
    public List<Comment> findByTweetId(Integer tweetId) {
        try {
            List<Comment> commentList = new ArrayList<>();
            query = "select * from Twitter_Comment where tweet_id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1,tweetId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                commentList.add(
                        getResultSet(resultSet)
                );
            }
            preparedStatement.close();
            return commentList;
        }catch (Exception e){
            e.getStackTrace();
            System.out.println("findAll operation was failed!");
        }
        return null;
    }
    @Override
    public Comment getResultSet(ResultSet resultSet) {
        try {
            return new Comment(
                    resultSet.getInt("id"),
                    resultSet.getString("comment"),
                    new Account(
                            resultSet.getInt("account_id")
                    ),
                    new Tweet(
                            resultSet.getInt("tweet_id")
                    )
            );
        } catch (Exception e) {
            System.out.println("get result set");
        }
        return null;
    }


}
