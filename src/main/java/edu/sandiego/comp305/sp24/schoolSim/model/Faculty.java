package edu.sandiego.comp305.sp24.schoolSim.model;

import edu.sandiego.comp305.sp24.schoolSim.Database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Faculty extends Employee {
    private Room officeLocation;
    private boolean hasTenure;

    public Faculty(long id) {
        super(id);

        String sql = "SELECT * FROM Faculty WHERE id=?";
        try {
            PreparedStatement preparedStatement = Database.getInstance().getDatabaseConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.officeLocation = new Room(resultSet.getInt("office_location"));
                this.hasTenure = resultSet.getBoolean("has_tenure");
            } else {
                throw new SQLException("No entries found with id " + id);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("SQL Error: " + e.getMessage());
        }
    }

    public Faculty(String firstName,
                   String lastName,
                   Date birthdate,
                   String phoneNumber,
                   String username,
                   String organizationEmail,
                   String secondaryEmail,
                   boolean isActive,
                   Department department,
                   Date startDate,
                   double hourlyWage,
                   Employee manager,
                   Room officeLocation,
                   boolean hasTenure) {
        super(firstName, lastName, birthdate, phoneNumber, username, organizationEmail, secondaryEmail, isActive, department, startDate, hourlyWage, manager);
        this.officeLocation = officeLocation;
        this.hasTenure = hasTenure;

        String sql = "INSERT INTO Faculty (id, office_location, has_tenure) " +
                "VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = Database.getInstance().getDatabaseConnection().prepareStatement(sql);
            preparedStatement.setLong(1, getId());
            preparedStatement.setLong(2, officeLocation.getId());
            preparedStatement.setBoolean(3, hasTenure);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Person entry not found");
        }
    }

    public Room getOfficeLocation() {
        return officeLocation;
    }

    public boolean hasTenure() {
        return hasTenure;
    }

    @Override
    public List<String> getStringList() {
        List<String> parentList = super.getStringList();
        parentList.add(getOfficeLocation().toString());
        parentList.add(Boolean.toString(hasTenure()));
        return parentList;
    }
}