package edu.sandiego.comp305.sp24.schoolSim.controller;

import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseItem;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseTable;
import edu.sandiego.comp305.sp24.schoolSim.view.TableVisualizer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TableVisualizerTest {
    @Test
    void generateTableView() {
        DatabaseTable fakeTable = mock(DatabaseTable.class);
        List<String> fakeColNames = new ArrayList<>();
        fakeColNames.add("id");
        fakeColNames.add("name");
        fakeColNames.add("birthdate");
        when(fakeTable.getColumnNames()).thenReturn(fakeColNames);

        List<String> fakeRowData1 = new ArrayList<>();
        fakeRowData1.add("1");
        fakeRowData1.add("Looper");
        fakeRowData1.add("07-01-1970");
        List<String> fakeRowData2 = new ArrayList<>();
        fakeRowData2.add("2");
        fakeRowData2.add("Souper");
        fakeRowData2.add("07-02-1970");

        DatabaseItem row1 = mock(DatabaseItem.class);
        DatabaseItem row2 = mock(DatabaseItem.class);
        when(row1.getStringList()).thenReturn(fakeRowData1);
        when(row2.getStringList()).thenReturn(fakeRowData2);

        List<DatabaseItem> fakeRows = new ArrayList<>();
        fakeRows.add(row1);
        fakeRows.add(row2);
        String table = TableVisualizer.generateTableView(fakeTable, fakeRows);
        String expected = "<table class=\"table\"><thead><tr><th>id</th><th>name</th><th>birthdate</th></tr></thead><tbody><tr><th>1</th><td>Looper</td><td>07-01-1970</td></tr><tr><th>2</th><td>Souper</td><td>07-02-1970</td></tr></tbody></table>";
        assertEquals(expected, table, "Expected table layout not generated");
    }

    @Test
    void generateTableStatbox() {
        DatabaseTable fakeTable = mock(DatabaseTable.class);
        when(fakeTable.getTableName()).thenReturn("FakeTable");
        when(fakeTable.getCountTableRows()).thenReturn((long)25);

        String expected = "<article class=\"message is-info cell\"><div class=\"message-header\">FakeTable</div><div class=\"message-body\">Number of Rows: 25</div></article>";
        assertEquals(expected, TableVisualizer.generateTableStatbox(fakeTable), "Expected statbox layout not generated");
    }
}