package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Database;
import edu.sandiego.comp305.sp24.schoolSim.model.Person;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonService {
    public static List<Person> getAllWithFirstName(String firstName){
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
    public static List<Person> getAllWithLastName(String lastName) {
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
    public static List<Person> getAllWithPhoneNumber(String phoneNumber) {
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
    public static Optional<Person> getByUsername(String username) {
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
    public static Optional<Person> getByOrganizationEmail(String organizationEmail) {
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
    public static Optional<Person> getBySecondaryEmail(String secondaryEmail) {
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
    public static List<Person> getActivePeople() {
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
    public static List<Person> getInactivePeople() {
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
}
