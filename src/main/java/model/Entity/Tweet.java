package model.Entity;

import java.sql.Date;

public class Tweet {
    private Integer id;
    private Date date;
    private Account account;
    private String title;
    private String description;

    public Tweet() {
    }

    public Tweet(Integer id) {
        this.id = id;
    }

    public Tweet(Date date, Account account,
                 String title, String description) {
        this.date = date;
        this.account = account;
        this.title = title;
        this.description = description;
    }

    public Tweet(Integer id, Date date,
                 Account account, String title, String description) {
        this.id = id;
        this.date = date;
        this.account = account;
        this.title = title;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", date=" + date +
                ", account=" + account.getId() +
                ", title='" + title +
                ", description='" + description  +
                '}';
    }
}
