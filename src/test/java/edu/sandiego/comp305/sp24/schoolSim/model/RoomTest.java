package edu.sandiego.comp305.sp24.schoolSim.model;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.Database;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    private static final int VALID_ROOM_ID = 1;
    private static final int VALID_ROOM_BUILDING_ID = 1;
    private static final int VALID_ROOM_NUMBER = 102;

    private static final int INVALID_ROOM_ID = -1;

    @BeforeAll
    static void beforeAll() {
        Config.initialize("config.properties");
    }


    @Test
    void verifyFirstRoomVariables() {
        Room room = new Room(VALID_ROOM_ID);

        assertEquals(VALID_ROOM_ID, room.getId());
        assertEquals(VALID_ROOM_BUILDING_ID, room.getBuilding().getId());
        assertEquals(VALID_ROOM_NUMBER, room.getRoomNumber());
    }

    @Test
    void getRoomInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            Room room = new Room(INVALID_ROOM_ID);
        });
    }

    @Test
    void createNewBuildingAndTestVariables() {
        Room room = new Room(
                new Building(VALID_ROOM_BUILDING_ID),
                VALID_ROOM_NUMBER
        );

        assertEquals(VALID_ROOM_BUILDING_ID, room.getBuilding().getId());
        assertEquals(VALID_ROOM_NUMBER, room.getRoomNumber());

        deleteWithId(room.getId(), "Room");
    }

    void deleteWithId(long id, String table) {
        try {
            String sql = "DELETE FROM " + table + " WHERE `id` = ?";

            PreparedStatement preparedStatement = Database.getInstance().getDatabaseConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("User not deleted");
            }
        } catch (SQLException e) {
            throw new IllegalStateException("User couldn't be deleted: " + e.getMessage());
        }
    }
}