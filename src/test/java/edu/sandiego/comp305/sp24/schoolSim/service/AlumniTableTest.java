package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.model.Alumni;
import edu.sandiego.comp305.sp24.schoolSim.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlumniTableTest {
    ArrayList<Person> singlePersonList = new ArrayList<>();
    ArrayList<Person> twoPersonList = new ArrayList<>();
    ArrayList<Person> emptyList = new ArrayList<>();

    Alumni FutureNoah = new Alumni(371);
    Alumni FutureJohn = new Alumni(372);
    Alumni FutreAndrew = new Alumni(373);

    @BeforeAll
    static void beforeAll() {
        Config.initialize("config.properties");
    }

    @Test
    void getAllWithDegreeTypeNoMatch() {
        AlumniTable table = new AlumniTable();
        List<Alumni> returnedList = table.getAllWithDegreeType("5"); //That's right, a degree 2 levels above Doctor
        assertEquals(emptyList, returnedList);
    }
    @Test
    void getAllWithDegreeTypeOneMatch() {
        AlumniTable table = new AlumniTable();
        List<Alumni> returnedList = table.getAllWithDegreeType("2");
        singlePersonList.add(FutureNoah);
        assertEquals(singlePersonList.size(), returnedList.size());
        assertEquals(singlePersonList.get(0).getId(),returnedList.get(0).getId());
    }
    @Test
    void getAllWithDegreeTypeTwoMatch() {
        AlumniTable table = new AlumniTable();
        List<Alumni> returnedList = table.getAllWithDegreeType("3");
        twoPersonList.add(FutureJohn);
        twoPersonList.add(FutreAndrew);
        assertEquals(twoPersonList.size(), returnedList.size());
        assertEquals(twoPersonList.get(0).getId(),returnedList.get(0).getId());
        assertEquals(twoPersonList.get(1).getId(),returnedList.get(1).getId());
    }
    @Test
    void getAllWithGraduationDateNoMatch() {
        AlumniTable table = new AlumniTable();
        List<Alumni> returnedList = table.getAllWithGraduationDate("10000-1-23"); // not sure if degreeType is listed as degree string or int 0-2
        assertEquals(emptyList, returnedList);
    }
    @Test
    void getAllWithGraduationDateOneMatch() {
        AlumniTable table = new AlumniTable();
        List<Alumni> returnedList = table.getAllWithGraduationDate("3999-06-25");
        singlePersonList.add(FutureNoah);
        assertEquals(singlePersonList.size(), returnedList.size());
        assertEquals(singlePersonList.get(0).getId(),returnedList.get(0).getId());
    }
    @Test
    void getAllWithGraduationDateTwoMatch() {
        AlumniTable table = new AlumniTable();
        List<Alumni> returnedList = table.getAllWithGraduationDate("2101-05-27");
        twoPersonList.add(FutureJohn);
        twoPersonList.add(FutreAndrew);
        assertEquals(twoPersonList.size(), returnedList.size());
        assertEquals(twoPersonList.get(0).getId(),returnedList.get(0).getId());
        assertEquals(twoPersonList.get(1).getId(),returnedList.get(1).getId());
    }
}