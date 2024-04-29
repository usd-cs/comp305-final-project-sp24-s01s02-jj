package edu.sandiego.comp305.sp24.schoolSim;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static Config instance;
    private String databaseName;
    private String databaseUsername;
    private String databasePassword;
    private String databaseHost;
    Config() {
        // Only for tests
    }
    private Config(String filename) {
        setInstanceVariables(getPropertiesFromInputStream(readFromResourceFile(filename)));
    }
    static InputStream readFromResourceFile(String filename) {
        if (filename == null) {
            throw new IllegalArgumentException("Null filename provided");
        }

        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classloader.getResourceAsStream(filename);
            if (inputStream == null) {
                throw new IOException("Invalid file provided");
            }

            return inputStream;
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid file provided");
        }
    }
    static Properties getPropertiesFromInputStream(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("Null InputStream provided");
        }

        try {
            Properties properties = new Properties();
            properties.load(inputStream);

            inputStream.close();

            return properties;
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid file provided");
        }

    }

     void setInstanceVariables(Properties properties) {
        if (properties == null) {
            throw new IllegalArgumentException("Null properties provided");
        }

        String[] keys = {"database_name", "database_username", "database_password", "database_host"};
        String[] values = new String[keys.length];
        for (int i = 0; i < keys.length; i++) {
            String value = properties.getProperty(keys[i]);

            if (value == null) {
                throw new IllegalArgumentException("Key " + keys[i] + " not found in properties");
            }
            values[i] = value;
        }

        databaseName = values[0];
        databaseUsername = values[1];
        databasePassword = values[2];
        databaseHost = values[3];
    }

    public static Config getInstance() {
        if (instance == null) {
            throw new NullPointerException("Please call the initialize method before getting an instance of the singleton");
        } else {
            return instance;
        }
    }

    public static Config initialize(String filename) {
        if (instance != null) {
            System.out.println("Singleton has already been initialized");
        } else {
            instance = new Config(filename);
        }

        return instance;
    }

    public String getDatabaseName() {
        return databaseName;
    }
    public String getDatabaseUsername() {
        return databaseUsername;
    }
    public String getDatabasePassword() {
        return databasePassword;
    }
    public String getDatabaseHost() {
        return databaseHost;
    }

    // Only for testing
    void reset() {
        instance = null;
    }
}
