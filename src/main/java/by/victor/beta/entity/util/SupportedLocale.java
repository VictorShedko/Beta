package by.victor.beta.entity.util;

public enum  SupportedLocale {
    BE("be_BY"),EN("en_EN");
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
        return BE;
    }

    public String getLocaleName() {
        return localeName;
    }
}
