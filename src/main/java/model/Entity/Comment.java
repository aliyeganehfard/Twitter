package model.Entity;

public class Comment {
    private Integer id;
    private String comment;
    private Account account;
    private Tweet tweet;

    public Comment() {
    }

    public Comment(String comment, Account account, Tweet tweet) {
        this.comment = comment;
        this.account = account;
        this.tweet = tweet;
    }

    public Comment(Integer id, String comment, Account account, Tweet tweet) {
        this.id = id;
        this.comment = comment;
        this.account = account;
        this.tweet = tweet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", account id =" + account.getId() +
                ", tweet id =" + tweet.getId() +
                '}';
    }
}

