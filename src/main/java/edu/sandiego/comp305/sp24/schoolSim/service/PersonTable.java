package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Database;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseTable;
import edu.sandiego.comp305.sp24.schoolSim.model.Person;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonTable implements DatabaseTable {
    public List<Person> getAllWithFirstName(String firstName){
        List<Person> resultList = new ArrayList<>();

        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Person WHERE first_name = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");

                Person person = new SimplePerson(id);
                resultList.add(person);
                }

            } catch (SQLException e){
                e.printStackTrace();
        }

        return resultList;
    }
    public List<Person> getAllWithLastName(String lastName) {
        List<Person> resultList = new ArrayList<>();

        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Person WHERE last_name = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, lastName);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");

                Person person = new SimplePerson(id);
                resultList.add(person);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return resultList;
    }
    public List<Person> getAllWithPhoneNumber(String phoneNumber) {
        List<Person> resultList = new ArrayList<>();

        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Person WHERE phone_number = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, phoneNumber);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");

                Person person = new SimplePerson(id);
                resultList.add(person);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return resultList;
    }
    public Optional<Person> getByUsername(String username) {
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Person WHERE username = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                long id = resultSet.getLong("id");

                Person person = new SimplePerson(id);
                return Optional.of(person);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public Optional<Person> getByOrganizationEmail(String organizationEmail) {
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Person WHERE organization_email = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, organizationEmail);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                long id = resultSet.getLong("id");

                Person person = new SimplePerson(id);
                return Optional.of(person);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public Optional<Person> getBySecondaryEmail(String secondaryEmail) {
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Person WHERE secondary_email = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, secondaryEmail);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                long id = resultSet.getLong("id");

                Person person = new SimplePerson(id);
                return Optional.of(person);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public List<Person> getActivePeople() {
        List<Person> resultList = new ArrayList<>();

        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Person WHERE is_active = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, true);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");

                Person person = new SimplePerson(id);
                resultList.add(person);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return resultList;
    }
    public List<Person> getInactivePeople() {
        List<Person> resultList = new ArrayList<>();

        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Person WHERE is_active = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, false);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");

                Person person = new SimplePerson(id);
                resultList.add(person);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return resultList;
    }

    public List<Person> getAllPaged(int pageNumber) {
        List<Person> resultList = new ArrayList<>();

        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Person ORDER BY id LIMIT ?, ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(2, PAGE_SIZE);
            preparedStatement.setInt(1, PAGE_SIZE*pageNumber);
            System.out.println(preparedStatement.toString());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");

                Person person = new SimplePerson(id);
                resultList.add(person);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public String getTableName() {
        return "Person";
    }

    @Override
    public long getCountTableRows() {
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT COUNT(*) FROM Person";
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
        return columnNames;
    }
}
