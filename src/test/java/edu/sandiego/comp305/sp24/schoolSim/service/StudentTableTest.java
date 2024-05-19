package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Config;
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

    void getAllWithMajorOneMatch() {
        StudentTable table = new StudentTable();
        List<Student> returnList = table.getAllWithMajor("Computer Science");
    }

    void getAllWithMajorTwoMatches() {
        StudentTable table = new StudentTable();
        List<Student> returnList = table.getAllWithMajor("Math");
    }

    @Test
    void getAllInGrade() {
    }
}