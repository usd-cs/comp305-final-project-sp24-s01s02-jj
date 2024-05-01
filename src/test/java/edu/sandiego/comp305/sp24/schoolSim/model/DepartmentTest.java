package edu.sandiego.comp305.sp24.schoolSim.model;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {
    private static final String CONFIG = "config.properties";
    private static final int VALID_DEPARTMENT_ID = 1;
    private static final String VALID_DEPARTMENT_NAME = "Fake Department 1";
    private static final int INVALID_DEPARTMENT_ID = 3;
    @BeforeAll
    static void beforeAll() {
        Config.initialize(CONFIG);
    }

    @Test
    void getDepartmentExistsNoError() {
        assertDoesNotThrow(() -> new Department(VALID_DEPARTMENT_ID));
    }


    @Test
    void getDepartmentExistsNotNull() {
        assertNotNull(new Department(VALID_DEPARTMENT_ID));
    }
    @Test
    void getDepartmentExistsCorrectValues() {
        Department department = new Department(VALID_DEPARTMENT_ID);
        assertEquals(VALID_DEPARTMENT_ID, department.getId());
        assertEquals(VALID_DEPARTMENT_NAME, department.getName());
    }

    @Test
    void getDepartmentDoesntExist() {
        assertThrows(IllegalArgumentException.class, () -> new Department(INVALID_DEPARTMENT_ID));
    }
}