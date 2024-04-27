package edu.sandiego.comp305.sp24.schoolSim;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTests {
    private static final String TEST_PROPERTIES = "config.properties";
    private static final String BAD_PROPERTIES = "test.properties";

    @Test
    void getInstanceNotInitialized() {
        Config.initialize(TEST_PROPERTIES).reset();
        Config.initialize(TEST_PROPERTIES);
        Database.getInstance().reset();
        Config.getInstance().reset();

        assertThrows(NullPointerException.class, () -> Database.getInstance());
    }
    @Test
    void getInstanceInitialized() {
        Config.initialize(TEST_PROPERTIES).reset();

        Config.initialize(TEST_PROPERTIES);

        assertDoesNotThrow(() -> Database.getInstance());
    }

    @Test
    void getInstanceBadConfig() {
        Config.initialize(TEST_PROPERTIES).reset();
        Database.getInstance().reset();
        Config.initialize(BAD_PROPERTIES);

        assertThrows(IllegalStateException.class, () -> Database.getInstance());
    }

    @Test
    void getDatabaseConnectionNotNull() {
        Config.initialize(TEST_PROPERTIES).reset();
        Config.initialize(TEST_PROPERTIES);

        assertNotNull(Database.getInstance().getDatabaseConnection());
    }
}
