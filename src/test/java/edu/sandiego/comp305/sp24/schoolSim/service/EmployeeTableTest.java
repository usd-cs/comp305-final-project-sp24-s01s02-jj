package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.enums.DegreeType;
import edu.sandiego.comp305.sp24.schoolSim.model.Alumni;
import edu.sandiego.comp305.sp24.schoolSim.model.Department;
import edu.sandiego.comp305.sp24.schoolSim.model.Employee;
import edu.sandiego.comp305.sp24.schoolSim.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTableTest {
    ArrayList<Person> singlePersonList = new ArrayList<>();
    ArrayList<Person> twoPersonList = new ArrayList<>();
    ArrayList<Person> emptyList = new ArrayList<>();

    Employee fakeGuy1 = new Employee(376);
    Employee fakeGuy2 = new Employee(377);
    Employee fakeGuy3 = new Employee(6);
    Employee greg = new Employee(378);


    @BeforeAll
    static void beforeAll() {
        Config.initialize("config.properties");
    }

    @Test
    void getAllWithStartDateNoMatch() {
        EmployeeTable table = new EmployeeTable();
        List<Employee> returnedList = table.getAllWithStartDate("12-5-72");
        assertEquals(emptyList, returnedList);
    }
    @Test
    void getAllWithStartDateOneMatch() {
        EmployeeTable table = new EmployeeTable();
        List<Employee> returnedList = table.getAllWithStartDate("2012-03-07");
        singlePersonList.add(greg);
        assertEquals(singlePersonList.size(), returnedList.size());
        assertEquals(singlePersonList.get(0).getId(),returnedList.get(0).getId());
    }
    @Test
    void getAllWithStartDateTwoMatch() {
        EmployeeTable table = new EmployeeTable();
        List<Employee> returnedList = table.getAllWithStartDate("2021-01-05");
        twoPersonList.add(fakeGuy1);
        twoPersonList.add(fakeGuy2);
        assertEquals(twoPersonList.size(), returnedList.size());
        assertEquals(twoPersonList.get(0).getId(),returnedList.get(0).getId());
        assertEquals(twoPersonList.get(1).getId(),returnedList.get(1).getId());
    }

    @Test
    void getAllWithHourlyWageNoMatch() {
        EmployeeTable table = new EmployeeTable();
        List<Employee> returnedList = table.getAllWithHourlyWage("10000.00");
        assertEquals(emptyList, returnedList);
    }
    @Test
    void getAllWithHourlyWageOneMatch() {
        EmployeeTable table = new EmployeeTable();
        List<Employee> returnedList = table.getAllWithHourlyWage("16.86");
        singlePersonList.add(fakeGuy3);
        assertEquals(singlePersonList.size(), returnedList.size());
        assertEquals(singlePersonList.get(0).getId(),returnedList.get(0).getId());

    }
    @Test
    void getAllWithHourlyWageTwoMatch() {
        EmployeeTable table = new EmployeeTable();
        List<Employee> returnedList = table.getAllWithHourlyWage(".12");
        twoPersonList.add(fakeGuy1);
        twoPersonList.add(fakeGuy2);
        assertEquals(twoPersonList.size(), returnedList.size());
        assertEquals(twoPersonList.get(0).getId(),returnedList.get(0).getId());
        assertEquals(twoPersonList.get(1).getId(),returnedList.get(1).getId());    }

    @Test
    void getAllWithMangerNoResults() {
        EmployeeTable table = new EmployeeTable();
        List<Employee> returnedList = table.getAllWithMangerID("jeff");
        assertEquals(returnedList,emptyList);
    }
    @Test
    void getAllWithMangerOneResult() {
        EmployeeTable table = new EmployeeTable();
        List<Employee> returnedList = table.getAllWithMangerID("377");
        singlePersonList.add(greg);
        assertEquals(returnedList.size(),singlePersonList.size());
        assertEquals(singlePersonList.get(0).getId(),returnedList.get(0).getId());
    }
    @Test
    void getAllWithMangerManyResults() {
        // I know it says threePersonList, but I didn't realize the manager I chose for my two employees
        // was already in use, so 3 people share the same manager. Test results still good tho.
        EmployeeTable table = new EmployeeTable();
        List<Employee> returnedList = table.getAllWithMangerID("5");
        twoPersonList.add(fakeGuy3);
        twoPersonList.add(fakeGuy1);
        twoPersonList.add(fakeGuy2);
        assertEquals(twoPersonList.size(), returnedList.size());
        assertEquals(twoPersonList.get(0).getId(),returnedList.get(0).getId());
        assertEquals(twoPersonList.get(1).getId(),returnedList.get(1).getId());
        assertEquals(twoPersonList.get(2).getId(),returnedList.get(2).getId());
    }

    @Test
    void deleteFromDatabaseExists() {
        EmployeeTable employeeTable = new EmployeeTable();

        Employee employee = new Employee(
                "Fake Name",
                "Fake Last",
                new Date(2020, 5, 6),
                "8580009999",
                "completelyuniqueemployee",
                "uniquieemployeeemails@org.com",
                "uniquieemployeeemails@gmail.com",
                true,
                new Department(1),
                new Date(2024, 8, 9),
                16.75,
                null
        );

        employeeTable.deleteFromDatabase(employee.getId());

        assertThrows(IllegalArgumentException.class, () -> {
            Employee employee1 = new Employee(employee.getId());
        });
    }

    @Test
    void deleteFromDatabaseDoesntExist() {
        EmployeeTable employeeTable = new EmployeeTable();

        assertThrows(IllegalStateException.class, () -> {
            employeeTable.deleteFromDatabase(-1);
        });
    }
}