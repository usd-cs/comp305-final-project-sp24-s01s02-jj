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

class BuildingTest {
    private static final String CONFIG_FILENAME = "config.properties";

    private static final int VALID_BUILDING_ID = 1;
    private static final String VALID_BUILDING_NAME = "Building1";
    private static final String VALID_BUILDING_ADDRESS = "1234 Building Ave";
    private static final int VALID_BUILDING_FLOORS = 3;
    private static final String VALID_BUILDING_ABBREVIATION = "BLD";

    private static final String UNIQUE_BUILDING_NAME = "uniquename";
    private static final String UNIQUE_BUILDING_ADDRESS = "uniqueaddr";
    private static final int UNIQUE_BUILDING_FLOORS = 4;
    private static final String UNIQUE_BUILDING_ABBREVIATION= "ABBR";
    private static final int INVALID_BUILDING_ID = -1;

    private static final String BUILDING_TABLE_NAME = "Building";

    @BeforeAll
    static void beforeAll() {
        Config.initialize(CONFIG_FILENAME);
    }

    @Test
    void verifyFirstBuildingValues() {
        Building building = new Building(VALID_BUILDING_ID);

        assertEquals(VALID_BUILDING_ID, building.getId());
        assertEquals(VALID_BUILDING_NAME, building.getName());
        assertEquals(VALID_BUILDING_ADDRESS, building.getAddress());
        assertEquals(VALID_BUILDING_FLOORS, building.getFloors());
        assertEquals(VALID_BUILDING_ABBREVIATION, building.getAbbreviation());
    }

    @Test
    void getBuildingInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            Building building = new Building(INVALID_BUILDING_ID);
        });
    }

    @Test
    void createNewBuildingDuplicate() {
        assertThrows(IllegalArgumentException.class, () -> {
            Building building = new Building(UNIQUE_BUILDING_NAME, UNIQUE_BUILDING_ADDRESS, UNIQUE_BUILDING_FLOORS, VALID_BUILDING_ABBREVIATION);
        });
    }

    @Test
    void createNewBuildingAndTestVariables() {
        Building building = new Building(
                UNIQUE_BUILDING_NAME,
                UNIQUE_BUILDING_ADDRESS,
                UNIQUE_BUILDING_FLOORS,
                UNIQUE_BUILDING_ABBREVIATION
        );

        assertEquals(UNIQUE_BUILDING_NAME, building.getName());
        assertEquals(UNIQUE_BUILDING_ADDRESS, building.getAddress());
        assertEquals(UNIQUE_BUILDING_FLOORS, building.getFloors());
        assertEquals(UNIQUE_BUILDING_ABBREVIATION, building.getAbbreviation());

        deleteWithId(building.getId(), BUILDING_TABLE_NAME);
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

    @Test
    void getStringList() {
        Building valid = new Building(VALID_BUILDING_ID);
        List<String> actual = valid.getStringList();

        List<String> expected = new ArrayList<>();
        expected.add(Long.toString(VALID_BUILDING_ID));
        expected.add(valid.getName());
        expected.add(valid.getAddress());
        expected.add(Integer.toString(valid.getFloors()));
        expected.add(valid.getAbbreviation());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}