package edu.sandiego.comp305.sp24.schoolSim.model;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.Database;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    private static final String CONFIG_FILENAME = "config.properties";
    private static final int ROOM_SPECIFIC_PARAMETERS = 3;

    private static final int VALID_ROOM_ID = 1;
    private static final int VALID_ROOM_BUILDING_ID = 1;
    private static final int VALID_ROOM_NUMBER = 102;

    private static final int INVALID_ROOM_ID = -1;

    @BeforeAll
    static void beforeAll() {
        Config.initialize(CONFIG_FILENAME);
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

        deleteRoomWithId(room.getId());
    }

    @Test
    void getStringList() {
        Room valid = new Room(VALID_ROOM_ID);
        List<String> actual = valid.getStringList();

        List<String> expected = new ArrayList<>();
        expected.add(Long.toString(VALID_ROOM_ID));
        expected.add(valid.getBuilding().toString());
        expected.add(Integer.toString(valid.getRoomNumber()));

        for (int i = 0; i < ROOM_SPECIFIC_PARAMETERS; i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    private static void deleteRoomWithId(long id) {
        try {
            String sql = "DELETE FROM Room WHERE `id` = ?";

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