package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseItem;
import edu.sandiego.comp305.sp24.schoolSim.model.Department;
import edu.sandiego.comp305.sp24.schoolSim.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractTableTest {
    private static final String CONFIG_FILENAME = "config.properties";
    private static class DummyTable extends AbstractTable {

        @Override
        public String getTableName() {
            return "Person";
        }

        @Override
        public List<String> getColumnNames() {
            List<String> columnNames = new ArrayList<>();
            columnNames.add("ID");
            columnNames.add("First Name");
            columnNames.add("Last Name");
            columnNames.add("Birthdate");
            columnNames.add("Phone Number");
            columnNames.add("Username");
            columnNames.add("Organization Email");
            columnNames.add("Secondary Email");
            columnNames.add("Is Active");
            columnNames.add("Department");
            return columnNames;
        }

        @Override
        public List<DatabaseItem> getAllPaged(int pageNumber) {
            return getPagedResultSet(pageNumber, Person::new);
        }

        @Override
        public void deleteFromDatabase(long id) {
            // not needed for tests
        }
    }

    private static class DummyInvalidTable extends AbstractTable {
        @Override
        public String getTableName() {
            return "NotReal";
        }

        @Override
        public List<String> getColumnNames() {
            List<String> columnNames = new ArrayList<>();
            return columnNames;
        }

        @Override
        public List<DatabaseItem> getAllPaged(int pageNumber) {
            return null;
        }

        @Override
        public void deleteFromDatabase(long id) {
            // not needed for tests
        }
    }

    @BeforeAll
    static void beforeAll() {
        Config.initialize(CONFIG_FILENAME);
    }

    @Test
    void getCountTableRows() {
        DummyTable dummyTable = new DummyTable();
        long result = dummyTable.getCountTableRows();

        assertEquals(19, result);
    }

    @Test
    void getPagedResultSetTest() {
        DummyTable dummyTable = new DummyTable();
        List<DatabaseItem> results = dummyTable.getPagedResultSet(0, Person::new);

        assertEquals(19, results.size());
    }

    @Test
    void getCountTableRowsInvalid() {
        DummyInvalidTable dummyInvalidTable = new DummyInvalidTable();

        assertThrows(IllegalStateException.class, () -> {
            long result = dummyInvalidTable.getCountTableRows();
        });
    }

    @Test
    void getPagedResultSetInvalidTest() {
        DummyInvalidTable dummyInvalidTable = new DummyInvalidTable();

        assertThrows(IllegalStateException.class, () -> {
            List<DatabaseItem> results = dummyInvalidTable.getPagedResultSet(0, Person::new);
        });
    }

    @Test
    void deleteWithIdSuccess() {
        DummyTable dummyTable = new DummyTable();
        Person person = new Person(
                "fakename",
                "fakelast",
                new Date(2020, 5, 4),
                "85958435",
                "fakestuser",
                "fakeuser@sandiego.edu",
                "fake@gmail.com",
                true,
                new Department(1)
        );
        DummyTable.deleteWithId(person.getId(), dummyTable.getTableName());

        assertThrows(IllegalArgumentException.class, () -> {
           Person person1 = new Person(person.getId());
        });
    }

    @Test
    void deleteWithIdFailure() {
        DummyTable dummyTable = new DummyTable();

        assertThrows(IllegalStateException.class, () -> {
            DummyTable.deleteWithId(-1, dummyTable.getTableName());
        });
    }
}
