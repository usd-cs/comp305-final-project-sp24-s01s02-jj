package edu.sandiego.comp305.sp24.schoolSim;

import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseTable;
import edu.sandiego.comp305.sp24.schoolSim.service.*;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final int NETWORK_TIMEOUT = 10000;
    private static Database instance;
    private Connection databaseConnection;
    private List<DatabaseTable> tables;

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
            if (!testHostConnection(Config.getInstance().getDatabaseHost())) {
                throw new SQLException("Unable to connect to host!");
            }

            this.databaseConnection = DriverManager.getConnection(url, Config.getInstance().getDatabaseUsername(), Config.getInstance().getDatabasePassword());
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        tables = new ArrayList<>();
        tables.add(new PersonTable());
        tables.add(new AlumniTable());
        tables.add(new EmployeeTable());
        tables.add(new FacultyTable());
        tables.add(new StudentTable());
        tables.add(new DepartmentTable());
        tables.add(new BuildingTable());
        tables.add(new RoomTable());

    }

    private boolean testHostConnection(String host) {
        try {
            InetAddress addr = InetAddress.getByName(host);
            if (addr.isReachable(NETWORK_TIMEOUT)) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    public Connection getDatabaseConnection() {
        return databaseConnection;
    }

    public List<DatabaseTable> getTables() {
        return this.tables;
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
