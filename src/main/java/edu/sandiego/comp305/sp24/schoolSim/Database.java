package edu.sandiego.comp305.sp24.schoolSim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database instance;
    private Connection databaseConnection;

    private Database() {
        initializeDatabase();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    private void initializeDatabase() {
        String url = "jdbc:mysql://" + Config.getInstance().getDatabaseHost() + ":3306/" + Config.getInstance().getDatabaseName();

        try {
            this.databaseConnection = DriverManager.getConnection(url, Config.getInstance().getDatabaseUsername(), Config.getInstance().getDatabasePassword());
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public Connection getDatabaseConnection() {
        return databaseConnection;
    }

    void reset() {
        if (databaseConnection != null) {
            try {
                databaseConnection.close();
            } catch (SQLException e) {
                // ignore
            }

        }
        instance = null;
    }
}
