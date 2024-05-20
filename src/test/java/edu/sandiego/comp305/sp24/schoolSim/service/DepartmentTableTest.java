package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.enums.DegreeType;
import edu.sandiego.comp305.sp24.schoolSim.model.Alumni;
import edu.sandiego.comp305.sp24.schoolSim.model.Department;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTableTest {

    Department fakeDepartment = new Department(1);
    @BeforeAll
    static void beforeAll() {
        Config.initialize("config.properties");
    }
    @Test
    void getByWithNameNoMatch() {
        DepartmentTable table = new DepartmentTable();
        assertEquals(Optional.empty(), table.getByName("Epic Department"));
    }
    @Test
    void getByWithNameMatch() {
        DepartmentTable table = new DepartmentTable();
        long results = table.getByName("Fake Department 1").get().getId();
        assertEquals(fakeDepartment.getId(), results);
    }

    @Test
    void deleteFromDatabaseExists() {
        DepartmentTable departmentTable = new DepartmentTable();

        Department department = new Department("Fake department name");

        departmentTable.deleteFromDatabase(department.getId());

        assertThrows(IllegalArgumentException.class, () -> {
            Department department1 = new Department(department.getId());
        });
    }

    @Test
    void deleteFromDatabaseDoesntExist() {
        DepartmentTable departmentTable = new DepartmentTable();

        assertThrows(IllegalStateException.class, () -> {
            departmentTable.deleteFromDatabase(-1);
        });
    }
}