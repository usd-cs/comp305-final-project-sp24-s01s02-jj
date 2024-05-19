package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Database;
import edu.sandiego.comp305.sp24.schoolSim.enums.Grade;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseItem;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseTable;
import edu.sandiego.comp305.sp24.schoolSim.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentTable extends AbstractTable {
    public List<Student> getAllWithMajor(String major){
        List<Student> resultList = new ArrayList<>();
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Student WHERE major = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, major);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");

                Student student = new Student(id);
                resultList.add(student);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return resultList;
    }

    public List<Student> getAllInGrade(Grade grade){
        List<Student> resultList = new ArrayList<>();
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Student WHERE grade = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int gradeValue = grade.ordinal() + 1;
            String currentGrade = String.valueOf(gradeValue);
            preparedStatement.setString(1, currentGrade);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");

                Student student = new Student(id);
                resultList.add(student);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public String getTableName() {
        return "Student";
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
        columnNames.add("Major");
        columnNames.add("Grade");
        return columnNames;
    }

    @Override
    public List<DatabaseItem> getAllPaged(int pageNumber) {
        // Passes student's from id constructor to create Student instances
        return getPagedResultSet(pageNumber, Student::new);
    }
}