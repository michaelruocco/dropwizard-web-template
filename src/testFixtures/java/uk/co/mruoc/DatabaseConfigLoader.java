package uk.co.mruoc;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class DatabaseConfigLoader {

    public DatabaseConfig loadClasspathDatabaseConfig(String path) {
        try {
            Yaml yaml = new Yaml();
            try (InputStream stream = getClass().getResourceAsStream(path)) {
                Map<String, Object> configValues = (Map<String,Object>) yaml.load(stream);
                Map<String, Object> databaseConfigValues = (Map<String,Object>) configValues.get("database");
                return new DatabaseConfig(databaseConfigValues);
            }
        } catch (IOException e) {
            throw new DatabaseConfigLoadException(e);
        }
    }

    public DatabaseConfig loadFileSystemDatabaseConfig(String path) {
        try {
            File file = new File(path);
            try (InputStream stream = new FileInputStream(file)) {
                return loadDatabaseConfig(stream);
            }
        } catch (IOException e) {
            throw new DatabaseConfigLoadException(e);
        }
    }

    private DatabaseConfig loadDatabaseConfig(InputStream stream) {
        Yaml yaml = new Yaml();
        Map<String, Object> configValues = (Map<String,Object>) yaml.load(stream);
        Map<String, Object> databaseConfigValues = (Map<String,Object>) configValues.get("database");
        return new DatabaseConfig(databaseConfigValues);
    }

    public static class DatabaseConfigLoadException extends RuntimeException {

        public DatabaseConfigLoadException(Throwable cause) {
            super(cause);
        }

    }

}
