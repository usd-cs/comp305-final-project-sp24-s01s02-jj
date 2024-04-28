package edu.sandiego.comp305.sp24.schoolSim.model;

import edu.sandiego.comp305.sp24.schoolSim.Database;
import edu.sandiego.comp305.sp24.schoolSim.enums.DegreeType;

import java.sql.*;

public class Alumni extends Person {
    private Date graduationDate;
    private DegreeType degreeType;

    public Alumni(int id) {
        super(id);

        try {
            PreparedStatement preparedStatement = Database.getInstance().getDatabaseConnection().prepareStatement("SELECT * FROM Alumni WHERE id=?");
            preparedStatement.setLong(1, getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.graduationDate = resultSet.getDate("graduation_date");
                this.degreeType = DegreeType.fromId(resultSet.getInt("degree_type"));
            } else {
                throw new SQLException("No entries found with id " + getId());
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("SQL Error: " + e.getMessage());
        }
    }

    public Alumni(String firstName, String lastName, Date birthdate, String phoneNumber, String username, String organizationEmail, String secondaryEmail, boolean isActive, int department, Date graduationDate, DegreeType degreeType) {
        super(firstName, lastName, birthdate, phoneNumber, username, organizationEmail, secondaryEmail, isActive, department);
        this.graduationDate = graduationDate;
        this.degreeType = degreeType;

        String sql = "INSERT INTO Alumni (id, graduation_date, degree_type) " +
                "VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = Database.getInstance().getDatabaseConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, getId());
            preparedStatement.setDate(2, graduationDate);
            preparedStatement.setInt(3, degreeType.getId());

            preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Person entry not found");
        }
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public DegreeType getDegree() {
        return degreeType;
    }
}
