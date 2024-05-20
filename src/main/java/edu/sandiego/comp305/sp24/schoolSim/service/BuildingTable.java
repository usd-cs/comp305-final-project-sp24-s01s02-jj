package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Database;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseItem;
import edu.sandiego.comp305.sp24.schoolSim.model.Building;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BuildingTable extends AbstractTable{

    public Optional<Building> getByName(String name){
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Building WHERE name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                long id = resultSet.getLong("id");

                Building building = new Building(id);
                return Optional.of(building);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Building> getAllWithAddress(String address){
        List<Building> resultList = new ArrayList<>();
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Building WHERE address = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, address);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");

                Building building = new Building(id);
                resultList.add(building);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return resultList;
    }

    public List<Building> getAllWithMoreFloors(int totalFloors) {
        String numOfFloors = String.valueOf(totalFloors);
        List<Building> resultList = new ArrayList<>();
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Building WHERE floors > ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, numOfFloors);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");

                Building building = new Building(id);
                resultList.add(building);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }


    public List<Building> getAllWithLessFloors(int totalFloors){
        String numOfFloors = String.valueOf(totalFloors);
        List<Building> resultList = new ArrayList<>();
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Building WHERE floors < ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, numOfFloors);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");

                Building building = new Building(id);
                resultList.add(building);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public Optional<Building> getByAbbreviation(String abbreviation){
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Building WHERE abbreviation = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, abbreviation);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                long id = resultSet.getLong("id");

                Building building = new Building(id);
                return Optional.of(building);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }


    @Override
    public String getTableName() {
        return "Building";
    }

    @Override
    public List<String> getColumnNames() {
        List<String> columnNames = new ArrayList<>();
        columnNames.add("ID");
        columnNames.add("Name");
        columnNames.add("Address");
        columnNames.add("Floors");
        columnNames.add("Abbreviation");
        return columnNames;
    }

    @Override
    public List<DatabaseItem> getAllPaged(int pageNumber) {
        return getPagedResultSet(pageNumber, Building::new);
    }

    @Override
    public void deleteFromDatabase(long id) {
        AbstractTable.deleteWithId(id, getTableName());
    }
}