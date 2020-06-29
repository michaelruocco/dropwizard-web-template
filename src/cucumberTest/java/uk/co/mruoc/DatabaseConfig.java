package uk.co.mruoc;

import java.util.Map;

public class DatabaseConfig {

    private final Map<String, Object> values;

    public DatabaseConfig(Map<String, Object> values) {
        this.values = values;
    }

    public String getUrl() {
        return getStringValue("url");
    }

    public String getUser() {
        return getStringValue("user");
    }

    public String getPassword() {
        return getStringValue("password");
    }

    public String getDriverClass() {
        return getStringValue("driverClass");
    }

    private String getStringValue(String key) {
        Object value = values.get(key);
        if (value == null)
            return "";
        return values.get(key).toString();
    }

}
