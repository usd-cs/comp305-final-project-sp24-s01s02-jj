package edu.sandiego.comp305.sp24.schoolSim.view;

import static org.junit.jupiter.api.Assertions.*;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.Database;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;

class PersonFormTest {
    @BeforeAll
    static void configDatabase() {
        Config.initialize("config.properties");
    }

    @Test
    void toBulmaForm() {
    }

    @Test
    void getDepartmentNonExistent() {
        PersonForm form = new PersonForm(
                "Alex",
                "Mason",
                new Date(1966-1900,7, 24),
                "1234567890",
                "amason",
                "amason@acme.org",
                "alex@amason.io",
                true,
                "Not A Real Department"
        );
        assertNull(form.getDepartment());
    }

    @Test
    void getDepartmentValid() {
        PersonForm form = new PersonForm(
                "Alex",
                "Mason",
                new Date(1966-1900,7, 24),
                "1234567890",
                "amason",
                "amason@acme.org",
                "alex@amason.io",
                true,
                "Fake Department 1"
        );
        assertEquals(1, form.getDepartment().getId());
    }

}