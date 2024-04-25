package edu.sandiego.comp305.sp24.schoolSim;

import java.sql.Connection;

public class Database {
    private Database instance;
    private Connection databaseConnection;

    private Database() {

    }

    public Database getInstance() {
        return null;
    }

    public Connection getDatabaseConnection() {
        return databaseConnection;
    }
}
