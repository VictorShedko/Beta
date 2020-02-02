package by.victor.beta.entity;


import java.util.Date;
import java.util.List;

public class Notify implements Entity {
    private List<String> values;
    private String valuesAsString;
    private Date date;
    private long id;
    private long userId;
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

    public void setId(int id) {
        this.id = id;
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
}
