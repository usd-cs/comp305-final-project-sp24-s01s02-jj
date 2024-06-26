package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Database;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseItem;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseTable;
import edu.sandiego.comp305.sp24.schoolSim.model.Person;

import java.sql.*;
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
            throw new IllegalStateException("Error accessing table: " + e.getMessage());
        }
    }

    /**
     * Returns a set of databaseItems based on the given page number. The objects are created using the passed
     * idConstructor method which should be a DatabaseItem constructor that takes a single long id as parameter.
     * The item should already exist in the database.
     *
     * e.x.
     * class MyItem implements DatabaseItem {
     *     public myItem(long id) {
     *         ...
     *     }
     * }
     * ---
     * getPagedResultSet(0, MyItem::new) // Get page 0 of this table where the instances of DatabaseItem are constructed
     *                                   // with myItem's constructor.
     *
     * @param pageNumber The page number to retrieve
     * @param idConstructor The constructor to use to make instances of DatabaseItem. Expects a single long id parameter. Item needs to already exist in database.
     * @return A list of databaseItems for this page
     */
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
            throw new IllegalStateException("Error accessing table: " + e.getMessage());
        }
        return items;
    }

    protected static void deleteWithId(long id, String tableName) {
        try {
            String sql = "DELETE FROM " + tableName + " WHERE `id` = ?";

            PreparedStatement preparedStatement = Database.getInstance().getDatabaseConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Table entry not deleted");
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Table entry not deleted: " + e.getMessage());
        }
    }

    public abstract void deleteFromDatabase(long id);
}
