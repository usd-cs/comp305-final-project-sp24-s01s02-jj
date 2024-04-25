package edu.sandiego.comp305.sp24.schoolSim.model;

import java.util.List;
import java.util.Optional;

public class Department implements DatabaseItem {

    @Override
    public boolean addToDatabase() {
        return false;
    }

    @Override
    public boolean removeFromDatabase() {
        return false;
    }

    @Override
    public boolean updateFromDatabase(String query) {
        return false;
    }

    @Override
    public Optional<DatabaseItem> fetchFromDatabase(int id) {
        return Optional.empty();
    }

    @Override
    public List<DatabaseItem> queryDatabase(String query) {
        return null;
    }
}
