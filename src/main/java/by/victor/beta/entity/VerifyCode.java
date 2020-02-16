package by.victor.beta.entity;

import java.util.Date;

public class VerifyCode {
    private String uuidAsString;
    private String username;
    private long userId;
    private Date time;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUuidAsString() {
        return uuidAsString;
    }

    public void setUuidAsString(String uuidAsString) {
        this.uuidAsString = uuidAsString;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Verify code:");
        stringBuilder.append("uuid:");
        stringBuilder.append(uuidAsString);
        stringBuilder.append("user:");
        stringBuilder.append(username);
        stringBuilder.append("time:");
        stringBuilder.append(time);
        stringBuilder.append("id:");
        stringBuilder.append(userId);
        return stringBuilder.toString();
    }
}
