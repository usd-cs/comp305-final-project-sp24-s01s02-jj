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

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private static final int VALID_STUDENT_ID = 55;
    private static final String VALID_STUDENT_USERNAME = "student";
    private static final String VALID_STUDENT_MAJOR = "Computer Science";
    private static final Grade VALID_STUDENT_GRADE = Grade.SOPHOMORE;

    private static final int INVALID_STUDENT_ID = -1;

    private static final String FAKE_FIRST_NAME = "FAKENAME";
    private static final String FAKE_LAST_NAME = "FAKELAST";
    private static final Date FAKE_BIRTHDATE = new Date(2004 - 1900, 4, 18);
    private static final String FAKE_PHONE_NUMBER = "8584940009";
    private static final String UNIQUE_USERNAME = "uniquestudent";
    private static final String UNIQUE_ORG_EMAIl = "uniquestudent@sandiego.edu";
    private static final String UNIQUE_SECONDARY_EMAIL = "uniquestudent@gmail.com";
    private static final int FAKE_DEPARTMENT_ID = 1;
    private static final String FAKE_STUDENT_MAJOR = "Mathematics";
    private static final Grade FAKE_STUDENT_GRADE = Grade.FRESHMAN;

    @BeforeAll
    static void beforeAll() {
        Config.initialize("config.properties");
    }

    @Test
    void verifyFirstStudentValues() {
        Student student = new Student(VALID_STUDENT_ID);
        assertNotNull(student);

        assertEquals(VALID_STUDENT_MAJOR, student.getMajor());
        assertEquals(VALID_STUDENT_GRADE, student.getGrade());
    }

    @Test
    void getAlumniInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Student(INVALID_STUDENT_ID));
    }

    @Test
    void createNewStudentDuplicate() {
        assertThrows(IllegalArgumentException.class, () -> {
            Student student = new Student(
                    FAKE_FIRST_NAME,
                    FAKE_LAST_NAME,
                    FAKE_BIRTHDATE,
                    FAKE_PHONE_NUMBER,
                    VALID_STUDENT_USERNAME,
                    UNIQUE_ORG_EMAIl,
                    UNIQUE_SECONDARY_EMAIL,
                    true,
                    new Department(FAKE_DEPARTMENT_ID),
                    FAKE_STUDENT_MAJOR,
                    FAKE_STUDENT_GRADE
            );
        });
    }

    @Test
    void createNewStudentAndTestVariables() {
        Student student = new Student(
                FAKE_FIRST_NAME,
                FAKE_LAST_NAME,
                FAKE_BIRTHDATE,
                FAKE_PHONE_NUMBER,
                UNIQUE_USERNAME,
                UNIQUE_ORG_EMAIl,
                UNIQUE_SECONDARY_EMAIL,
                true,
                new Department(FAKE_DEPARTMENT_ID),
                FAKE_STUDENT_MAJOR,
                FAKE_STUDENT_GRADE
        );

        assertEquals(FAKE_STUDENT_MAJOR, student.getMajor());
        assertEquals(FAKE_STUDENT_GRADE, student.getGrade());


        // Delete person so test can be re-run
        deleteWithId(student.getId(), "Person");
        deleteWithId(student.getId(), "Student");
    }

    void deleteWithId(int id, String table) {
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