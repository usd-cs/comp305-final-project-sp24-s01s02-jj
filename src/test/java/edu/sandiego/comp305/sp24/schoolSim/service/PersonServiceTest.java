package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {
    ArrayList<Person> singlePersonList = new ArrayList<>();
    ArrayList<Person> twoPersonList = new ArrayList<>();
    ArrayList<SimplePerson> emptyList = new ArrayList<>();
    Person John = new SimplePerson(142);
    Person EvilJohn = new SimplePerson(143);
    Person Fac1 = new SimplePerson(75);
    Person fakeGuy1 = new SimplePerson(5);
    Person fakeGuy2 = new SimplePerson(6);

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
        singlePersonList.add(Fac1);
        List<Person> returnedList = PersonService.getAllWithFirstName("Fac1");
        long testID = singlePersonList.get(0).getId();
        long Fac1ID = 75;

        assertEquals(singlePersonList.size(), returnedList.size());
        assertEquals(testID,Fac1ID);

    }

    @Test
    void getAllWithFirstNameReturnManyMatches() {
        twoPersonList.add(John);
        twoPersonList.add(EvilJohn);
        List<Person> returnedList = PersonService.getAllWithFirstName("John");
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
        List<Person> returnedList = PersonService.getAllWithLastName("lastNameSoLongIDoNotThinkAnyoneWillHaveItBilips");
        assertEquals(emptyList, returnedList);
    }

    @Test
    void getAllWithLastNameReturnOneMatch() {
        singlePersonList.add(Fac1);
        List<Person> returnedList = PersonService.getAllWithLastName("Fac1last");
        long testID = singlePersonList.get(0).getId();
        long Fac1ID = 75;

        assertEquals(singlePersonList.size(), returnedList.size());
        assertEquals(testID,Fac1ID);
    }

    @Test
    void getAllWithLastNameReturnManyMatch() {
        twoPersonList.add(fakeGuy1);
        twoPersonList.add(fakeGuy2);
        List<Person> returnedList = PersonService.getAllWithLastName("Fakelast");
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
        List<Person> returnedList = PersonService.getAllWithPhoneNumber("911");
        assertEquals(emptyList, returnedList);
    }

    @Test
    void getAllWithPhoneNumberReturnOneMatch() {
        singlePersonList.add(John);
        List<Person> returnedList = PersonService.getAllWithPhoneNumber("1");
        assertEquals(singlePersonList.size(),returnedList.size());
        long JohnID = John.getId();
        long testID = returnedList.get(0).getId();
        assertEquals(testID, JohnID);
    }

    @Test
    void getAllWithPhoneNumberReturnManyMatch() {
        twoPersonList.add(fakeGuy1);
        twoPersonList.add(fakeGuy2);
        List<Person> returnedList = PersonService.getAllWithPhoneNumber("1235650099");
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
        assertEquals(Optional.empty(),PersonService.getByUsername("FreakyJohn"));
    }

    @Test
    void getByUsernameReturnMatch() {
        long results = PersonService.getByUsername("JohnE").get().getId();
        assertEquals(results,EvilJohn.getId());
    }

    @Test
    void getByOrganizationEmailReturnNoMatch() {
        assertEquals(Optional.empty(),PersonService.getByOrganizationEmail("notjohn@disney.com"));

    }

    @Test
    void getByOrganizationEmailReturnMatch() {
        long results = PersonService.getByOrganizationEmail("john@evilmail.com").get().getId();
        assertEquals(results,EvilJohn.getId());
    }

    @Test
    void getBySecondaryEmailReturnNoMatch() {
        assertEquals(Optional.empty(),PersonService.getBySecondaryEmail("nonapprovedjohnemail@cmail.eom"));
    }

    @Test
    void getBySecondaryEmailReturnMatch() {
        long results = PersonService.getBySecondaryEmail("john@evilmail2.com").get().getId();
        assertEquals(results,EvilJohn.getId());
    }

    @Test
    void getActivePeopleReturnEveryoneButEvilJohn() {
        List<Person> resultsList = PersonService.getActivePeople();
        int numOfActivePeople = 6; // the database only has 7 people, only one is inactive
        assertEquals(resultsList.size(), numOfActivePeople);

    }

    @Test
    void getInactivePeopleReturnOnlyEvilJohn() {
        singlePersonList.add(EvilJohn);
        List<Person> returnedList = PersonService.getInactivePeople();
        assertEquals(singlePersonList.size(),returnedList.size());
    }


}