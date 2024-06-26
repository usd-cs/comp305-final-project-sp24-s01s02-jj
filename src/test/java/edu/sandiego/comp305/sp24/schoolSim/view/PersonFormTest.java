package edu.sandiego.comp305.sp24.schoolSim.view;

import static org.junit.jupiter.api.Assertions.*;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.Database;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.NoSuchElementException;

class PersonFormTest {
    @BeforeAll
    static void configDatabase() {
        Config.initialize("config.properties");
    }

    @Test
    void setDepartmentNonExistent() {
        PersonForm form = new PersonForm();
        assertThrows(NoSuchElementException.class, () -> {form.setDepartment("Not a real department");});
    }

    @Test
    void setDepartmentValid() {
        PersonForm form = new PersonForm();
        assertDoesNotThrow(()->{form.setDepartment("Fake Department 1");});
        assertEquals(1, form.getDepartmentInstance().getId());
    }

}