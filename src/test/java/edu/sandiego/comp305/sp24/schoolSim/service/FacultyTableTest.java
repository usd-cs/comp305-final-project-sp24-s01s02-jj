package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FacultyTableTest {
    ArrayList<Person> singlePersonList = new ArrayList<>();
    ArrayList<Person> twoPersonList = new ArrayList<>();
    ArrayList<Person> emptyList = new ArrayList<>();
    Faculty faculty = new Faculty(75);
    Faculty EvilDrOlsen = new Faculty(376);
    Faculty EvilDrSat = new Faculty(377);

    @BeforeAll
    static void beforeAll() {
        Config.initialize("config.properties");
    }

    @Test
    void getAllByRoomNoMatch() {
        FacultyTable table = new FacultyTable();
        List<Faculty> returnedList = table.getAllByOfficeLocation("700000");
        assertEquals(emptyList, returnedList);
    }
    @Test
    void getAllByRoomOneMatch() {
        //1 is default officeLocation
        FacultyTable table = new FacultyTable();
        List<Faculty> returnedList = table.getAllByOfficeLocation("1");
        singlePersonList.add(faculty);
        assertEquals(singlePersonList.size(), returnedList.size());
        assertEquals(singlePersonList.get(0).getId(),returnedList.get(0).getId());
    }
    @Test
    void getAllByRoomTwoMatch() {
        //45
        FacultyTable table = new FacultyTable();
        List<Faculty> returnedList = table.getAllByOfficeLocation("45");
        twoPersonList.add(EvilDrOlsen);
        twoPersonList.add(EvilDrSat);
        assertEquals(twoPersonList.size(), returnedList.size());
        assertEquals(twoPersonList.get(0).getId(),returnedList.get(0).getId());
        assertEquals(twoPersonList.get(1).getId(),returnedList.get(1).getId());
    }

    @Test
    void getAllWithTenureOnlyOneMatch() {
        FacultyTable table = new FacultyTable();
        List<Faculty> returnedList = table.getAllWithTenure();
        singlePersonList.add(faculty);
        assertEquals(singlePersonList.size(), returnedList.size());
        assertEquals(singlePersonList.get(0).getId(),returnedList.get(0).getId());
    }

    @Test
    void getAllWithNoTenureTwoMatch() {
        FacultyTable table = new FacultyTable();
        List<Faculty> returnedList = table.getAllWithNoTenure();
        twoPersonList.add(EvilDrOlsen);
        twoPersonList.add(EvilDrSat);
        assertEquals(twoPersonList.size(), returnedList.size());
        assertEquals(twoPersonList.get(0).getId(),returnedList.get(0).getId());
        assertEquals(twoPersonList.get(1).getId(),returnedList.get(1).getId());
        }
}