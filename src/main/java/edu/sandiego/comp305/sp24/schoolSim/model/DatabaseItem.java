package edu.sandiego.comp305.sp24.schoolSim.model;

import java.util.List;
import java.util.Optional;

public interface DatabaseItem {
    boolean addToDatabase();
    boolean removeFromDatabase();
    boolean updateFromDatabase(String query);
    Optional<DatabaseItem> fetchFromDatabase(int id);
    List<DatabaseItem> queryDatabase(String query);
}
