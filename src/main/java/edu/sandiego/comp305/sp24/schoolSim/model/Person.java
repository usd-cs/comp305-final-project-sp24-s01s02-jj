package edu.sandiego.comp305.sp24.schoolSim.model;


import edu.sandiego.comp305.sp24.schoolSim.Database;

import java.sql.*;

public abstract class Person {
    private long id;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private String phoneNumber;
    private String username;
    private String organizationEmail;
    private String secondaryEmail;
    private boolean isActive;
    private Department department;

    // Obtain a Person entry from the database
    public Person(long id) {
        String sql = "SELECT * FROM Person WHERE id=?";
        try {
            PreparedStatement preparedStatement = Database.getInstance().getDatabaseConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.id = resultSet.getInt("id");
                this.firstName = resultSet.getString("first_name");
                this.lastName = resultSet.getString("last_name");
                this.birthdate = resultSet.getDate("birthdate");
                this.phoneNumber = resultSet.getString("phone_number");
                this.username = resultSet.getString("username");
                this.organizationEmail = resultSet.getString("organization_email");
                this.secondaryEmail = resultSet.getString("secondary_email");
                this.isActive = resultSet.getBoolean("is_active");
                this.department = new Department(resultSet.getInt("department"));
            } else {
                throw new SQLException("No entries found with id " + id);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("SQL Error: " + e.getMessage());
        }
    }

    // Create a new Person object and insert it into the database
    public Person(String firstName,
                  String lastName,
                  Date birthdate,
                  String phoneNumber,
                  String username,
                  String organizationEmail,
                  String secondaryEmail,
                  boolean isActive,
                  Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.organizationEmail = organizationEmail;
        this.secondaryEmail = secondaryEmail;
        this.isActive = isActive;
        this.department = department;

        String sql = "INSERT INTO Person (first_name, last_name, birthdate, phone_number, username, organization_email, secondary_email, is_active, department) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = Database.getInstance().getDatabaseConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setDate(3, birthdate);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5, username);
            preparedStatement.setString(6, organizationEmail);
            preparedStatement.setString(7, secondaryEmail);
            preparedStatement.setBoolean(8, isActive);
            preparedStatement.setLong(9, department.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.id = generatedKeys.getLong(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Person entry not found");
        }
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getOrganizationEmail() {
        return organizationEmail;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public boolean isActive() {
        return isActive;
    }

    public Department getDepartment() {
        return department;
    }
}
