package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FacultyTableTest {
    ArrayList<Person> singlePersonList = new ArrayList<>();
    ArrayList<Person> twoPersonList = new ArrayList<>();
    ArrayList<Person> emptyList = new ArrayList<>();
    @BeforeAll
    static void beforeAll() {
        Config.initialize("config.properties");
    }

    @Test
    void getAllByRoom() {
    }

    @Test
    void getAllWithTenure() {
    }
}