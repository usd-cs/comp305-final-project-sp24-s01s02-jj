package edu.sandiego.comp305.sp24.schoolSim.model;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.Database;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    private static final String CONFIG_FILENAME = "config.properties";
    private static final int SQL_YEAR_OFFSET = 1900;

    private static final long VALID_PERSON_ID = 1;
    private static final String VALID_PERSON_FIRSTNAME = "Alumni1";
    private static final String VALID_PERSON_LASTNAME = "Smith";
    private static final Date VALID_PERSON_BIRTHDATE = new Date(2004 - SQL_YEAR_OFFSET, 2, 18);
    private static final String VALID_PERSON_PHONE_NUMBER = "1234567891";
    private static final String VALID_PERSON_USERNAME = "alumni";
    private static final String VALID_PERSON_ORGANIZATION_EMAIL = "alumni@sandiego.edu";
    private static final String VALID_PERSON_SECONDARY_EMAIL = "alumni@gmail.com";
    private static final boolean VALID_PERSON_IS_ACTIVE = true;
    private static final int VALID_PERSON_DEPARTMENT_ID = 1;

    private static final String FAKE_FIRST_NAME = "fakename";
    private static final String FAKE_LAST_NAME = "fakelast";
    private static final Date FAKE_BIRTHDATE = new Date(2004 - SQL_YEAR_OFFSET, 7, 1);
    private static final String FAKE_PHONE_NUMBER = "1234009955";
    private static final String FAKE_USERNAME = "alumni123";
    private static final String FAKE_ORG_EMAIL = "different_email@sandiego.edu";
    private static final String FAKE_SECONDARY_EMAIL = "different_email@gmail.edu";
    private static final boolean FAKE_PERSON_IS_ACTIVE = true;
    private static final int FAKE_PERSON_DEPARTMENT_ID = 1;

    private static final String UNIQUE_USERNAME = "uniqueuser";
    private static final String UNIQUE_ORG_EMAIl = "unique@sandiego.edu";
    private static final String UNIQUE_SECONDARY_EMAIL = "unique@gmail.com";

    private static final int INVALID_PERSON_ID = -1;


    @BeforeAll
    static void beforeAll() {
        Config.initialize(CONFIG_FILENAME);
    }

    @Test
    void verifyFirstPersonValues() {
        Person person = new Person(VALID_PERSON_ID);
        assertNotNull(person);

        assertEquals(VALID_PERSON_ID, person.getId());
        assertEquals(VALID_PERSON_FIRSTNAME, person.getFirstName());
        assertEquals(VALID_PERSON_LASTNAME, person.getLastName());
        assertEquals(VALID_PERSON_BIRTHDATE, person.getBirthdate());
        assertEquals(VALID_PERSON_PHONE_NUMBER, person.getPhoneNumber());
        assertEquals(VALID_PERSON_USERNAME, person.getUsername());
        assertEquals(VALID_PERSON_ORGANIZATION_EMAIL, person.getOrganizationEmail());
        assertEquals(VALID_PERSON_SECONDARY_EMAIL, person.getSecondaryEmail());
        assertEquals(VALID_PERSON_IS_ACTIVE, person.isActive());
        assertEquals(VALID_PERSON_DEPARTMENT_ID, person.getDepartment().getId());
    }

    @Test
    void getPersonDoesntExist() {
        assertThrows(IllegalArgumentException.class, () -> new Person(INVALID_PERSON_ID));
    }

    @Test
    void createPersonDuplicateUsername() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Person(
                    FAKE_FIRST_NAME,
                    FAKE_LAST_NAME,
                    FAKE_BIRTHDATE,
                    FAKE_PHONE_NUMBER,
                    VALID_PERSON_USERNAME,
                    FAKE_ORG_EMAIL,
                    FAKE_SECONDARY_EMAIL,
                    FAKE_PERSON_IS_ACTIVE,
                    new Department(FAKE_PERSON_DEPARTMENT_ID)
            );
        });
    }

    @Test
    void createPersonDuplicateOrgEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Person(
                    FAKE_FIRST_NAME,
                    FAKE_LAST_NAME,
                    FAKE_BIRTHDATE,
                    FAKE_PHONE_NUMBER,
                    FAKE_USERNAME,
                    VALID_PERSON_ORGANIZATION_EMAIL,
                    FAKE_SECONDARY_EMAIL,
                    FAKE_PERSON_IS_ACTIVE,
                    new Department(FAKE_PERSON_DEPARTMENT_ID)
            );
        });
    }

    @Test
    void createPersonDuplicateSecondaryEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Person(
                    FAKE_FIRST_NAME,
                    FAKE_LAST_NAME,
                    FAKE_BIRTHDATE,
                    FAKE_PHONE_NUMBER,
                    FAKE_USERNAME,
                    FAKE_ORG_EMAIL,
                    VALID_PERSON_SECONDARY_EMAIL,
                    FAKE_PERSON_IS_ACTIVE,
                    new Department(FAKE_PERSON_DEPARTMENT_ID)
            );
        });
    }

    @Test
    void createNewPersonTestVariables() {
        Person newPerson = new Person(
                FAKE_FIRST_NAME,
                FAKE_LAST_NAME,
                FAKE_BIRTHDATE,
                FAKE_PHONE_NUMBER,
                UNIQUE_USERNAME,
                UNIQUE_ORG_EMAIl,
                UNIQUE_SECONDARY_EMAIL,
                FAKE_PERSON_IS_ACTIVE,
                new Department(FAKE_PERSON_DEPARTMENT_ID)
        );
        assertEquals(FAKE_FIRST_NAME, newPerson.getFirstName());
        assertEquals(FAKE_LAST_NAME, newPerson.getLastName());
        assertEquals(FAKE_BIRTHDATE.toString(), newPerson.getBirthdate().toString());
        assertEquals(FAKE_PHONE_NUMBER, newPerson.getPhoneNumber());
        assertEquals(UNIQUE_USERNAME, newPerson.getUsername());
        assertEquals(UNIQUE_ORG_EMAIl, newPerson.getOrganizationEmail());
        assertEquals(UNIQUE_SECONDARY_EMAIL, newPerson.getSecondaryEmail());
        assertEquals(FAKE_PERSON_IS_ACTIVE, newPerson.isActive());
        assertEquals(FAKE_PERSON_DEPARTMENT_ID, newPerson.getDepartment().getId());


        // Delete person so test can be re-run
        deletePersonWithUsername(UNIQUE_USERNAME);
    }

    @Test
    void createNewPersonGetAndTestVariables() {
        Person newPerson1 = new Person(
                FAKE_FIRST_NAME,
                FAKE_LAST_NAME,
                FAKE_BIRTHDATE,
                FAKE_PHONE_NUMBER,
                UNIQUE_USERNAME,
                UNIQUE_ORG_EMAIl,
                UNIQUE_SECONDARY_EMAIL,
                FAKE_PERSON_IS_ACTIVE,
                new Department(FAKE_PERSON_DEPARTMENT_ID)
        );

        // Retrieve the person after creating it
        Person newPerson = new Person(newPerson1.getId());

        assertEquals(FAKE_FIRST_NAME, newPerson.getFirstName());
        assertEquals(FAKE_LAST_NAME, newPerson.getLastName());
        assertEquals(FAKE_BIRTHDATE.toString(), newPerson.getBirthdate().toString());
        assertEquals(FAKE_PHONE_NUMBER, newPerson.getPhoneNumber());
        assertEquals(UNIQUE_USERNAME, newPerson.getUsername());
        assertEquals(UNIQUE_ORG_EMAIl, newPerson.getOrganizationEmail());
        assertEquals(UNIQUE_SECONDARY_EMAIL, newPerson.getSecondaryEmail());
        assertEquals(FAKE_PERSON_IS_ACTIVE, newPerson.isActive());
        assertEquals(FAKE_PERSON_DEPARTMENT_ID, newPerson.getDepartment().getId());

        // Delete person so test can be re-run
        deletePersonWithUsername(UNIQUE_USERNAME);
    }

    @Test
    void getStringList() {
        Person valid = new Person(VALID_PERSON_ID);
        List<String> actual = valid.getStringList();

        List<String> expected = new ArrayList<>();
        expected.add(Long.toString(valid.getId()));
        expected.add(valid.getFirstName());
        expected.add(valid.getLastName());
        expected.add(valid.getBirthdate().toString());
        expected.add(valid.getPhoneNumber());
        expected.add(valid.getUsername());
        expected.add(valid.getOrganizationEmail());
        expected.add(valid.getSecondaryEmail());
        expected.add(Boolean.toString(valid.isActive()));
        expected.add(valid.getDepartment().toString());

        for(int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    private static void deletePersonWithUsername(String username) {
        try {
            String sql = "DELETE FROM Person WHERE `username` = ?";

            PreparedStatement preparedStatement = Database.getInstance().getDatabaseConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, username);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("User not deleted");
            }
        } catch (SQLException e) {
            throw new IllegalStateException("User couldn't be deleted");
        }
    }
}
