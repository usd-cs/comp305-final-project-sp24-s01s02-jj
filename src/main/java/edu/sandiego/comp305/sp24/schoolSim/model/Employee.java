package edu.sandiego.comp305.sp24.schoolSim.model;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Employee extends Person{
    private Optional<Employee> manager;
    private Date startDate;
    private double hourlyWage;

    Employee(){

    }

    public Optional<Employee> getManager() {
        return manager;
    }

    public Date getStartDate() {
        return startDate;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setManager(Optional<Employee> manager) {
        this.manager = manager;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
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