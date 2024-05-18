package edu.sandiego.comp305.sp24.schoolSim.model;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {
    private static final String CONFIG_FILENAME = "config.properties";
    private static final int VALID_DEPARTMENT_ID = 1;
    private static final String VALID_DEPARTMENT_NAME = "Fake Department 1";
    private static final int INVALID_DEPARTMENT_ID = -1;

    @BeforeAll
    static void beforeAll() {
        Config.initialize(CONFIG_FILENAME);
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

    @Test
    void getStringListValues() {
        Department department = new Department(VALID_DEPARTMENT_ID);
        List<String> actual = department.getStringList();

        List<String> expected = new ArrayList<>();
        expected.add(Long.toString(VALID_DEPARTMENT_ID));
        expected.add(department.getName());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}