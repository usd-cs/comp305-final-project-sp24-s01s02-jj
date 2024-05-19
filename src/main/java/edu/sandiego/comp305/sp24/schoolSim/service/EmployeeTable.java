package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Database;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseTable;
import edu.sandiego.comp305.sp24.schoolSim.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTable implements DatabaseTable {
    public List<Employee> getAllWithStartDate(String startDate){
        List<Employee> resultList = new ArrayList<>();
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Employee WHERE start_date = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, startDate);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");

                Employee employee = new Employee(id);
                resultList.add(employee);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return resultList;
    }

    public List<Employee> getAllWithHourlyWage(String hourlyWage){
        List<Employee> resultList = new ArrayList<>();
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Employee WHERE hourly_wage = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, hourlyWage);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");

                Employee employee = new Employee(id);
                resultList.add(employee);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return resultList;
    }

    public List<Employee> getAllWithMangerID(String managerID){
        List<Employee> resultList = new ArrayList<>();
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Employee WHERE manager = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, managerID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");

                Employee employee = new Employee(id);
                resultList.add(employee);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public String getTableName() {
        return "Employee";
    }

    @Override
    public long getCountTableRows() {
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT COUNT(*) FROM Employee";
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
        columnNames.add("Start Date");
        columnNames.add("Hourly Wage");
        columnNames.add("Manager");
        return columnNames;
    }
}