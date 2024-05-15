package edu.sandiego.comp305.sp24.schoolSim.model;

import edu.sandiego.comp305.sp24.schoolSim.Database;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class Employee extends Person {
    private Optional<Employee> manager;
    private Date startDate;
    private double hourlyWage;

    public Employee(long id) {
        super(id);

        String sql = "SELECT * FROM Employee WHERE id=?";
        try {
            PreparedStatement preparedStatement = Database.getInstance().getDatabaseConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.startDate = resultSet.getDate("start_date");
                this.hourlyWage = resultSet.getDouble("hourly_wage");

                if (resultSet.getInt("manager") == 0) {
                    this.manager = Optional.empty();
                } else {
                    this.manager = Optional.of(new Employee(resultSet.getInt("manager")));
                }
            } else {
                throw new SQLException("No entries found with id " + id);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("SQL Error: " + e.getMessage());
        }
    }

    public Employee(
            String firstName,
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
            Employee manager) {
        super(firstName, lastName, birthdate, phoneNumber, username, organizationEmail, secondaryEmail, isActive, department);
        String sql = "INSERT INTO Employee (id, start_date, hourly_wage, manager) " +
                "VALUES (?, ?, ?, ?)";

        this.startDate = startDate;
        this.hourlyWage = hourlyWage;

        if (manager == null) {
            this.manager = Optional.empty();
        } else {
            this.manager = Optional.of(manager);
        }

        try {
            PreparedStatement preparedStatement = Database.getInstance().getDatabaseConnection().prepareStatement(sql);
            preparedStatement.setLong(1, getId());
            preparedStatement.setDate(2, startDate);
            preparedStatement.setDouble(3, hourlyWage);

            if (this.manager.isPresent()) {
                preparedStatement.setLong(4, this.manager.get().getId());
            } else {
                preparedStatement.setNull(4, Types.BIGINT);
            }

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Person entry not found");
        }
    }

    public Optional<Employee> getManager() {
        return manager;
    }

    public Date getStartDate() {
        return startDate;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    @Override
    public List<String> getStringList() {
        List<String> parentList = super.getStringList();
        Optional<Employee> manager = getManager();
        String managerRepresentation = "No Manager";
        if (manager.isPresent()) {
            managerRepresentation = manager.get().toString();
        }
        parentList.add(managerRepresentation);
        parentList.add(getStartDate().toString());
        parentList.add("$"+Double.toString(getHourlyWage()));
        return parentList;
    }

    @Override
    public DatabaseTable getParentTable() {
        return null;
    }
}