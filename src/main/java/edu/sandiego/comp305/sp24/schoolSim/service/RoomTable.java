package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Database;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseItem;
import edu.sandiego.comp305.sp24.schoolSim.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomTable extends AbstractTable {
    public List<Room> getAllWithBuildingID(String buildingID){
        List<Room> resultList = new ArrayList<>();
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Room WHERE building = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, buildingID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");

                Room room = new Room(id);
                resultList.add(room);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return resultList;
    }

    public List<Room> getAllWithRoomNumber(String roomNumber){
        List<Room> resultList = new ArrayList<>();
        ResultSet resultSet;
        Connection connection = Database.getInstance().getDatabaseConnection();

        String query = "SELECT * FROM Room WHERE room_number = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, roomNumber);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");

                Room room = new Room(id);
                resultList.add(room);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return resultList;
    }

    @Override
    public String getTableName() {
        return "Room";
    }

    @Override
    public List<String> getColumnNames() {
        List<String> columnNames = new ArrayList<>();
        columnNames.add("ID");
        columnNames.add("Building");
        columnNames.add("Room Number");
        return columnNames;
    }

    @Override
    public List<DatabaseItem> getAllPaged(int pageNumber) {
        return getPagedResultSet(pageNumber, Room::new);
    }
}