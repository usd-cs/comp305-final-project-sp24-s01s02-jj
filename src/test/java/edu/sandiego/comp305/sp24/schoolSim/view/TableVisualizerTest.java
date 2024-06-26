package edu.sandiego.comp305.sp24.schoolSim.view;

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
        when(fakeTable.getTableName()).thenReturn("FakeTable");

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
        String expected = "<table class=\"table is-fullwidth is-striped\"><thead><tr><th class=\"is-size-7\">id</th><th class=\"is-size-7\">name</th><th class=\"is-size-7\">birthdate</th></tr></thead><tbody><tr><th class=\"is-size-7\">1</th><td class=\"is-size-7\">Looper</td><td class=\"is-size-7\">07-01-1970</td></tr><tr><th class=\"is-size-7\">2</th><td class=\"is-size-7\">Souper</td><td class=\"is-size-7\">07-02-1970</td></tr></tbody></table>";
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

    @Test
    void generateErrorContent() {
        String message = "Test Error";
        String expected = "<div class=\"notification is-danger\"><button class=\"delete\"></button>Test Error</div>";
        assertEquals(expected, TableVisualizer.generateErrorContent(message));
    }
}