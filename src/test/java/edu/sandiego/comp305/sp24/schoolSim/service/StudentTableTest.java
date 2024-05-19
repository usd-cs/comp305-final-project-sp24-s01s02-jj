package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.enums.Grade;
import edu.sandiego.comp305.sp24.schoolSim.model.Person;
import edu.sandiego.comp305.sp24.schoolSim.model.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudentTableTest {
    ArrayList<Person> singlePersonList = new ArrayList<>();
    ArrayList<Person> twoPersonList = new ArrayList<>();
    ArrayList<Person> emptyList = new ArrayList<>();

    Student fakeStudent = new Student(55);

    Student myHomieKevin = new Student(374);

    Student myHomieRonnie = new Student(375);


    @BeforeAll
    static void beforeAll() {
        Config.initialize("config.properties");
    }

    @Test
    void getAllWithMajorNoMatches() {
        StudentTable table = new StudentTable();
        List<Student> returnList = table.getAllWithMajor("UngaBunga");
        assertEquals(returnList, emptyList);
    }
    @Test
    void getAllWithMajorOneMatch() {
        StudentTable table = new StudentTable();
        List<Student> returnedList = table.getAllWithMajor("Computer Science");
        singlePersonList.add(fakeStudent);
        assertEquals(singlePersonList.size(), returnedList.size());
        assertEquals(singlePersonList.get(0).getId(),returnedList.get(0).getId());
    }
    @Test
    void getAllWithMajorTwoMatches() {
        StudentTable table = new StudentTable();
        List<Student> returnList = table.getAllWithMajor("Math");
        twoPersonList.add(myHomieKevin);
        twoPersonList.add(myHomieRonnie);
        assertEquals(returnList.size(), twoPersonList.size());
        assertEquals(twoPersonList.get(0).getId(),returnList.get(0).getId());
        assertEquals(twoPersonList.get(1).getId(),returnList.get(1).getId());
    }

    @Test
    void getAllInGradeNoMatches() {
        StudentTable table = new StudentTable();
        List<Student> returnedList = table.getAllInGrade(Grade.FRESHMAN);
        assertEquals(returnedList, emptyList);
    }
    @Test
    void getAllInGradeOneMatch() {
        StudentTable table = new StudentTable();
        List<Student> returnedList = table.getAllInGrade(Grade.SOPHOMORE);
        singlePersonList.add(fakeStudent);
        assertEquals(singlePersonList.size(), returnedList.size());
        assertEquals(singlePersonList.get(0).getId(),returnedList.get(0).getId());
    }
    @Test
    void getAllInGradeTwoMatch() {
        StudentTable table = new StudentTable();
        List<Student> returnedList = table.getAllInGrade(Grade.SENIOR);
        twoPersonList.add(myHomieKevin);
        twoPersonList.add(myHomieRonnie);
        assertEquals(returnedList.size(), twoPersonList.size());
        assertEquals(twoPersonList.get(0).getId(),returnedList.get(0).getId());
        assertEquals(twoPersonList.get(1).getId(),returnedList.get(1).getId());

    }
}