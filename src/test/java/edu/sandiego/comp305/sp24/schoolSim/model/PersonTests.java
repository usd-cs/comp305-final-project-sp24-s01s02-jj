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

public class PersonTests {
    private static class DummyPerson extends Person {
        public DummyPerson(long id) {
            super(id);
        }

        public DummyPerson(String firstName, String lastName, Date birthdate, String phoneNumber, String username, String organizationEmail, String secondaryEmail, boolean isActive, Department department) {
            super(firstName, lastName, birthdate, phoneNumber, username, organizationEmail, secondaryEmail, isActive, department);
        }
    }
    private static final int DOESNT_EXIST_ID = -1;

    private static final String FAKE_FIRST_NAME = "fakename";
    private static final String FAKE_LAST_NAME = "fakelast";
    private static final Date FAKE_BIRTHDATE = new Date(2004 - 1900, 7, 1);
    private static final String FAKE_PHONE_NUMBER = "1234009955";
    private static final String FAKE_USERNAME = "alumni123";
    private static final String FAKE_ORG_EMAIL = "different_email@sandiego.edu";
    private static final String FAKE_SECONDARY_EMAIL = "different_email@gmail.edu";

    private static final String UNIQUE_USERNAME = "uniqueuser";
    private static final String UNIQUE_ORG_EMAIl = "unique@sandiego.edu";
    private static final String UNIQUE_SECONDARY_EMAIL = "unique@gmail.com";
    private static final long VALID_PERSON_ID = 1;

    @BeforeAll
    static void beforeAll() {
        Config.initialize("config.properties");
    }

    @Test
    void verifyFirstPersonValues() {
        DummyPerson person = new DummyPerson(VALID_PERSON_ID);
        assertNotNull(person);

        assertEquals(VALID_PERSON_ID, person.getId());
        assertEquals("Alumni1", person.getFirstName());
        assertEquals("Smith", person.getLastName());
        assertEquals("2004-03-18", person.getBirthdate().toString());
        assertEquals("1234567891", person.getPhoneNumber());
        assertEquals("alumni", person.getUsername());
        assertEquals("alumni@sandiego.edu", person.getOrganizationEmail());
        assertEquals("alumni@gmail.com", person.getSecondaryEmail());
        assertTrue(person.isActive());
        assertEquals(1, person.getDepartment().getId());
    }

    @Test
    void getPersonDoesntExist() {
        assertThrows(IllegalArgumentException.class, () -> new DummyPerson(DOESNT_EXIST_ID));
    }

    @Test
    void createPersonDuplicateUsername() {
        assertThrows(IllegalArgumentException.class, () -> {
            DummyPerson person = new DummyPerson(
                    FAKE_FIRST_NAME,
                    FAKE_LAST_NAME,
                    FAKE_BIRTHDATE,
                    FAKE_PHONE_NUMBER,
                    "alumni",
                    FAKE_ORG_EMAIL,
                    FAKE_SECONDARY_EMAIL,
                    true,
                    new Department(1)
            );
        });
    }

    @Test
    void createPersonDuplicateOrgEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            DummyPerson person = new DummyPerson(
                    FAKE_FIRST_NAME,
                    FAKE_LAST_NAME,
                    FAKE_BIRTHDATE,
                    FAKE_PHONE_NUMBER,
                    FAKE_USERNAME,
                    "alumni@sandiego.edu",
                    FAKE_SECONDARY_EMAIL,
                    true,
                    new Department(1)
            );
        });
    }

    @Test
    void createPersonDuplicateSecondaryEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            DummyPerson person = new DummyPerson(
                    FAKE_FIRST_NAME,
                    FAKE_LAST_NAME,
                    FAKE_BIRTHDATE,
                    FAKE_PHONE_NUMBER,
                    FAKE_USERNAME,
                    FAKE_ORG_EMAIL,
                    "alumni@gmail.com",
                    true,
                    new Department(1)
            );
        });
    }

    @Test
    void createNewPersonTestVariables() {
        DummyPerson newPerson = new DummyPerson(
                FAKE_FIRST_NAME,
                FAKE_LAST_NAME,
                FAKE_BIRTHDATE,
                FAKE_PHONE_NUMBER,
                UNIQUE_USERNAME,
                UNIQUE_ORG_EMAIl,
                UNIQUE_SECONDARY_EMAIL,
                true,
                new Department(1)
        );
        assertEquals(FAKE_FIRST_NAME, newPerson.getFirstName());
        assertEquals(FAKE_LAST_NAME, newPerson.getLastName());
        assertEquals(FAKE_BIRTHDATE.toString(), newPerson.getBirthdate().toString());
        assertEquals(FAKE_PHONE_NUMBER, newPerson.getPhoneNumber());
        assertEquals(UNIQUE_USERNAME, newPerson.getUsername());
        assertEquals(UNIQUE_ORG_EMAIl, newPerson.getOrganizationEmail());
        assertEquals(UNIQUE_SECONDARY_EMAIL, newPerson.getSecondaryEmail());
        assertTrue(newPerson.isActive());
        assertEquals(1, newPerson.getDepartment().getId());


        // Delete person so test can be re-run
        deletePersonWithUsername(UNIQUE_USERNAME);
    }

    @Test
    void createNewPersonGetAndTestVariables() {
        DummyPerson newPerson1 = new DummyPerson(
                FAKE_FIRST_NAME,
                FAKE_LAST_NAME,
                FAKE_BIRTHDATE,
                FAKE_PHONE_NUMBER,
                UNIQUE_USERNAME,
                UNIQUE_ORG_EMAIl,
                UNIQUE_SECONDARY_EMAIL,
                true,
                new Department(1)
        );

        DummyPerson newPerson = new DummyPerson(newPerson1.getId());
        assertEquals(FAKE_FIRST_NAME, newPerson.getFirstName());
        assertEquals(FAKE_LAST_NAME, newPerson.getLastName());
        assertEquals(FAKE_BIRTHDATE.toString(), newPerson.getBirthdate().toString());
        assertEquals(FAKE_PHONE_NUMBER, newPerson.getPhoneNumber());
        assertEquals(UNIQUE_USERNAME, newPerson.getUsername());
        assertEquals(UNIQUE_ORG_EMAIl, newPerson.getOrganizationEmail());
        assertEquals(UNIQUE_SECONDARY_EMAIL, newPerson.getSecondaryEmail());
        assertTrue(newPerson.isActive());
        assertEquals(1, newPerson.getDepartment().getId());


        // Delete person so test can be re-run
        deletePersonWithUsername(UNIQUE_USERNAME);
    }

    void deletePersonWithUsername(String username) {
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
    @Test
    void getStringList() {
        Person valid = new DummyPerson(VALID_PERSON_ID);

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
}
