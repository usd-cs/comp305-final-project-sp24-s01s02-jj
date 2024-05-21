package edu.sandiego.comp305.sp24.schoolSim.controller;

import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseTable;
import edu.sandiego.comp305.sp24.schoolSim.service.BuildingTable;
import edu.sandiego.comp305.sp24.schoolSim.service.RoomTable;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PlacesFormControllerTest {

    @Test
    void getTableFromKeyEmptyKey() {
        PlacesFormController controller = new PlacesFormController();
        DatabaseTable table = controller.getTableFromKey(Optional.empty());
        assertInstanceOf(BuildingTable.class, table);
    }

    @Test
    void getTableFromKeyInvalidKey() {
        PlacesFormController controller = new PlacesFormController();
        DatabaseTable table = controller.getTableFromKey(Optional.of("Random key"));
        assertInstanceOf(BuildingTable.class, table);
    }

    @Test
    void getTableFromKeyRoomKey() {
        PlacesFormController controller = new PlacesFormController();
        DatabaseTable table = controller.getTableFromKey(Optional.of("room"));
        assertInstanceOf(RoomTable.class, table);
    }
}