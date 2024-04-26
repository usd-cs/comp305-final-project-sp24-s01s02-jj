package edu.sandiego.comp305.sp24.schoolSim;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigTests {
    private static final String VALID_RESOURCE = "test-resource-file.txt";
    private static final String INVALID_RESOURCE = "doesnt-exist.txt";

    private static final String VALID_PROPERTIES = "valid.properties";
    private static final String INVALID_PROPERTIES = "invalid.properties";

    @Test
    void readFromResourceFileNull() {
        assertThrows(IllegalArgumentException.class, () -> Config.readFromResourceFile(null));
    }

    @Test
    void readFromResourceFileExists() {
        InputStream inputStream = Config.readFromResourceFile(VALID_RESOURCE);
        assertNotNull(inputStream);

        try {
            inputStream.close();
        } catch (IOException e) {
            // ignore
        }
    }

    @Test
    void readFromResourceFileDoesntExist() {
        assertThrows(IllegalArgumentException.class, () -> Config.readFromResourceFile(INVALID_RESOURCE));
    }

    @Test
    void getPropertiesFromInputStreamNull() {
        assertThrows(IllegalArgumentException.class, () -> Config.getPropertiesFromInputStream(null));
    }

    @Test
    void getPropertiesFromInputStreamValid() {
        InputStream inputStream = Config.readFromResourceFile(VALID_PROPERTIES);
        Properties properties = Config.getPropertiesFromInputStream(inputStream);

        assertNotNull(properties);
        assertEquals("true", properties.getProperty("validity"));
    }

    @Test
    void getPropertiesFromInputStreamInvalid() {
        InputStream inputStream = Config.readFromResourceFile(INVALID_PROPERTIES);
        Properties properties = Config.getPropertiesFromInputStream(inputStream);

        assertNotNull(properties);
        assertEquals(0, properties.entrySet().size());
    }

    @Test
    void setInstanceVariablesNull() {
        assertThrows(IllegalArgumentException.class, () -> Config.getInstance().setInstanceVariables(null));
    }

    @Test
    void setInstanceVariablesMissingDBName() {
        Properties properties = new Properties();
        properties.setProperty("database_username", "fake username");
        properties.setProperty("database_password", "fake password");
        properties.setProperty("database_host", "fake host");
        assertThrows(IllegalArgumentException.class, () -> Config.getInstance().setInstanceVariables(properties));
    }

    @Test
    void setInstanceVariablesMissingDBUsername() {
        Properties properties = new Properties();
        properties.setProperty("database_name", "fake name");
        properties.setProperty("database_password", "fake password");
        properties.setProperty("database_host", "fake host");
        assertThrows(IllegalArgumentException.class, () -> Config.getInstance().setInstanceVariables(properties));
    }

    @Test
    void setInstanceVariablesMissingDBPassword() {
        Properties properties = new Properties();
        properties.setProperty("database_username", "fake username");
        properties.setProperty("database_name", "fake name");
        properties.setProperty("database_host", "fake host");
        assertThrows(IllegalArgumentException.class, () -> Config.getInstance().setInstanceVariables(properties));
    }

    @Test
    void setInstanceVariablesMissingDBHost() {
        Properties properties = new Properties();
        properties.setProperty("database_username", "fake username");
        properties.setProperty("database_name", "fake name");
        properties.setProperty("database_password", "fake password");
        assertThrows(IllegalArgumentException.class, () -> Config.getInstance().setInstanceVariables(properties));
    }

    @Test
    void setInstanceVariablesAllPresent() {
        Properties properties = new Properties();
        properties.setProperty("database_username", "fake username");
        properties.setProperty("database_name", "fake name");
        properties.setProperty("database_password", "fake password");
        properties.setProperty("database_host", "fake host");

        assertDoesNotThrow(() -> Config.getInstance().setInstanceVariables(properties));
    }

    @Test
    void setInstanceVariablesEmpty() {
        Properties properties = new Properties();

        assertThrows(IllegalArgumentException.class, () -> Config.getInstance().setInstanceVariables(properties));
    }

    @Test
    void getInstanceNotMade() {
        Config config = Config.getInstance();
        assertNotNull(config);
    }
    @Test
    void getInstanceAlreadyMade() {
        Config.getInstance();
        assertNotNull(Config.getInstance());
    }

    @Test
    void getInstanceCorrectVariables() {
        assertEquals("TestName", Config.getInstance().getDatabaseName());
        assertEquals("a", Config.getInstance().getDatabaseUsername());
        assertEquals("b", Config.getInstance().getDatabasePassword());
        assertEquals("c", Config.getInstance().getDatabaseHost());
    }
}
