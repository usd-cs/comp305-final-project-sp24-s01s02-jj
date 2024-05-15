package edu.sandiego.comp305.sp24.schoolSim.model;

import edu.sandiego.comp305.sp24.schoolSim.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Department implements DatabaseItem{
    private long id;
    private String name;

    public Department(long id) {
        this.id = id;
        try {
            PreparedStatement preparedStatement = Database.getInstance().getDatabaseConnection().prepareStatement("SELECT * FROM Department WHERE id=?");
            preparedStatement.setLong(1, getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.name = resultSet.getString("name");
            } else {
                throw new SQLException("No entries found with id " + getId());
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("SQL Error: " + e.getMessage());
        }
    }

    public Department(String name) {
        this.name = name;
        String sql = "INSERT INTO Department (name) " +
                "VALUES (?)";

        try {
            PreparedStatement preparedStatement = Database.getInstance().getDatabaseConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating department failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.id = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating department failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Department entry not found");
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public DatabaseTable getParentTable() {
        return null;
    }

    @Override
    public List<String> getStringList() {
        List<String> myAttributes = new ArrayList<>();
        myAttributes.add(Long.toString(getId()));
        myAttributes.add(getName());
        return myAttributes;
    }
}
