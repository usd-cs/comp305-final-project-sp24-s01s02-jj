package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.DatabaseTest;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseItem;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseTable;
import edu.sandiego.comp305.sp24.schoolSim.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    }



    @BeforeAll
    static void beforeAll() {
        Config.initialize(CONFIG_FILENAME);
    }

    @Test
    void getCountTableRows() {
        DummyTable dummyTable = new DummyTable();
        long result = dummyTable.getCountTableRows();

        assertEquals(16, result);
    }

    @Test
    void getPagedResultSetTest() {
        DummyTable dummyTable = new DummyTable();
        List<DatabaseItem> results = dummyTable.getPagedResultSet(1, Person::new);

        assertEquals(DatabaseTable.PAGE_SIZE, results.size());
    }
}
