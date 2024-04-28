package edu.sandiego.comp305.sp24.schoolSim.model;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTests {
    private static class DummyPerson extends Person {
        public DummyPerson(int id) {
            super(id);
        }

        public DummyPerson(String firstName, String lastName, Date birthdate, String phoneNumber, String username, String organizationEmail, String secondaryEmail, boolean isActive, int department) {
            super(firstName, lastName, birthdate, phoneNumber, username, organizationEmail, secondaryEmail, isActive, department);
        }
    }

    private static final String FAKE_FIRST_NAME = "fakename";
    private static final String FAKE_LAST_NAME = "fakelast";
    private static final Date FAKE_BIRTHDATE = new Date(2004 - 1900, 7, 1);
    private static final String FAKE_PHONE_NUMBER = "1234009955";
    private static final String FAKE_USERNAME = "alumni123";
    private static final String FAKE_ORG_EMAIL = "different_email@sandiego.edu";
    private static final String FAKE_SECONDARY_EMAIL = "different_email@gmail.edu";
    private static final Date FAKE_GRAD_DATE = new Date(2023 - 1900, 8, 1);


    @BeforeAll
    static void beforeAll() {
        Config.initialize("config.properties");
    }

    @Test
    void verifyFirstPersonValues() {
        DummyPerson person = new DummyPerson(1);
        assertNotNull(person);

        assertEquals(1, person.getId());
        assertEquals("Alumni1", person.getFirstName());
        assertEquals("Smith", person.getLastName());
        assertEquals("2004-03-18", person.getBirthdate().toString());
        assertEquals("1234567891", person.getPhoneNumber());
        assertEquals("alumni", person.getUsername());
        assertEquals("alumni@sandiego.edu", person.getOrganizationEmail());
        assertEquals("alumni@gmail.com", person.getSecondaryEmail());
        assertTrue(person.isActive());
        assertEquals(0, person.getDepartment());
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
                    1
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
                    1
            );
        });
    }

    @Test
    void createAlumniDuplicateSecondaryEmail() {
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
                    1
            );
        });
    }
}
