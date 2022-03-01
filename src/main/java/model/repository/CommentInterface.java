package model.repository;

import model.Entity.Comment;

import java.util.List;

public interface CommentInterface extends RepositoryInterface<Comment> {
    List<Comment> findByTweetId(Integer tweetId);
}
