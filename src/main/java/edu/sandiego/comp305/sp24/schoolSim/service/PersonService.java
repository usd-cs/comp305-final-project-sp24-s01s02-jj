package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Database;
import edu.sandiego.comp305.sp24.schoolSim.model.Department;
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
                String lastName = resultSet.getString("last_name");
                Date birthdate = resultSet.getDate("birthday");
                String phoneNumber = resultSet.getString("phone_number");
                String username = resultSet.getString("username");
                String organizationEmail = resultSet.getString("organization_email");
                String secondaryEmail = resultSet.getString("secondary_email");
                boolean isActive = resultSet.getBoolean("is_active");
                Department department = new Department(resultSet.getInt("department"));

                Person person = new SimplePerson(firstName, lastName, birthdate, phoneNumber, username, organizationEmail, secondaryEmail, isActive, department);
                resultList.add(person);
                }

            } catch (SQLException e){
                e.printStackTrace();
        }

        return resultList;
    }


    public static List<Person> getAllWithLastName(String lastName) {
        return null;
    }
    public static List<Person> getAllWithPhoneNumber(String phoneNumber) {
        return null;
    }
    public static Optional<Person> getByUsername(String username) {
        return null;
    }
    public static Optional<Person> getByOrganizationEmail(String organizationEmail) {
        return null;
    }
    public static Optional<Person> getBySecondaryEmail(String secondaryEmail) {
        return null;
    }
    public static List<Person> getActivePeople() {
        return null;
    }
    public static List<Person> getInactivePeople() {
        return null;
    }
}
