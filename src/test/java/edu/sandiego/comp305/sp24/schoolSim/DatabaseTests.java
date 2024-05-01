package edu.sandiego.comp305.sp24.schoolSim;

import edu.sandiego.comp305.sp24.schoolSim.model.Person;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTests {
    private static final String TEST_PROPERTIES = "config.properties";
    private static final String BAD_PROPERTIES = "test.properties";

    @BeforeEach
    void beforeEach() {
        try {
            Config.initialize(TEST_PROPERTIES);
            Database.getInstance().reset();
            Config.getInstance().reset();
        } catch (IllegalStateException e) {
            // ignore
        }
    }

    @AfterAll
    static void afterAll() {
        Config.initialize(TEST_PROPERTIES).reset();
    }

    @Test
    void getInstanceNotInitialized() {
        assertThrows(IllegalStateException.class, () -> Database.getInstance());
    }
    @Test
    void getInstanceInitialized() {
        Config.initialize(TEST_PROPERTIES);

        assertDoesNotThrow(() -> Database.getInstance());
    }

    @Test
    void getInstanceBadConfig() {
        Config.initialize(BAD_PROPERTIES);

        assertThrows(IllegalStateException.class, () -> Database.getInstance());
    }

    @Test
    void getDatabaseConnectionNotNull() {
        Config.initialize(TEST_PROPERTIES);

        assertNotNull(Database.getInstance().getDatabaseConnection());
    }
}
