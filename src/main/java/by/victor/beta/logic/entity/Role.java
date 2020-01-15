package by.victor.beta.logic.entity;

public enum  Role {
    ADMIN("admin"),USER("user"),DEFAULT("default");
    private final String value;

    Role(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Role fromValue(String v) {
        for (Role c: Role.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
