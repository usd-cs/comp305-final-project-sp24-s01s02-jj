package edu.sandiego.comp305.sp24.schoolSim.model;

import edu.sandiego.comp305.sp24.schoolSim.Database;
import edu.sandiego.comp305.sp24.schoolSim.enums.Grade;

import java.sql.*;


public class Student extends Person {
    private String major;
    private Grade grade;

    public Student(int id) {
        super(id);

        try {
            PreparedStatement preparedStatement = Database.getInstance().getDatabaseConnection().prepareStatement("SELECT * FROM Student WHERE id=?");
            preparedStatement.setLong(1, getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.major = resultSet.getString("major");
                this.grade = Grade.fromId(resultSet.getInt("grade"));
            } else {
                throw new SQLException("No entries found with id " + getId());
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("SQL Error: " + e.getMessage());
        }
    }

    public Student(String firstName, String lastName, Date birthdate, String phoneNumber, String username, String organizationEmail, String secondaryEmail, boolean isActive, Department department, String major, Grade grade) {
        super(firstName, lastName, birthdate, phoneNumber, username, organizationEmail, secondaryEmail, isActive, department);
        this.major = major;
        this.grade = grade;

        String sql = "INSERT INTO Student (id, major, grade) " +
                "VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = Database.getInstance().getDatabaseConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, getId());
            preparedStatement.setString(2, getMajor());
            preparedStatement.setInt(3, getGrade().getId());

            preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Person entry not found");
        }
    }
    public String getMajor(){
        return major;
    }
    public Grade getGrade() {
        return grade;
    }
}