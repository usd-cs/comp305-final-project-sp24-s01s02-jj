package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.model.Department;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
}