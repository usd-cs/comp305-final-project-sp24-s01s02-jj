package edu.sandiego.comp305.sp24.schoolSim.model;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private static final String CONFIG_FILENAME = "config.properties";
    private static final int SQL_YEAR_OFFSET = 1900;
    private static final int EMPLOYEE_SPECIFIC_VARIABLE_COUNT = 3;
    private static final String NO_MANAGER_VALUE = "No Manager";

    private static final int VALID_EMPLOYEE_ID_NO_MANAGER = 5;
    private static final Date VALID_EMPLOYEE_START_DATE = new Date(2024 - SQL_YEAR_OFFSET, 4, 4);
    private static final double VALID_EMPLOYEE_WAGE_NO_MANAGER = 16.85;
    private static final int VALID_EMPLOYEE_ID_WITH_MANAGER = 6;

    @BeforeAll
    static void beforeAll() {
        Config.initialize(CONFIG_FILENAME);
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
        assertEquals(VALID_EMPLOYEE_START_DATE, employee.getStartDate());
        assertTrue(employee.getManager().isEmpty());
    }

    @Test
    void getEmployeeExistsCorrectValuesHasManager() {
        Employee employee = new Employee(VALID_EMPLOYEE_ID_WITH_MANAGER);

        assertTrue(employee.getManager().isPresent());
        assertEquals(VALID_EMPLOYEE_ID_NO_MANAGER, employee.getManager().get().getId());
    }
    @Test
    void getStringListValuesNoManager() {
        Employee valid = new Employee(VALID_EMPLOYEE_ID_NO_MANAGER);

        List<String> actual = valid.getStringList();
        int employeeSpecificStart = actual.size() - EMPLOYEE_SPECIFIC_VARIABLE_COUNT;

        assertEquals(NO_MANAGER_VALUE, actual.get(employeeSpecificStart));
        assertEquals(valid.getStartDate().toString(),actual.get(++employeeSpecificStart));
        assertEquals("$" + valid.getHourlyWage(), actual.get(++employeeSpecificStart));
    }

    @Test
    void getStringListValuesManager() {
        Employee valid = new Employee(VALID_EMPLOYEE_ID_WITH_MANAGER);

        Employee manager = null;
        if (valid.getManager().isPresent()) {
            manager = valid.getManager().get();
        }

        List<String> actual = valid.getStringList();
        int employeeSpecificStart = actual.size() - EMPLOYEE_SPECIFIC_VARIABLE_COUNT;

        assertNotNull(manager);
        assertEquals(manager.toString(), actual.get(employeeSpecificStart));
        assertEquals(valid.getStartDate().toString(),actual.get(++employeeSpecificStart));
        assertEquals("$" + valid.getHourlyWage(), actual.get(++employeeSpecificStart));
    }
}