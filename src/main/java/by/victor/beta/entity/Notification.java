package by.victor.beta.entity;


import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Notification implements Entity {
    private List<String> values;
    private String valuesAsString;
    private Date date;
    private Long id;
    private Long userId;
    private String username;
    private NotifyType type;

    public NotifyType getType() {
        return type;
    }

    public void setType(NotifyType type) {
        this.type = type;
    }

    public String getValuesAsString() {
        return valuesAsString;
    }

    public void setValuesAsString(String valuesAsString) {
        this.valuesAsString = valuesAsString;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification notification = (Notification) o;
        return id == notification.id &&
                userId == notification.userId &&
                Objects.equals(values, notification.values) &&
                Objects.equals(valuesAsString, notification.valuesAsString) &&
                Objects.equals(date, notification.date) &&
                Objects.equals(username, notification.username) &&
                type == notification.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(values, valuesAsString, date, id, userId, username, type);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Notify");
        builder.append("values=");
        builder.append(values);
        builder.append(", valuesAsString=");
        builder.append(valuesAsString);
        builder.append(", date=");
        builder.append(date);
        builder.append(", id=");
        builder.append(id);
        builder.append(", userId=");
        builder.append(userId);
        builder.append("  , username=");
        builder.append(username);
        builder.append("  , type=");
        builder.append(type);
        return builder.toString();
    }
}