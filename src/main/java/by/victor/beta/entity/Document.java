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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (id != document.id) return false;
        if (userId != document.userId) return false;
        if (adminId != null ? !adminId.equals(document.adminId) : document.adminId != null) return false;
        if (username != null ? !username.equals(document.username) : document.username != null) return false;
        if (adminName != null ? !adminName.equals(document.adminName) : document.adminName != null) return false;
        return filePath != null ? filePath.equals(document.filePath) : document.filePath == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (adminId != null ? adminId.hashCode() : 0);
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (adminName != null ? adminName.hashCode() : 0);
        result = 31 * result + (filePath != null ? filePath.hashCode() : 0);
        return result;
    }
}

