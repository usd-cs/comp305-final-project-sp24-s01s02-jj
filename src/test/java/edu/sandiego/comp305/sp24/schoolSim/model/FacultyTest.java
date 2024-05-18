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

public class FacultyTest {
    private static final String CONFIG_FILENAME = "config.properties";
    private static final int SQL_YEAR_OFFSET = 1900;
    private static final int FACULTY_SPECIFIC_PARAMETER_COUNT = 2;

    private static final String PERSON_TABLE_NAME = "Person";
    private static final String EMPLOYEE_TABLE_NAME = "Employee";
    private static final String FACULTY_TABLE_NAME = "Faculty";

    private static final int VALID_FACULTY_ID = 75;
    private static final int VALID_FACULTY_OFFICE_LOCATION_ID = 1;
    private static final boolean VALID_FACULTY_HAS_TENURE = true;

    private static final String FAKE_FACULTY_FIRST_NAME = "fakefirst";
    private static final String FAKE_FACULTY_LAST_NAME = "fakelast";
    private static final Date FAKE_FACULTY_DOB = new Date(2004 - SQL_YEAR_OFFSET, 5, 6);
    private static final String FAKE_FACULTY_PHONE = "6192607059";
    private static final String FAKE_FACULTY_USERNAME = "fakefac";
    private static final String FAKE_FACULTY_ORG_EMAIL = "fakefac@sandiego.edu";
    private static final String FAKE_FACULTY_SECONDARY_EMAIL = "fakefac@gmail.com";
    private static final boolean FAKE_FACULTY_IS_ACTIVE = true;
    private static final int FAKE_FACULTY_DEPARTMENT_ID = 1;
    private static final Date FAKE_FACULTY_START_DATE = new Date(2024 - SQL_YEAR_OFFSET, 5, 4);
    private static final double FAKE_FACULTY_HOURLY_WAGE = 20.05;
    private static final Employee FAKE_FACULTY_MANAGER = null;
    private static final int FAKE_FACULTY_ROOM_ID = 1;
    private static final boolean FAKE_FACULTY_HAS_TENURE = true;

    private static final int INVALID_FACULTY_ID = -1;

    @BeforeAll
    static void beforeAll() {
        Config.initialize(CONFIG_FILENAME);
    }

    @Test
    void verifyFirstFacultyValues() {
        Faculty faculty = new Faculty(VALID_FACULTY_ID);

        assertEquals(VALID_FACULTY_OFFICE_LOCATION_ID, faculty.getOfficeLocation().getId());
        assertEquals(VALID_FACULTY_HAS_TENURE, faculty.hasTenure());
    }

    @Test
    void getInvalidFaculty() {
        assertThrows(IllegalArgumentException.class, () -> {
            Faculty faculty = new Faculty(INVALID_FACULTY_ID);
        });
    }

    @Test
    void createNewFacultyAndTestVariables() {
        Faculty faculty = new Faculty(
                FAKE_FACULTY_FIRST_NAME,
                FAKE_FACULTY_LAST_NAME,
                FAKE_FACULTY_DOB,
                FAKE_FACULTY_PHONE,
                FAKE_FACULTY_USERNAME,
                FAKE_FACULTY_ORG_EMAIL,
                FAKE_FACULTY_SECONDARY_EMAIL,
                FAKE_FACULTY_IS_ACTIVE,
                new Department(FAKE_FACULTY_DEPARTMENT_ID),
                FAKE_FACULTY_START_DATE,
                FAKE_FACULTY_HOURLY_WAGE,
                FAKE_FACULTY_MANAGER,
                new Room(FAKE_FACULTY_ROOM_ID),
                FAKE_FACULTY_HAS_TENURE
        );

        assertEquals(FAKE_FACULTY_ROOM_ID, faculty.getOfficeLocation().getId());
        assertEquals(FAKE_FACULTY_HAS_TENURE, faculty.hasTenure());

        deleteSQLEntryWithId(faculty.getId(), FACULTY_TABLE_NAME);
        deleteSQLEntryWithId(faculty.getId(), EMPLOYEE_TABLE_NAME);
        deleteSQLEntryWithId(faculty.getId(), PERSON_TABLE_NAME);
    }

    @Test
    void getStringListValues() {
        Faculty valid = new Faculty(VALID_FACULTY_ID);

        List<String> actual = valid.getStringList();
        int facultySpecificStart = actual.size() - FACULTY_SPECIFIC_PARAMETER_COUNT;

        assertEquals(valid.getOfficeLocation().toString(), actual.get(facultySpecificStart));
        assertEquals(Boolean.toString(valid.hasTenure()), actual.get(++facultySpecificStart));
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
