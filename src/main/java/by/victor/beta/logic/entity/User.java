package by.victor.beta.logic.entity;

import by.victor.beta.logic.command.Router;

import java.util.Objects;

public class User {
    private Role role;
    private String password;
    private String login;
    private long id;
    private UserStatus status;
    private long balance;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                balance == user.balance &&
                role == user.role &&
                Objects.equals(password, user.password) &&
                Objects.equals(login, user.login) &&
                status == user.status &&
                Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, password, login, id, status, balance, username);
    }
}
