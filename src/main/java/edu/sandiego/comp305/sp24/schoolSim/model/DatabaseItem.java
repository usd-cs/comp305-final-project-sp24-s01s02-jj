package edu.sandiego.comp305.sp24.schoolSim.model;

import java.util.List;

public interface DatabaseItem {
    DatabaseTable getParentTable();
    List<String> getStringList();
}
