package edu.sandiego.comp305.sp24.schoolSim.view;

import edu.sandiego.comp305.sp24.schoolSim.Database;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseTable;
import edu.sandiego.comp305.sp24.schoolSim.model.Department;
import edu.sandiego.comp305.sp24.schoolSim.model.Employee;
import edu.sandiego.comp305.sp24.schoolSim.model.Person;

import jakarta.validation.constraints.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonForm extends AbstractForm {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Past
    @NotNull
    private Date birthDate;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String username;
    @NotNull
    @Email
    private String organizationEmail;
    @Email
    private String secondaryEmail;
    @NotNull
    private boolean isActive;
    @NotNull
    private String department;

    public PersonForm(String firstName,
                  String lastName,
                  Date birthdate,
                  String phoneNumber,
                  String username,
                  String organizationEmail,
                  String secondaryEmail,
                  boolean isActive,
                  String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthdate;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.organizationEmail = organizationEmail;
        this.secondaryEmail = secondaryEmail;
        this.isActive = isActive;
        this.department = department;
    }

    public void build() {
        new Person(getFirstName(),
                getLastName(),
                getBirthDate(),
                getPhoneNumber(),
                getUsername(),
                getOrganizationEmail(),
                getSecondaryEmail(),
                isActive(),
                getDepartment());
    }

    @Override
    public String toBulmaForm(String postPath) {
        return "";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
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
        // Uncomment once departmentTable exists
        // DatabaseTable departmentTable = new DepartmentTable();
        String query = String.format("SELECT id FROM %s WHERE name = ?", "Department");
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, this.department);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                long id = resultSet.getLong("id");
                return new Department(id);
            } else {
                return null;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
