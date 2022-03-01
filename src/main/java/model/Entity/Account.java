package model.Entity;

public class Account {
    private Integer id;
    private String userName;
    private String password;
    private String name;

    public Account() {
    }

    public Account(Integer id) {
        this.id = id;
    }

    public Account(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Account(String userName, String password, String name) {
        this.userName = userName;
        this.password = password;
        this.name = name;
    }

    public Account(Integer id, String userName, String password, String name) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
