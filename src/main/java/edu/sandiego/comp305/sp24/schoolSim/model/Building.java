package edu.sandiego.comp305.sp24.schoolSim.model;

import edu.sandiego.comp305.sp24.schoolSim.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Building implements DatabaseItem{
    private long id;
    private String name;
    private String address;
    private int floors;
    private String abbreviation;


    public Building(long id) {
        this.id = id;

        String sql = "SELECT * FROM Building WHERE id=?";
        try {
            PreparedStatement preparedStatement = Database.getInstance().getDatabaseConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.name = resultSet.getString("name");
                this.address = resultSet.getString("address");
                this.floors = resultSet.getInt("floors");
                this.abbreviation = resultSet.getString("abbreviation");
            } else {
                throw new SQLException("No entries found with id " + id);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("SQL Error: " + e.getMessage());
        }
    }

    public Building(String name, String address, int floors, String abbreviation) {
        this.name = name;
        this.address = address;
        this.floors = floors;
        this.abbreviation = abbreviation;

        String sql = "INSERT INTO Building (`name`, `address`, `floors`, `abbreviation`) " +
                "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = Database.getInstance().getDatabaseConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setInt(3, floors);
            preparedStatement.setString(4, abbreviation);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating building failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.id = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating building failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Building entry not found");
        }
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getFloors() {
        return floors;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    @Override
    public DatabaseTable getParentTable() {
        // Return BuildingTable once it exists
        return null;
    }

    @Override
    public List<String> getStringList() {
        List<String> myAttributes = new ArrayList<>();
        myAttributes.add(Long.toString(getId()));
        myAttributes.add(getName());
        myAttributes.add(getAddress());
        myAttributes.add(Integer.toString(getFloors()));
        myAttributes.add(getAbbreviation());
        return myAttributes;
    }

    @Override
    public String toString() {
        return getAbbreviation();
    }
}