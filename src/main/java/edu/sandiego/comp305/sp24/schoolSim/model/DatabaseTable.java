package edu.sandiego.comp305.sp24.schoolSim.model;

import java.util.List;

public interface DatabaseTable {
    String getTableName();
    long getCountTableRows();
    List<String> getColumnNames();

}
