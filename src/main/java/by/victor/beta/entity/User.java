package by.victor.beta.entity;

import by.victor.beta.entity.util.Role;
import by.victor.beta.entity.util.UserStatus;

import java.util.Arrays;
import java.util.Date;


public class User implements Entity {
    private Role role;
    private byte[] password;
    private String login;
    private long id;
    private UserStatus status;
    private long balance;
    private String email;
    private Date registrationTime;
    private String photoPath;
    private byte[] salt;

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public User(Role role, byte[] password, String login, long id,
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

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (balance != user.balance) return false;
        if (role != user.role) return false;
        if (!Arrays.equals(password, user.password)) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (status != user.status) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (registrationTime != null ? !registrationTime.equals(user.registrationTime) : user.registrationTime != null)
            return false;
        if (photoPath != null ? !photoPath.equals(user.photoPath) : user.photoPath != null) return false;
        if (!Arrays.equals(salt, user.salt)) return false;
        return username != null ? username.equals(user.username) : user.username == null;
    }

    @Override
    public int hashCode() {
        int result = role != null ? role.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(password);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (int) (balance ^ (balance >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (registrationTime != null ? registrationTime.hashCode() : 0);
        result = 31 * result + (photoPath != null ? photoPath.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(salt);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}
