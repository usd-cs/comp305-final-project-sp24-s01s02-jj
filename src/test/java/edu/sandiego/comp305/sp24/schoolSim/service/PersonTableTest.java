package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonTableTest {
    ArrayList<Person> singlePersonList = new ArrayList<>();
    ArrayList<Person> twoPersonList = new ArrayList<>();
    ArrayList<Person> emptyList = new ArrayList<>();
    Person John = new Person(142);
    Person EvilJohn = new Person(143);
    Person Fac1 = new Person(75);
    Person fakeGuy1 = new Person(5);
    Person fakeGuy2 = new Person(6);

    @BeforeAll
    static void beforeAll() {
        Config.initialize("config.properties");
    }

    @Test
    void getAllWithFirstNameReturnEmptyList() {
        PersonTable table = new PersonTable();
        List<Person> returnedList = table.getAllWithFirstName("notJohn");
        assertEquals(emptyList, returnedList);

    }

    @Test
    void getAllWithFirstNameReturnOneMatch() {
        PersonTable table = new PersonTable();
        singlePersonList.add(Fac1);
        List<Person> returnedList = table.getAllWithFirstName("Fac1");
        long testID = singlePersonList.get(0).getId();
        long Fac1ID = 75;

        assertEquals(singlePersonList.size(), returnedList.size());
        assertEquals(testID,Fac1ID);

    }

    @Test
    void getAllWithFirstNameReturnManyMatches() {
        PersonTable table = new PersonTable();
        twoPersonList.add(John);
        twoPersonList.add(EvilJohn);
        List<Person> returnedList = table.getAllWithFirstName("John");
        assertEquals(twoPersonList.size(), returnedList.size());
        long testID1 = twoPersonList.get(0).getId();
        long testID2 = twoPersonList.get(1).getId();
        long JohnID = John.getId();
        long EvilID = EvilJohn.getId();
        assertEquals(testID1, JohnID);
        assertEquals(testID2, EvilID);
    }

    @Test
    void getAllWithLastNameReturnEmptyList() {
        PersonTable table = new PersonTable();
        List<Person> returnedList = table.getAllWithLastName("lastNameSoLongIDoNotThinkAnyoneWillHaveItBilips");
        assertEquals(emptyList, returnedList);
    }

    @Test
    void getAllWithLastNameReturnOneMatch() {
        PersonTable table = new PersonTable();
        singlePersonList.add(Fac1);
        List<Person> returnedList = table.getAllWithLastName("Fac1last");
        long testID = singlePersonList.get(0).getId();
        long Fac1ID = 75;

        assertEquals(singlePersonList.size(), returnedList.size());
        assertEquals(testID,Fac1ID);
    }

    @Test
    void getAllWithLastNameReturnManyMatch() {
        PersonTable table = new PersonTable();
        twoPersonList.add(fakeGuy1);
        twoPersonList.add(fakeGuy2);
        List<Person> returnedList = table.getAllWithLastName("Fakelast");
        assertEquals(twoPersonList.size(), returnedList.size());
        long testID1 = twoPersonList.get(0).getId();
        long testID2 = twoPersonList.get(1).getId();
        long FakeID1 = fakeGuy1.getId();
        long FakeID2 = fakeGuy2.getId();
        assertEquals(testID1, FakeID1);
        assertEquals(testID2, FakeID2);
    }

    @Test
    void getAllWithPhoneNumberReturnNoMatch() {
        PersonTable table = new PersonTable();
        List<Person> returnedList = table.getAllWithPhoneNumber("911");
        assertEquals(emptyList, returnedList);
    }

    @Test
    void getAllWithPhoneNumberReturnOneMatch() {
        PersonTable table = new PersonTable();
        singlePersonList.add(John);
        List<Person> returnedList = table.getAllWithPhoneNumber("1");
        assertEquals(singlePersonList.size(),returnedList.size());
        long JohnID = John.getId();
        long testID = returnedList.get(0).getId();
        assertEquals(testID, JohnID);
    }

    @Test
    void getAllWithPhoneNumberReturnManyMatch() {
        PersonTable table = new PersonTable();
        twoPersonList.add(fakeGuy1);
        twoPersonList.add(fakeGuy2);
        List<Person> returnedList = table.getAllWithPhoneNumber("1235650099");
        assertEquals(twoPersonList.size(), returnedList.size());
        long testID1 = twoPersonList.get(0).getId();
        long testID2 = twoPersonList.get(1).getId();
        long FakeID1 = fakeGuy1.getId();
        long FakeID2 = fakeGuy2.getId();
        assertEquals(testID1, FakeID1);
        assertEquals(testID2, FakeID2);

    }

    @Test
    void getByUsernameReturnNoMatch() {
        PersonTable table = new PersonTable();
        assertEquals(Optional.empty(), table.getByUsername("FreakyJohn"));
    }

    @Test
    void getByUsernameReturnMatch() {
        PersonTable table = new PersonTable();
        long results = table.getByUsername("JohnE").get().getId();
        assertEquals(results,EvilJohn.getId());
    }

    @Test
    void getByOrganizationEmailReturnNoMatch() {
        PersonTable table = new PersonTable();
        assertEquals(Optional.empty(), table.getByOrganizationEmail("notjohn@disney.com"));

    }

    @Test
    void getByOrganizationEmailReturnMatch() {
        PersonTable table = new PersonTable();
        long results = table.getByOrganizationEmail("john@evilmail.com").get().getId();
        assertEquals(results,EvilJohn.getId());
    }

    @Test
    void getBySecondaryEmailReturnNoMatch() {
        PersonTable table = new PersonTable();
        assertEquals(Optional.empty(), table.getBySecondaryEmail("nonapprovedjohnemail@cmail.eom"));
    }

    @Test
    void getBySecondaryEmailReturnMatch() {
        PersonTable table = new PersonTable();
        long results = table.getBySecondaryEmail("john@evilmail2.com").get().getId();
        assertEquals(results,EvilJohn.getId());
    }

    @Test
    void getActivePeopleReturnEveryoneButEvilJohn() {
        PersonTable table = new PersonTable();
        List<Person> resultsList = table.getActivePeople();
        int numOfActivePeople = 14; // the database only has 7 people, only one is inactive
        assertEquals(resultsList.size(), numOfActivePeople);

    }

    @Test
    void getInactivePeopleReturnOnlyEvilJohn() {
        PersonTable table = new PersonTable();
        singlePersonList.add(EvilJohn);
        List<Person> returnedList = table.getInactivePeople();
        assertEquals(singlePersonList.size(),returnedList.size());
    }


}