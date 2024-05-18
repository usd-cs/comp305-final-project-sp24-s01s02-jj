package edu.sandiego.comp305.sp24.schoolSim.model;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.Database;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {
    private static final String CONFIG_FILENAME = "config.properties";
    private static final int VALID_DEPARTMENT_ID = 1;
    private static final String VALID_DEPARTMENT_NAME = "Fake Department 1";

    private static final String UNIQUE_DEPARTMENT_NAME = "Unique Department (TM)";

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
    void createDuplicateDepartment() {
        assertThrows(IllegalArgumentException.class, () -> {
            Department newDepartment = new Department(VALID_DEPARTMENT_NAME);
        });
    }

    @Test
    void createNewDepartmentAndTestVariables() {
        Department newDepartment = new Department(UNIQUE_DEPARTMENT_NAME);

        assertEquals(UNIQUE_DEPARTMENT_NAME, newDepartment.getName());

        // Delete alumni so test can be re-run
        deleteDepartmentWithId(newDepartment.getId());
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

    private static void deleteDepartmentWithId(long id) {
        try {
            String sql = "DELETE FROM Department WHERE `id` = ?";

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