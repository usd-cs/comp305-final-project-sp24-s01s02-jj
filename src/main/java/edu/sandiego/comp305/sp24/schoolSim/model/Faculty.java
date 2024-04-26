package edu.sandiego.comp305.sp24.schoolSim.model;

public class Faculty extends Employee{
    private Room officeLocation;
    private Department department;
    private boolean hasTenure;
    private String college;

    Faculty(){

    }

    public Room getOfficeLocation() {
        return officeLocation;
    }

    public Department getDepartment() {
        return department;
    }

    public boolean isHasTenure() {
        return hasTenure;
    }

    public String getCollege() {
        return college;
    }

    public void setOfficeLocation(Room officeLocation) {
        this.officeLocation = officeLocation;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setHasTenure(boolean hasTenure) {
        this.hasTenure = hasTenure;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}