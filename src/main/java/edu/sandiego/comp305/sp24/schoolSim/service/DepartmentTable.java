package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Database;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseItem;
import edu.sandiego.comp305.sp24.schoolSim.model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentTable extends AbstractTable {
    public Optional<Department> getByName(String name){
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Department WHERE name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                long id = resultSet.getLong("id");

                Department department = new Department(id);
                return Optional.of(department);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }


    @Override
    public String getTableName() {
        return "Department";
    }

    @Override
    public List<String> getColumnNames() {
        List<String> columnNames = new ArrayList<>();
        columnNames.add("ID");
        columnNames.add("Name");
        return columnNames;
    }

    @Override
    public List<DatabaseItem> getAllPaged(int pageNumber) {
        return getPagedResultSet(pageNumber, Department::new);
    }
}