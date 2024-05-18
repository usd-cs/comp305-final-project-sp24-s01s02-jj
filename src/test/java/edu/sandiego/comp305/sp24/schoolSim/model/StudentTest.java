package edu.sandiego.comp305.sp24.schoolSim.model;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.Database;
import edu.sandiego.comp305.sp24.schoolSim.enums.Grade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private static final String CONFIG_FILENAME = "config.properties";
    private static final int SQL_DATE_OFFSET = 1900;
    private static final String STUDENT_TABLE_NAME = "Student";
    private static final String PERSON_TABLE_NAME = "Person";
    private static final int STUDENT_SPECIFIC_PARAMETERS = 2;

    private static final int VALID_STUDENT_ID = 55;
    private static final String VALID_STUDENT_USERNAME = "student";
    private static final String VALID_STUDENT_MAJOR = "Computer Science";
    private static final Grade VALID_STUDENT_GRADE = Grade.SOPHOMORE;

    private static final String FAKE_STUDENT_FIRST_NAME = "FAKENAME";
    private static final String FAKE_STUDENT_LAST_NAME = "FAKELAST";
    private static final Date FAKE_STUDENT_BIRTHDATE = new Date(2004 - SQL_DATE_OFFSET, 4, 18);
    private static final String FAKE_STUDENT_PHONE_NUMBER = "8584940009";
    private static final String UNIQUE_STUDENT_USERNAME = "uniquestudent";
    private static final String UNIQUE_STUDENT_ORG_EMAIl = "uniquestudent@sandiego.edu";
    private static final String UNIQUE_STUDENT_SECONDARY_EMAIL = "uniquestudent@gmail.com";
    private static final boolean FAKE_STUDENT_IS_ACTIVE = true;
    private static final int FAKE_STUDENT_DEPARTMENT_ID = 1;
    private static final String FAKE_STUDENT_MAJOR = "Mathematics";
    private static final Grade FAKE_STUDENT_GRADE = Grade.FRESHMAN;

    private static final int INVALID_STUDENT_ID = -1;

    @BeforeAll
    static void beforeAll() {
        Config.initialize(CONFIG_FILENAME);
    }

    @Test
    void verifyValidStudentValues() {
        Student student = new Student(VALID_STUDENT_ID);
        assertNotNull(student);

        assertEquals(VALID_STUDENT_MAJOR, student.getMajor());
        assertEquals(VALID_STUDENT_GRADE, student.getGrade());
    }

    @Test
    void getStudentInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Student(INVALID_STUDENT_ID));
    }

    @Test
    void createNewStudentDuplicate() {
        assertThrows(IllegalArgumentException.class, () -> {
            Student student = new Student(
                    FAKE_STUDENT_FIRST_NAME,
                    FAKE_STUDENT_LAST_NAME,
                    FAKE_STUDENT_BIRTHDATE,
                    FAKE_STUDENT_PHONE_NUMBER,
                    VALID_STUDENT_USERNAME,
                    UNIQUE_STUDENT_ORG_EMAIl,
                    UNIQUE_STUDENT_SECONDARY_EMAIL,
                    FAKE_STUDENT_IS_ACTIVE,
                    new Department(FAKE_STUDENT_DEPARTMENT_ID),
                    FAKE_STUDENT_MAJOR,
                    FAKE_STUDENT_GRADE
            );
        });
    }

    @Test
    void createNewStudentAndTestVariables() {
        Student student = new Student(
                FAKE_STUDENT_FIRST_NAME,
                FAKE_STUDENT_LAST_NAME,
                FAKE_STUDENT_BIRTHDATE,
                FAKE_STUDENT_PHONE_NUMBER,
                UNIQUE_STUDENT_USERNAME,
                UNIQUE_STUDENT_ORG_EMAIl,
                UNIQUE_STUDENT_SECONDARY_EMAIL,
                FAKE_STUDENT_IS_ACTIVE,
                new Department(FAKE_STUDENT_DEPARTMENT_ID),
                FAKE_STUDENT_MAJOR,
                FAKE_STUDENT_GRADE
        );

        assertEquals(FAKE_STUDENT_MAJOR, student.getMajor());
        assertEquals(FAKE_STUDENT_GRADE, student.getGrade());


        // Delete person so test can be re-run
        deleteWithId(student.getId(), PERSON_TABLE_NAME);
        deleteWithId(student.getId(), STUDENT_TABLE_NAME);
    }

    @Test
    void getStringList() {
        Student valid = new Student(VALID_STUDENT_ID);

        List<String> actual = valid.getStringList();
        int studentSpecificStart = actual.size() - STUDENT_SPECIFIC_PARAMETERS;

        assertEquals(valid.getMajor(), actual.get(studentSpecificStart));
        assertEquals(valid.getGrade().toString(), actual.get(++studentSpecificStart));
    }

    private static void deleteWithId(long id, String table) {
        try {
            String sql = "DELETE FROM " + table + " WHERE `id` = ?";

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