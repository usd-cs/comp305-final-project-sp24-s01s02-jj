package edu.sandiego.comp305.sp24.schoolSim.model;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private static final String CONFIG = "config.properties";
    private static final int VALID_EMPLOYEE_ID_NO_MANAGER = 5;
    private static final double VALID_EMPLOYEE_WAGE_NO_MANAGER = 16.85;
    private static final int VALID_EMPLOYEE_ID_WITH_MANAGER = 6;

    @BeforeAll
    static void beforeAll() {
        Config.initialize(CONFIG);
    }

    @Test
    void getEmployeeExistsNoError() {
        assertDoesNotThrow(() -> new Employee(VALID_EMPLOYEE_ID_NO_MANAGER));
    }

    @Test
    void getEmployeeExistsNotNull() {
        assertNotNull(new Employee(VALID_EMPLOYEE_ID_NO_MANAGER));
    }

    @Test
    void getEmployeeExistsCorrectValuesNoManager() {
        Employee employee = new Employee(VALID_EMPLOYEE_ID_NO_MANAGER);
        assertEquals(VALID_EMPLOYEE_ID_NO_MANAGER, employee.getId());
        assertEquals(VALID_EMPLOYEE_WAGE_NO_MANAGER, employee.getHourlyWage());
        assertEquals("2024-05-04", employee.getStartDate().toString());
        assertTrue(employee.getManager().isEmpty());
    }

    @Test
    void getEmployeeExistsCorrectValuesHasManager() {
        Employee employee = new Employee(VALID_EMPLOYEE_ID_WITH_MANAGER);

        assertTrue(employee.getManager().isPresent());
        assertEquals(VALID_EMPLOYEE_ID_NO_MANAGER, employee.getManager().get().getId());
    }
}