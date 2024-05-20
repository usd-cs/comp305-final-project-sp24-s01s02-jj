package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.enums.DegreeType;
import edu.sandiego.comp305.sp24.schoolSim.model.Alumni;
import edu.sandiego.comp305.sp24.schoolSim.model.Building;
import edu.sandiego.comp305.sp24.schoolSim.model.Department;
import edu.sandiego.comp305.sp24.schoolSim.model.Room;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomTableTest {
    ArrayList<Room> singleRoomList = new ArrayList<>();
    ArrayList<Room> twoRoomList = new ArrayList<>();
    ArrayList<Room> emptyList = new ArrayList<>();

    Room realRoom = new Room(1);
    Room drSatOffice = new Room(61);
    Room evilRoom = new Room(45);
    @BeforeAll
    static void beforeAll() {
        Config.initialize("config.properties");
    }
    @Test
    void getAllWithBuildingIDNoResult() {
        RoomTable table = new RoomTable();
        List<Room> resultsList = table.getAllWithBuildingID("400000");
        assertEquals(emptyList, resultsList);
    }

    @Test
    void getAllWithBuildingIDOneResult() {
        RoomTable table = new RoomTable();
        List<Room> resultsList = table.getAllWithBuildingID("1");
        singleRoomList.add(realRoom);
        assertEquals(resultsList.size(),singleRoomList.size());
        assertEquals(resultsList.get(0).getId(),singleRoomList.get(0).getId());

    }

    @Test
    void getAllWithBuildingIDTwoResult() {
        RoomTable table = new RoomTable();
        List<Room> resultsList = table.getAllWithBuildingID("45");
        twoRoomList.add(evilRoom);
        twoRoomList.add(drSatOffice);
        assertEquals(resultsList.size(),twoRoomList.size());
        assertEquals(resultsList.get(0).getId(),twoRoomList.get(0).getId());
        assertEquals(resultsList.get(1).getId(),twoRoomList.get(1).getId());
    }

    @Test
    void getAllWithRoomNumberNoResult() {
        RoomTable table = new RoomTable();
        List<Room> resultsList = table.getAllWithRoomNumber("400000");
        assertEquals(emptyList, resultsList);
    }

    @Test
    void getAllWithRoomNumberOneResult() {
        RoomTable table = new RoomTable();
        List<Room> resultsList = table.getAllWithRoomNumber("13");
        singleRoomList.add(evilRoom);
        assertEquals(resultsList.size(),singleRoomList.size());
        assertEquals(resultsList.get(0).getId(),singleRoomList.get(0).getId());
    }
    @Test
    void getAllWithRoomNumberTwoResult() {
        RoomTable table = new RoomTable();
        List<Room> resultsList = table.getAllWithRoomNumber("102");
        twoRoomList.add(realRoom);
        twoRoomList.add(drSatOffice);
        assertEquals(resultsList.size(),twoRoomList.size());
        assertEquals(resultsList.get(0).getId(),twoRoomList.get(0).getId());
        assertEquals(resultsList.get(1).getId(),twoRoomList.get(1).getId());
    }

    @Test
    void deleteFromDatabaseExists() {
        RoomTable roomTable = new RoomTable();

        Room room = new Room(
                new Building(1),
                100
        );

        roomTable.deleteFromDatabase(room.getId());

        assertThrows(IllegalArgumentException.class, () -> {
            Room room1 = new Room(room.getId());
        });
    }

    @Test
    void deleteFromDatabaseDoesntExist() {
        RoomTable roomTable = new RoomTable();

        assertThrows(IllegalStateException.class, () -> {
            roomTable.deleteFromDatabase(-1);
        });
    }
}