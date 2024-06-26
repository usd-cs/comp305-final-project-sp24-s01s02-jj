package edu.sandiego.comp305.sp24.schoolSim.model;

import edu.sandiego.comp305.sp24.schoolSim.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Room implements DatabaseItem{
    private long id;
    private Building building;
    private int roomNumber;

    public Room(long id) {
        this.id = id;

        String sql = "SELECT * FROM Room WHERE id=?";
        try {
            PreparedStatement preparedStatement = Database.getInstance().getDatabaseConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.building = new Building(resultSet.getInt("building"));
                this.roomNumber = resultSet.getInt("room_number");
            } else {
                throw new SQLException("No entries found with id " + id);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("SQL Error: " + e.getMessage());
        }
    }

    public Room(Building building, int roomNumber) {
        this.building = building;
        this.roomNumber = roomNumber;

        String sql = "INSERT INTO Room (building, room_number) " +
                "VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = Database.getInstance().getDatabaseConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, building.getId());
            preparedStatement.setInt(2, roomNumber);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating room failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.id = (int) generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating room failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Room entry not found");
        }
    }

    public long getId() {
        return id;
    }
    public Building getBuilding() {
        return building;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    @Override
    public DatabaseTable getParentTable() {
        // Return RoomTable once it exists
        return null;
    }

    @Override
    public List<String> getStringList() {
        List<String> myAttributes = new ArrayList<>();
        myAttributes.add(Long.toString(getId()));
        myAttributes.add(getBuilding().toString());
        myAttributes.add(Integer.toString(getRoomNumber()));
        return myAttributes;
    }

    @Override
    public String toString() {
        return String.format("%s %d", getBuilding().toString(), getRoomNumber());
    }
}