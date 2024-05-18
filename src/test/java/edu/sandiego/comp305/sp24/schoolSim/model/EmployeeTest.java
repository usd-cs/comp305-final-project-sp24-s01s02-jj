package edu.sandiego.comp305.sp24.schoolSim.model;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.Database;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private static final String CONFIG_FILENAME = "config.properties";
    private static final int SQL_YEAR_OFFSET = 1900;
    private static final String PERSON_TABLE_NAME = "Person";
    private static final String EMPLOYEE_TABLE_NAME = "Employee";
    private static final int EMPLOYEE_SPECIFIC_VARIABLE_COUNT = 3;
    private static final String NO_MANAGER_VALUE = "No Manager";

    private static final String FAKE_EMPLOYEE_FIRST_NAME = "FAKENAME";
    private static final String FAKE_EMPLOYEE_LAST_NAME = "FAKELAST";
    private static final Date FAKE_EMPLOYEE_BIRTHDATE = new Date(2004 - SQL_YEAR_OFFSET, 4, 18);
    private static final String FAKE_EMPLOYEE_PHONE_NUMBER = "8584940009";
    private static final String UNIQUE_EMPLOYEE_USERNAME = "newemployee";
    private static final String UNIQUE_EMPLOYEE_ORG_EMAIl = "newemployee@sandiego.edu";
    private static final String UNIQUE_EMPLOYEE_SECONDARY_EMAIL = "newemployee@gmail.com";
    private static final boolean FAKE_EMPLOYEE_IS_ACTIVE = true;
    private static final int FAKE_EMPLOYEE_DEPARTMENT_ID = 1;
    private static final Date FAKE_EMPLOYEE_START_DATE = new Date(2024 - SQL_YEAR_OFFSET, 4, 18);
    private static final double FAKE_EMPLOYEE_WAGE = 35.75;

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

    @Test
    void createNewEmployeeNoManager() {
        Employee newEmployee = new Employee(
                FAKE_EMPLOYEE_FIRST_NAME,
                FAKE_EMPLOYEE_LAST_NAME,
                FAKE_EMPLOYEE_BIRTHDATE,
                FAKE_EMPLOYEE_PHONE_NUMBER,
                UNIQUE_EMPLOYEE_USERNAME,
                UNIQUE_EMPLOYEE_ORG_EMAIl,
                UNIQUE_EMPLOYEE_SECONDARY_EMAIL,
                FAKE_EMPLOYEE_IS_ACTIVE,
                new Department(FAKE_EMPLOYEE_DEPARTMENT_ID),
                FAKE_EMPLOYEE_START_DATE,
                FAKE_EMPLOYEE_WAGE,
                null
        );

        assertEquals(FAKE_EMPLOYEE_START_DATE, newEmployee.getStartDate());
        assertEquals(FAKE_EMPLOYEE_WAGE, newEmployee.getHourlyWage());
        assertFalse(newEmployee.getManager().isPresent());

        deleteSQLEntryWithId(newEmployee.getId(), PERSON_TABLE_NAME);
        deleteSQLEntryWithId(newEmployee.getId(), EMPLOYEE_TABLE_NAME);
    }

    @Test
    void createNewEmployeeWithManager() {
        Employee newEmployee = new Employee(
                FAKE_EMPLOYEE_FIRST_NAME,
                FAKE_EMPLOYEE_LAST_NAME,
                FAKE_EMPLOYEE_BIRTHDATE,
                FAKE_EMPLOYEE_PHONE_NUMBER,
                UNIQUE_EMPLOYEE_USERNAME,
                UNIQUE_EMPLOYEE_ORG_EMAIl,
                UNIQUE_EMPLOYEE_SECONDARY_EMAIL,
                FAKE_EMPLOYEE_IS_ACTIVE,
                new Department(FAKE_EMPLOYEE_DEPARTMENT_ID),
                FAKE_EMPLOYEE_START_DATE,
                FAKE_EMPLOYEE_WAGE,
                new Employee(VALID_EMPLOYEE_ID_WITH_MANAGER)
        );

        assertEquals(FAKE_EMPLOYEE_START_DATE, newEmployee.getStartDate());
        assertEquals(FAKE_EMPLOYEE_WAGE, newEmployee.getHourlyWage());
        assertTrue(newEmployee.getManager().isPresent());
        assertEquals(newEmployee.getManager().get().getId(), VALID_EMPLOYEE_ID_WITH_MANAGER);

        deleteSQLEntryWithId(newEmployee.getId(), PERSON_TABLE_NAME);
        deleteSQLEntryWithId(newEmployee.getId(), EMPLOYEE_TABLE_NAME);
    }

    private static void deleteSQLEntryWithId(long id, String tableName) {
        try {
            String sql = "DELETE FROM " + tableName + " WHERE `id` = ?";

            PreparedStatement preparedStatement = Database.getInstance().getDatabaseConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("User not deleted");
            }
        } catch (SQLException e) {
            throw new IllegalStateException("User couldn't be deleted: " + e.getMessage());
        }
    }
}