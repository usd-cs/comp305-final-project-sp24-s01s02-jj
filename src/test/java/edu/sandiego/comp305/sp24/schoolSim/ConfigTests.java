package edu.sandiego.comp305.sp24.schoolSim;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigTests {
    private static final String TEST_PROPERTIES = "test.properties";

    private static final String VALID_RESOURCE = "test-resource-file.txt";
    private static final String INVALID_RESOURCE = "doesnt-exist.txt";

    private static final String VALID_PROPERTIES = "valid.properties";
    private static final String INVALID_PROPERTIES = "invalid.properties";
    @AfterAll
    static void afterAll() {
        Config.getInstance().reset();
    }


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
        Config config = new Config();
        assertThrows(IllegalArgumentException.class, () -> config.setInstanceVariables(null));
    }

    @Test
    void setInstanceVariablesMissingDBName() {
        Config config = new Config();

        Properties properties = new Properties();
        properties.setProperty("database_username", "fake username");
        properties.setProperty("database_password", "fake password");
        properties.setProperty("database_host", "fake host");
        assertThrows(IllegalArgumentException.class, () -> config.setInstanceVariables(properties));
    }

    @Test
    void setInstanceVariablesMissingDBUsername() {
        Config config = new Config();

        Properties properties = new Properties();
        properties.setProperty("database_name", "fake name");
        properties.setProperty("database_password", "fake password");
        properties.setProperty("database_host", "fake host");
        assertThrows(IllegalArgumentException.class, () -> config.setInstanceVariables(properties));
    }

    @Test
    void setInstanceVariablesMissingDBPassword() {
        Config config = new Config();

        Properties properties = new Properties();
        properties.setProperty("database_username", "fake username");
        properties.setProperty("database_name", "fake name");
        properties.setProperty("database_host", "fake host");
        assertThrows(IllegalArgumentException.class, () -> config.setInstanceVariables(properties));
    }

    @Test
    void setInstanceVariablesMissingDBHost() {
        Config config = new Config();

        Properties properties = new Properties();
        properties.setProperty("database_username", "fake username");
        properties.setProperty("database_name", "fake name");
        properties.setProperty("database_password", "fake password");
        assertThrows(IllegalArgumentException.class, () -> config.setInstanceVariables(properties));
    }

    @Test
    void setInstanceVariablesAllPresent() {
        Config config = new Config();

        Properties properties = new Properties();
        properties.setProperty("database_username", "fake username");
        properties.setProperty("database_name", "fake name");
        properties.setProperty("database_password", "fake password");
        properties.setProperty("database_host", "fake host");

        assertDoesNotThrow(() -> config.setInstanceVariables(properties));
    }

    @Test
    void setInstanceVariablesEmpty() {
        Config config = new Config();

        Properties properties = new Properties();

        assertThrows(IllegalArgumentException.class, () -> config.setInstanceVariables(properties));
    }

    @Test
    void getInstanceNotInitialized() {
        Config.initialize(TEST_PROPERTIES);
        Config.getInstance().reset();

        assertThrows(NullPointerException.class, () -> Config.getInstance());
    }
    @Test
    void getInstanceInitialized() {
        Config.initialize(TEST_PROPERTIES);

        assertNotNull(Config.getInstance());
    }

    @Test
    void getInstanceCorrectVariables() {
        Config.initialize(TEST_PROPERTIES);


        assertEquals("fakename", Config.getInstance().getDatabaseName());
        assertEquals("fakeusername", Config.getInstance().getDatabaseUsername());
        assertEquals("fakepassword", Config.getInstance().getDatabasePassword());
        assertEquals("fakehost", Config.getInstance().getDatabaseHost());
    }
}
