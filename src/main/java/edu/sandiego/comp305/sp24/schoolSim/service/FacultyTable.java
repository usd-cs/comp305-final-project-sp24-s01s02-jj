package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Database;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseItem;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseTable;
import edu.sandiego.comp305.sp24.schoolSim.model.Faculty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultyTable extends AbstractTable {
    public List<Faculty> getAllByOfficeLocation(String officeLocation){
        List<Faculty> resultList = new ArrayList<>();
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Faculty WHERE office_location = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, officeLocation);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");

                Faculty faculty = new Faculty(id);
                resultList.add(faculty);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return resultList;
    }

    public List<Faculty> getAllWithTenure(){
        List<Faculty> resultList = new ArrayList<>();

        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Faculty WHERE has_tenure = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, true);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");

                Faculty faculty = new Faculty(id);
                resultList.add(faculty);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return resultList;
    }

    public List<Faculty> getAllWithNoTenure(){
        List<Faculty> resultList = new ArrayList<>();

        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Faculty WHERE has_tenure = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, false);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");

                Faculty faculty = new Faculty(id);
                resultList.add(faculty);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public String getTableName() {
        return "Faculty";
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
        columnNames.add("Start Date");
        columnNames.add("Hourly Wage");
        columnNames.add("Manager");
        columnNames.add("Room");
        columnNames.add("Has Tenure");
        return columnNames;
    }

    @Override
    public List<DatabaseItem> getAllPaged(int pageNumber) {
        // Passes faculty's from id constructor to be used to create the objects.
        return getPagedResultSet(pageNumber, Faculty::new);
    }

    @Override
    public void deleteFromDatabase(long id) {
        AbstractTable.deleteWithId(id, getTableName());
        AbstractTable.deleteWithId(id, "Employee");
        AbstractTable.deleteWithId(id, "Person");
    }
}