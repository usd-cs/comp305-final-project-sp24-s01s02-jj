package edu.sandiego.comp305.sp24.schoolSim.model;

import java.util.List;
import java.util.Optional;

public class Building implements DatabaseItem{
    private String name;
    private String address;
    private int floors;
    private String abbreviation;


    Building(){
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getFloors() {
        return floors;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public boolean addToDatabase() {
        return false;
    }

    @Override
    public boolean removeFromDatabase() {
        return false;
    }

    @Override
    public boolean updateFromDatabase(String query) {
        return false;
    }

    @Override
    public Optional<DatabaseItem> fetchFromDatabase(int id) {
        return Optional.empty();
    }

    @Override
    public List<DatabaseItem> queryDatabase(String query) {
        return null;
    }
}