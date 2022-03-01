package model.repository;

import model.Entity.Account;
import model.Entity.Tweet;
import model.utility.PostgresConnection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TweetRepositoryTest {
    private TweetRepository tweetRepository;
    private static Connection connection;
    private AccountRepository accountRepository;

    @BeforeAll
    public static void beforeAll() {
        connection = PostgresConnection.connection;
    }

    @BeforeEach
    public void beforeEach() {
        tweetRepository = new TweetRepository();
        accountRepository = new AccountRepository();
    }

    @Test
    void save() {
//        arrange
        Tweet tweet = new Tweet(null, Date.valueOf("2000-02-02"),
                accountRepository.findById(2),
                "tt", "dddd");
//        act
        tweet.setId(tweetRepository.save(tweet));
        Tweet load = tweetRepository.findById(tweet.getId());
//        assert
        assertAll(
                () -> assertEquals(tweet.getId(), load.getId()),
                () -> assertEquals(tweet.getAccount().getId(), load.getAccount().getId())
        );
    }

    @Test
    void update() {
//        arrange
        Tweet tweet = new Tweet(null, Date.valueOf("2000-02-02"),
                accountRepository.findById(2),
                "tt", "dddd");
//        act
        tweet.setId(tweetRepository.save(tweet));
        String title = "title";
        tweet.setTitle(title);
        tweetRepository.update(tweet);
        Tweet load = tweetRepository.findById(tweet.getId());
//        assert
        assertEquals(title, load.getTitle());
    }

    @Test
    void delete() {
        //        arrange
        Tweet tweet = new Tweet(null, Date.valueOf("2000-02-02"),
                accountRepository.findById(2),
                "tt", "dddd");
//        act
        tweet.setId(tweetRepository.save(tweet));
        tweetRepository.delete(tweet.getId());
//        assert
        assertNull(tweetRepository.findById(tweet.getId()));
    }

    @Test
    void findById() {
//        arrange
        Tweet tweet = new Tweet(null, Date.valueOf("2000-02-02"),
                accountRepository.findById(2),
                "tt", "dddd");
//        act
        tweet.setId(tweetRepository.save(tweet));
//        assert
        assertNotNull(tweetRepository.findById(tweet.getId()));
    }

    @Test
    void findAll() {
//        arrange
        List<Tweet> tweetList = Arrays.asList(
                new Tweet(null, Date.valueOf("2000-02-02"),
                        accountRepository.findById(2),
                        "tt", "dddd"),
                new Tweet(null, Date.valueOf("2000-02-02"),
                        accountRepository.findById(3),
                        "asdf", "sdgsdfgsd")
        );
        int size = tweetRepository.findAll().size();
        for (Tweet t : tweetList) {
            tweetRepository.save(t);
        }
        size += tweetList.size();
//        assert
        assertEquals(size,tweetRepository.findAll().size());
    }
}