package edu.sandiego.comp305.sp24.schoolSim.model;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.enums.DegreeType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class AlumniTest {
    @BeforeAll
    static void beforeAll() {
        Config.initialize("config.properties");
    }
    @Test
    void verifyFirstAlumniValues() {
        Alumni alumni = new Alumni(1);
        assertNotNull(alumni);

        assertEquals("2023-07-31", alumni.getGraduationDate().toString());
        assertEquals(DegreeType.BACHELOR, alumni.getDegree());
    }
}