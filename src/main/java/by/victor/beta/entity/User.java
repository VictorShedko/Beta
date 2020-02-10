package by.victor.beta.entity;

import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class User implements Entity {
    private Role role;
    private String password;
    private String login;
    private long id;
    private UserStatus status;
    private long balance;
    private String email;
    private Date registrationTime;
    private String photoPath;

    public User(Role role, String password, String login, long id,
                UserStatus status, long balance, String email,
                Date registrationTime, String username) {
        this.role = role;
        this.password = password;
        this.login = login;
        this.id = id;
        this.status = status;
        this.balance = balance;
        this.email = email;
        this.registrationTime = registrationTime;
        this.username = username;
    }

    public User() {
    }


    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Order");
        builder.append("role=");
        builder.append(role);
        builder.append(", password=");
        builder.append(password);
        builder.append(", login=");
        builder.append(login);
        builder.append(", id=");
        builder.append(id);
        builder.append(", status=");
        builder.append(status);
        builder.append("  , balance=");
        builder.append(balance);
        builder.append("  , email=");
        builder.append(email);
        builder.append(", registrationTime=");
        builder.append(registrationTime);
        builder.append("  , price=");
        builder.append(photoPath);
        builder.append("  , photoPath=");
        builder.append(status);
        return builder.toString();
    }
}
