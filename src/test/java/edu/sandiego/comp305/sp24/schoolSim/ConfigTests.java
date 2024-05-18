package edu.sandiego.comp305.sp24.schoolSim;

import org.junit.jupiter.api.AfterEach;
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

    private static final String DATABASE_NAME_KEY = "database_name";
    private static final String DATABASE_USERNAME_KEY = "database_username";
    private static final String DATABASE_PASSWORD_KEY = "database_password";
    private static final String DATABASE_HOST_KEY = "database_host";

    private static final String FAKE_DATABASE_NAME_VALUE = "fake name";
    private static final String FAKE_DATABASE_USERNAME_VALUE = "fake username";
    private static final String FAKE_DATABASE_PASSWORD_VALUE = "fake password";
    private static final String FAKE_DATABASE_HOST_VALUE = "fake host";

    private static final String VALID_DATABASE_NAME_VALUE = "fakename";
    private static final String VALID_DATABASE_USERNAME_VALUE = "fakeusername";
    private static final String VALID_DATABASE_PASSWORD_VALUE = "fakepassword";
    private static final String VALID_DATABASE_HOST_VALUE = "fakehost";

    private static final String VALID_PROPERTIES_KEY = "validity";
    private static final String VALID_PROPERTIES_VALUE = "true";

    @AfterEach
    void afterEach() {
        Config.initialize(TEST_PROPERTIES).reset();
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
        assertEquals(VALID_PROPERTIES_VALUE, properties.getProperty(VALID_PROPERTIES_KEY));
    }

    @Test
    void getPropertiesFromInputStreamInvalid() {
        InputStream inputStream = Config.readFromResourceFile(INVALID_PROPERTIES);
        Properties properties = Config.getPropertiesFromInputStream(inputStream);

        assertNotNull(properties);
        assertTrue(properties.entrySet().isEmpty());
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
        properties.setProperty(DATABASE_USERNAME_KEY, FAKE_DATABASE_USERNAME_VALUE);
        properties.setProperty(DATABASE_PASSWORD_KEY, FAKE_DATABASE_PASSWORD_VALUE);
        properties.setProperty(DATABASE_HOST_KEY, FAKE_DATABASE_HOST_VALUE);
        assertThrows(IllegalArgumentException.class, () -> config.setInstanceVariables(properties));
    }

    @Test
    void setInstanceVariablesMissingDBUsername() {
        Config config = new Config();

        Properties properties = new Properties();
        properties.setProperty(DATABASE_NAME_KEY, FAKE_DATABASE_NAME_VALUE);
        properties.setProperty(DATABASE_PASSWORD_KEY, FAKE_DATABASE_PASSWORD_VALUE);
        properties.setProperty(DATABASE_HOST_KEY, FAKE_DATABASE_HOST_VALUE);
        assertThrows(IllegalArgumentException.class, () -> config.setInstanceVariables(properties));
    }

    @Test
    void setInstanceVariablesMissingDBPassword() {
        Config config = new Config();

        Properties properties = new Properties();
        properties.setProperty(DATABASE_USERNAME_KEY, FAKE_DATABASE_HOST_VALUE);
        properties.setProperty(DATABASE_NAME_KEY, FAKE_DATABASE_NAME_VALUE);
        properties.setProperty(DATABASE_HOST_KEY, FAKE_DATABASE_HOST_VALUE);
        assertThrows(IllegalArgumentException.class, () -> config.setInstanceVariables(properties));
    }

    @Test
    void setInstanceVariablesMissingDBHost() {
        Config config = new Config();

        Properties properties = new Properties();
        properties.setProperty(DATABASE_USERNAME_KEY, FAKE_DATABASE_USERNAME_VALUE);
        properties.setProperty(DATABASE_NAME_KEY, FAKE_DATABASE_NAME_VALUE);
        properties.setProperty(DATABASE_PASSWORD_KEY, FAKE_DATABASE_PASSWORD_VALUE);
        assertThrows(IllegalArgumentException.class, () -> config.setInstanceVariables(properties));
    }

    @Test
    void setInstanceVariablesAllPresent() {
        Config config = new Config();

        Properties properties = new Properties();
        properties.setProperty(DATABASE_USERNAME_KEY, FAKE_DATABASE_USERNAME_VALUE);
        properties.setProperty(DATABASE_NAME_KEY, FAKE_DATABASE_NAME_VALUE);
        properties.setProperty(DATABASE_PASSWORD_KEY, FAKE_DATABASE_PASSWORD_VALUE);
        properties.setProperty(DATABASE_HOST_KEY, FAKE_DATABASE_HOST_VALUE);

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

        assertEquals(VALID_DATABASE_NAME_VALUE, Config.getInstance().getDatabaseName());
        assertEquals(VALID_DATABASE_USERNAME_VALUE, Config.getInstance().getDatabaseUsername());
        assertEquals(VALID_DATABASE_PASSWORD_VALUE, Config.getInstance().getDatabasePassword());
        assertEquals(VALID_DATABASE_HOST_VALUE, Config.getInstance().getDatabaseHost());
    }
}
