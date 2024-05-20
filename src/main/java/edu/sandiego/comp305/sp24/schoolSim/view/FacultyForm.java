package edu.sandiego.comp305.sp24.schoolSim.view;

import edu.sandiego.comp305.sp24.schoolSim.model.Building;
import edu.sandiego.comp305.sp24.schoolSim.model.Faculty;
import edu.sandiego.comp305.sp24.schoolSim.model.Room;
import edu.sandiego.comp305.sp24.schoolSim.service.BuildingTable;
import edu.sandiego.comp305.sp24.schoolSim.service.RoomTable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FacultyForm extends EmployeeForm{
    @NotNull
    @NotBlank
    String buildingAbbreviation;

    private long buildingID;

    int roomNumber;

    boolean tenured;

    public boolean getTenured() {
        return tenured;
    }

    public void setTenured(boolean tenured) {
        this.tenured = tenured;
    }

    @Override
    public void build() {
        Room office = new RoomTable().getWithBuildingIDAndRoomNumber(buildingID, getRoomNumber()).orElseThrow();
        new Faculty(getFirstName(),
                getLastName(),
                getBirthDate(),
                getPhoneNumber(),
                getUsername(),
                getOrganizationEmail(),
                getSecondaryEmail(),
                getActive(),
                getDepartmentInstance(),
                getStartDate(),
                getHourlyWage(),
                getManagerInstance(),
                office,
                getTenured());
    }

    @Override
    public String getFormPath() {
        return "facultyForm";
    }

    @Override
    public String getSuccessRedirect() {
        return "redirect:/person?type=faculty";
    }

    @Override
    public String getUnsuccessfulRedirect() {
        return "redirect:/person?type=faculty&status=error";
    }
    @Override
    public String toString() {
        return "FacultyForm{" +
                "officeLocation=" + new Building(buildingID) +
                ", tenured=" + tenured +
                '}';
    }

    public String getBuildingAbbreviation() {
        return buildingAbbreviation;
    }

    public void setBuildingAbbreviation(String buildingAbbreviation) {
        BuildingTable table = new BuildingTable();
        this.buildingID = table.getByAbbreviation(buildingAbbreviation).orElseThrow().getId();
        this.buildingAbbreviation = buildingAbbreviation;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isTenured() {
        return tenured;
    }
}
