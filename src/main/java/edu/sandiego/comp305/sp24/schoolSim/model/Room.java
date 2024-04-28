package edu.sandiego.comp305.sp24.schoolSim.model;

import java.util.List;
import java.util.Optional;

public class Room {
    private Building building;
    private int roomNumber;

    Room(){

    }

    public Building getBuilding() {
        return building;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}