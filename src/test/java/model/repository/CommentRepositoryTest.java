package model.repository;

import model.Entity.Comment;
import model.utility.PostgresConnection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommentRepositoryTest {
    private CommentRepository commentRepository;
    private AccountRepository accountRepository;
    private TweetRepository tweetRepository;
    private static Connection connection;

    @BeforeAll
    public static void beforeAll() {
        connection = PostgresConnection.connection;
    }

    @BeforeEach
    public void beforeEach() {
        commentRepository = new CommentRepository();
        accountRepository = new AccountRepository();
        tweetRepository = new TweetRepository();
    }


    @Test
    void save() {
//        arrange
        Comment comment = new Comment(
                null,
                "hi",
                accountRepository.findById(2),
                tweetRepository.findById(5)
        );
//        act
        comment.setId(commentRepository.save(comment));
        Comment load = commentRepository.findById(comment.getId());
//        assert
        assertAll(
                ()-> assertEquals(comment.getId(),load.getId()),
                ()-> assertEquals(comment.getAccount().getId(),load.getAccount().getId())
        );

    }

    @Test
    void update() {
        //        arrange
        Comment comment = new Comment(
                null,
                "hi",
                accountRepository.findById(2),
                tweetRepository.findById(5)
        );
//        act
        comment.setId(commentRepository.save(comment));
        String cmt = "new comment";
        comment.setComment(cmt);
        commentRepository.update(comment);
        Comment load = commentRepository.findById(comment.getId());
//        assert
        assertEquals(cmt,load.getComment());
    }

    @Test
    void delete() {
        //        arrange
        Comment comment = new Comment(
                null,
                "hi",
                accountRepository.findById(2),
                tweetRepository.findById(5)
        );
//        act
        comment.setId(commentRepository.save(comment));
        commentRepository.delete(comment.getId());
//        assert
        assertNull(commentRepository.findById(comment.getId()));
    }

    @Test
    void findById() {
        //        arrange
        Comment comment = new Comment(
                null,
                "hi",
                accountRepository.findById(2),
                tweetRepository.findById(5)
        );
//        act
        comment.setId(commentRepository.save(comment));
//        assert
        assertNotNull(commentRepository.findById(comment.getId()));
    }

    @Test
    void findAll() {
//        arrange
        List<Comment> commentList = Arrays.asList(
                new Comment(
                        null,
                        "hi",
                        accountRepository.findById(2),
                        tweetRepository.findById(5)
                ),
                new Comment(
                        null,
                        "b",
                        accountRepository.findById(2),
                        tweetRepository.findById(5)
                )
        );
        int size = commentRepository.findAll().size();
        for (Comment cmt : commentList) {
            commentRepository.save(cmt);
        }
        size += commentList.size();
//        assert
        assertEquals(size,commentRepository.findAll().size());
    }
}