package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Database;
import edu.sandiego.comp305.sp24.schoolSim.model.Alumni;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseTable;
import edu.sandiego.comp305.sp24.schoolSim.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
public class AlumniTable implements DatabaseTable {
    public List<Alumni> getAllWithDegreeType(String degreeType){
        List<Alumni> resultList = new ArrayList<>();
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Alumni WHERE degree_type = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, degreeType);
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
    public long getCountTableRows() {
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT COUNT(*) FROM Alumni";
        try{
            resultSet = connection.prepareStatement(query).executeQuery();
            if(resultSet.next()) {
                return resultSet.getLong(1);
            } else {
                return -1;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
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
}