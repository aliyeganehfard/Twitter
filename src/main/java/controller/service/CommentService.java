package controller.service;

import model.Entity.Comment;
import model.repository.CommentRepository;

import java.util.List;

public class CommentService extends BaseService<Comment, CommentRepository> {
    CommentRepository commentRepository;
    public CommentService() {
        super(new CommentRepository());
        commentRepository = new CommentRepository();
    }
    public List<Comment> findByTweetId(Integer tweetId){
        return commentRepository.findByTweetId(tweetId);
    }
}
