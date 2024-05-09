package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.model.Department;
import edu.sandiego.comp305.sp24.schoolSim.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito.*;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {
    ArrayList<Person> singlePersonList = new ArrayList<>();
    ArrayList<SimplePerson> twoPersonList = new ArrayList<>();
    ArrayList<SimplePerson> emptyList = new ArrayList<>();
    Date bday = new Date(2003, 1, 1);
    Person John = new SimplePerson(142);

    @BeforeAll
    static void beforeAll() {
        Config.initialize("config.properties");
    }

    @Test
    void getAllWithFirstNameReturnEmptyList() {
        List<Person> returnedList = PersonService.getAllWithFirstName("notJohn");
        assertEquals(emptyList, returnedList);

    }

    @Test
    void getAllWithFirstNameReturnOneMatch() {
        singlePersonList.add(John);
        List<Person> returnedList = PersonService.getAllWithFirstName("John");
        assertEquals(singlePersonList, returnedList);


    }

    @Test
    void getAllWithFirstNameReturnManyMatches() {
    }

    @Test
    void getAllWithLastNameReturnEmptyList() {
    }

    @Test
    void getAllWithLastNameReturnOneMatch() {
    }

    @Test
    void getAllWithLastNameReturnManyMatch() {
    }

    @Test
    void getAllWithPhoneNumberReturnNoMatch() {
    }

    @Test
    void getAllWithPhoneNumberReturnOneMatch() {
    }

    @Test
    void getAllWithPhoneNumberReturnManyMatch() {
    }

    @Test
    void getByUsernameReturnNoMatch() {
    }

    @Test
    void getByUsernameReturnMatch() {
    }

    @Test
    void getByOrganizationEmailReturnNoMatch() {
    }

    @Test
    void getByOrganizationEmailReturnMatch() {
    }

    @Test
    void getBySecondaryEmailReturnNoMatch() {
    }

    @Test
    void getBySecondaryEmailReturnMatch() {
    }

    @Test
    void getActivePeopleReturnNoMatch() {
    }

    @Test
    void getActivePeopleReturnOneMatch() {
    }

    @Test
    void getActivePeopleReturnManyMatch() {
    }

    @Test
    void getInactivePeopleReturnNoMatch() {
    }
    @Test
    void getInactivePeopleReturnOneMatch() {
    }

    @Test
    void getInactivePeopleReturnManyMatch() {
    }

}