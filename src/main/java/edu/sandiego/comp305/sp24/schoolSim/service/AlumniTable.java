package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Database;
import edu.sandiego.comp305.sp24.schoolSim.enums.DegreeType;
import edu.sandiego.comp305.sp24.schoolSim.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
public class AlumniTable extends AbstractTable {
    public List<Alumni> getAllWithDegreeType(DegreeType degreeType){
        List<Alumni> resultList = new ArrayList<>();
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Alumni WHERE degree_type = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int degreeValue = degreeType.ordinal() + 1;
            String degree = String.valueOf(degreeValue);
            preparedStatement.setString(1, degree);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");

                Alumni alumn = new Alumni(id);
                resultList.add(alumn);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return resultList;

    }

    public List<Alumni> getAllWithGraduationDate(String graduationDate){
        List<Alumni> resultList = new ArrayList<>();
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Alumni WHERE graduation_date = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, graduationDate);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");

                Alumni alumn = new Alumni(id);
                resultList.add(alumn);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return resultList;

    }

    @Override
    public String getTableName() {
        return "Alumni";
    }

    @Override
    public List<String> getColumnNames() {
        List<String> columnNames = new ArrayList<>();
        columnNames.add("ID");
        columnNames.add("First Name");
        columnNames.add("Last Name");
        columnNames.add("Birthdate");
        columnNames.add("Phone Number");
        columnNames.add("Username");
        columnNames.add("Organization Email");
        columnNames.add("Secondary Email");
        columnNames.add("Is Active");
        columnNames.add("Department");
        columnNames.add("Degree Type");
        columnNames.add("Graduation Date");
        return columnNames;
    }

    @Override
    public List<DatabaseItem> getAllPaged(int pageNumber) {
        // Passes alumni's from id constructor to be used to create the objects.
        return getPagedResultSet(pageNumber, Alumni::new);
    }
}