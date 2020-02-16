package by.victor.beta.entity;

public enum SupportedImagesFormat {
    BE(".jpg"),EN(".png");
    private String extension;

    SupportedImagesFormat(String extension){
        this.extension=extension;
    }
    public static SupportedImagesFormat fromValue(String v) {
        for (SupportedImagesFormat ext: SupportedImagesFormat.values()) {
            if (ext.extension.equals(v)) {
                return ext;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public  String getExtension() {
        return extension;
    }

    public  static boolean isSupported(String v){
        for (SupportedImagesFormat ext: SupportedImagesFormat.values()) {
            if (ext.extension.equalsIgnoreCase(v)) {
                return true;
            }
        }
        return false;
    }
}
