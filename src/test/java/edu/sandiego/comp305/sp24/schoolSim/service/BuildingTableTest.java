package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.Config;
import edu.sandiego.comp305.sp24.schoolSim.model.Building;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BuildingTableTest {
    ArrayList<Building> singleBuildingList = new ArrayList<>();
    ArrayList<Building> twoBuildingList = new ArrayList<>();
    ArrayList<Building> emptyList = new ArrayList<>();

    Building building1 = new Building(1);
    Building evilBuilding = new Building(45);
    Building elmosHouse = new Building(61);

    @BeforeAll
    static void beforeAll() {
        Config.initialize("config.properties");
    }
    @Test
    void getAllWithNameNoResult() {
        BuildingTable table = new BuildingTable();
        assertEquals(Optional.empty(),table.getByName("Long John Silvers"));
    }
    @Test
    void getAllWithNameGotResult() {
        BuildingTable table = new BuildingTable();
        long results = table.getByName("Evil Building").get().getId();
        assertEquals(evilBuilding.getId(), results);
    }

    @Test
    void getAllWithAddressNoResult() {
        BuildingTable table = new BuildingTable();
        List<Building> resultsList = table.getAllWithAddress("5998 Alcala Park");
        assertEquals(emptyList, resultsList);
    }

    @Test
    void getAllWithAddressOneResult() {
        BuildingTable table = new BuildingTable();
        List<Building> resultsList = table.getAllWithAddress("1234 Building Ave");
        singleBuildingList.add(building1);
        assertEquals(resultsList.size(),singleBuildingList.size());
        assertEquals(resultsList.get(0).getId(),singleBuildingList.get(0).getId());
    }

    @Test
    void getAllWithAddressTwoResult() {
        BuildingTable table = new BuildingTable();
        List<Building> resultsList = table.getAllWithAddress("123 Seasame St.");
        twoBuildingList.add(evilBuilding);
        twoBuildingList.add(elmosHouse);
        assertEquals(resultsList.size(),twoBuildingList.size());
        assertEquals(resultsList.get(0).getId(),twoBuildingList.get(0).getId());
        assertEquals(resultsList.get(1).getId(),twoBuildingList.get(1).getId());
    }

    @Test
    void getAllWithMoreFloorsNoResult() {
        BuildingTable table = new BuildingTable();
        List<Building> resultsList = table.getAllWithMoreFloors(50);
        assertEquals(emptyList, resultsList);
    }
    @Test
    void getAllWithMoreFloorsOneResult() {
        BuildingTable table = new BuildingTable();
        List<Building> resultsList = table.getAllWithMoreFloors(35);
        singleBuildingList.add(elmosHouse);
        assertEquals(singleBuildingList.size(), resultsList.size());
        assertEquals(singleBuildingList.get(0).getId(),resultsList.get(0).getId());
    }

    @Test
    void getAllWithMoreFloorsTwoResult() {
        BuildingTable table = new BuildingTable();
        List<Building> resultsList = table.getAllWithMoreFloors(6);
        twoBuildingList.add(evilBuilding);
        twoBuildingList.add(elmosHouse);
        assertEquals(resultsList.size(),twoBuildingList.size());
        assertEquals(resultsList.get(0).getId(),twoBuildingList.get(0).getId());
        assertEquals(resultsList.get(1).getId(),twoBuildingList.get(1).getId());
    }

    @Test
    void getAllWithLessFloorsNoResult() {
        BuildingTable table = new BuildingTable();
        List<Building> resultsList = table.getAllWithLessFloors(2);
        assertEquals(emptyList,resultsList);

    }

    @Test
    void getAllWithLessFloorsOneResult() {
        BuildingTable table = new BuildingTable();
        List<Building> resultsList = table.getAllWithLessFloors(5);
        singleBuildingList.add(building1);
        assertEquals(singleBuildingList.size(), resultsList.size());
        assertEquals(singleBuildingList.get(0).getId(),resultsList.get(0).getId());
    }
    @Test
    void getAllWithLessFloorsTwoResult() {
        BuildingTable table = new BuildingTable();
        List<Building> resultsList = table.getAllWithLessFloors(40);
        twoBuildingList.add(building1);
        twoBuildingList.add(evilBuilding);
        assertEquals(resultsList.size(),twoBuildingList.size());
        assertEquals(resultsList.get(0).getId(),twoBuildingList.get(0).getId());
        assertEquals(resultsList.get(1).getId(),twoBuildingList.get(1).getId());
    }

    @Test
    void getByAbbreviationNoResult() {
        BuildingTable table = new BuildingTable();
        assertEquals(Optional.empty(),table.getByAbbreviation("LJS"));
    }
    @Test
    void getByAbbreviationGotResult() {
        BuildingTable table = new BuildingTable();
        long results = table.getByAbbreviation("BLD").get().getId();
        assertEquals(building1.getId(), results);
    }
}