package by.victor.beta.entity;

public enum  SupportedLocale {
    BE("be_BY"),EN("en_UK");
    private String localeName;

    SupportedLocale(String localeName){
        this.localeName=localeName;
    }
    public static SupportedLocale fromValue(String v) {
        for (SupportedLocale locale: SupportedLocale.values()) {
            if (locale.localeName.equals(v)) {
                return locale;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public String getLocaleName() {
        return localeName;
    }
}
