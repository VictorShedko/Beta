package by.victor.beta.command;

public class PRGParameterManager {
    private static final String PARAMETER_DELIMITER="&";
    private static final String PARAMETER_VALUE="=";
    public String addParameter(String path,String key,String value){
        StringBuilder result=new StringBuilder(path);
        result.append(PARAMETER_DELIMITER);
        result.append(key);
        result.append(PARAMETER_VALUE);
        result.append(value);
        return result.toString();
    }
}
