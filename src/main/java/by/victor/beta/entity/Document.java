package by.victor.beta.entity;

import java.io.File;

public class Document implements Entity{
    private long id;
    private Long adminId;
    private long userId;
    private String username;
    private String adminName;
    private String filePath;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getFile() {
        return filePath;
    }

    public void setFile(String file) {
        this.filePath = file;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Document ");
        builder.append("id=");
        builder.append(  id);
        builder.append(", adminId=");
        builder.append(adminId);
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", username=");
        builder.append(username);
        builder.append(", adminName=");
        builder.append(adminName);
        builder.append("  , filePath=");
        builder.append(filePath);

        return builder.toString();
    }
}

