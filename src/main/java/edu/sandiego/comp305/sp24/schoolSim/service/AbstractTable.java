package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Database;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseItem;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseTable;
import edu.sandiego.comp305.sp24.schoolSim.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class AbstractTable implements DatabaseTable {
    @Override
    public long getCountTableRows() {
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = String.format("SELECT COUNT(*) FROM %s", getTableName());
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

    protected List<DatabaseItem> getPagedResultSet(int pageNumber, Function<? super Long, ? extends DatabaseItem> idConstructor) {
        ResultSet resultSet = null;
        Connection connection = Database.getInstance().getDatabaseConnection();
        List<DatabaseItem> items  = new ArrayList<>();

        String query = String.format("SELECT * FROM %s ORDER by id LIMIT ?,?", this.getTableName());
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, PAGE_SIZE*pageNumber);
            preparedStatement.setInt(2, PAGE_SIZE);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                DatabaseItem item = idConstructor.apply(id);
                items.add(item);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }
}
