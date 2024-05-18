package edu.sandiego.comp305.sp24.schoolSim.model;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.Database;
import edu.sandiego.comp305.sp24.schoolSim.enums.DegreeType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlumniTest {
    private static final String CONFIG_FILENAME = "config.properties";
    private static final int SQL_YEAR_OFFSET = 1900;
    private static final int ALUMNI_ADDITIONAL_PARAMETER_COUNT = 2;

    private static final int VALID_ALUMNI_ID = 1;
    private static final String VALID_ALUMNI_USERNAME = "alumni";
    private static final String VALID_ALUMNI_GRADUATION_DATE = "2023-07-31";
    private static final DegreeType VALID_ALUMNI_DEGREE = DegreeType.BACHELOR;

    private static final int INVALID_ALUMNI_ID = -1;

    private static final String FAKE_FIRST_NAME = "FAKENAME";
    private static final String FAKE_LAST_NAME = "FAKELAST";
    private static final Date FAKE_BIRTHDATE = new Date(2004 - SQL_YEAR_OFFSET, 4, 18);
    private static final String FAKE_PHONE_NUMBER = "8584940009";
    private static final String UNIQUE_USERNAME = "uniquealum";
    private static final String UNIQUE_ORG_EMAIl = "uniquealum@sandiego.edu";
    private static final String UNIQUE_SECONDARY_EMAIL = "uniquealum@gmail.com";
    private static final boolean FAKE_ALUMNI_IS_ACTIVE = true;
    private static final int FAKE_ALUMNI_DEPARTMENT_ID = 1;
    private static final Date FAKE_ALUMNI_GRADUATION_DATE = new Date(2024 - SQL_YEAR_OFFSET, 6, 30);
    private static final DegreeType FAKE_ALUMNI_DEGREE = DegreeType.BACHELOR;

    private static final String PERSON_TABLE_NAME = "Person";
    private static final String ALUMNI_TABLE_NAME = "Alumni";


    @BeforeAll
    static void beforeAll() {
        Config.initialize(CONFIG_FILENAME);
    }
    @Test
    void verifyFirstAlumniValues() {
        Alumni alumni = new Alumni(VALID_ALUMNI_ID);
        assertNotNull(alumni);

        assertEquals(VALID_ALUMNI_GRADUATION_DATE, alumni.getGraduationDate().toString());
        assertEquals(VALID_ALUMNI_DEGREE, alumni.getDegree());
    }

    @Test
    void getAlumniInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Alumni(INVALID_ALUMNI_ID));
    }

    @Test
    void createNewAlumniDuplicate() {
        assertThrows(IllegalArgumentException.class, () -> {
            Alumni alumni = new Alumni(
                    FAKE_FIRST_NAME,
                    FAKE_LAST_NAME,
                    FAKE_BIRTHDATE,
                    FAKE_PHONE_NUMBER,
                    VALID_ALUMNI_USERNAME,
                    UNIQUE_ORG_EMAIl,
                    UNIQUE_SECONDARY_EMAIL,
                    FAKE_ALUMNI_IS_ACTIVE,
                    new Department(FAKE_ALUMNI_DEPARTMENT_ID),
                    FAKE_ALUMNI_GRADUATION_DATE,
                    FAKE_ALUMNI_DEGREE
            );
        });
    }

    @Test
    void createNewAlumniAndTestVariables() {
        Alumni alumni = new Alumni(
                FAKE_FIRST_NAME,
                FAKE_LAST_NAME,
                FAKE_BIRTHDATE,
                FAKE_PHONE_NUMBER,
                UNIQUE_USERNAME,
                UNIQUE_ORG_EMAIl,
                UNIQUE_SECONDARY_EMAIL,
                FAKE_ALUMNI_IS_ACTIVE,
                new Department(FAKE_ALUMNI_DEPARTMENT_ID),
                FAKE_ALUMNI_GRADUATION_DATE,
                FAKE_ALUMNI_DEGREE
        );

        assertEquals(FAKE_ALUMNI_GRADUATION_DATE.toString(), alumni.getGraduationDate().toString());
        assertEquals(FAKE_ALUMNI_DEGREE, alumni.getDegree());


        // Delete alumni so test can be re-run
        deleteSQLEntryWithId(alumni.getId(), PERSON_TABLE_NAME);
        deleteSQLEntryWithId(alumni.getId(), ALUMNI_TABLE_NAME);
    }

    @Test
    void getStringList() {
        Alumni valid = new Alumni(VALID_ALUMNI_ID);

        List<String> actual = valid.getStringList();
        int alumniSpecificStart = actual.size() - ALUMNI_ADDITIONAL_PARAMETER_COUNT;

        assertEquals(valid.getGraduationDate().toString(), actual.get(alumniSpecificStart));
        assertEquals(valid.getDegree().toString(), actual.get(++alumniSpecificStart));
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