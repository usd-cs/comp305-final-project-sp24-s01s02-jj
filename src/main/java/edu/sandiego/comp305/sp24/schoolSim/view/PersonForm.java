package edu.sandiego.comp305.sp24.schoolSim.view;

import edu.sandiego.comp305.sp24.schoolSim.Database;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseTable;
import edu.sandiego.comp305.sp24.schoolSim.model.Department;
import edu.sandiego.comp305.sp24.schoolSim.model.Person;

import edu.sandiego.comp305.sp24.schoolSim.service.DepartmentTable;
import jakarta.validation.constraints.*;

import java.sql.*;

public class PersonForm implements WebForm{

    @NotNull
    @NotBlank
    String firstName;
    @NotNull
    @NotBlank
    String lastName;
    @Past
    @NotNull
    Date birthDate;
    @NotNull
    @NotBlank
    String phoneNumber;
    @NotNull
    @NotBlank
    String username;
    @NotNull
    @Email
    @NotBlank
    String organizationEmail;
    @Email
    @NotBlank
    String secondaryEmail;
    boolean active;
    @NotNull
    Department department;

    @Override
    public void build() {
        new Person(getFirstName(),
                getLastName(),
                getBirthDate(),
                getPhoneNumber(),
                getUsername(),
                getOrganizationEmail(),
                getSecondaryEmail(),
                getActive(),
                getDepartmentInstance());
    }

    @Override
    public String getFormPath() {
        return "personForm";
    }

    @Override
    public String getSuccessRedirect() {
        return "redirect:/person";
    }

    @Override
    public String getUnsuccessfulRedirect() {
        return "redirect:/person?status=error";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getOrganizationEmail() {
        return organizationEmail;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public Boolean getActive() {
        return active;
    }

    public String getDepartment() {
        // Uncomment once departmentTable exists
        if (department != null) {
            return this.department.toString();
        }
        return "";
    }

    public Department getDepartmentInstance() {
        return this.department;
    }

    public void setDepartment(String departmentName) {
        DepartmentTable departmentTable = new DepartmentTable();
        this.department = departmentTable.getByName(departmentName).orElseThrow();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOrganizationEmail(String organizationEmail) {
        this.organizationEmail = organizationEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "PersonForm{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", organizationEmail='" + organizationEmail + '\'' +
                ", secondaryEmail='" + secondaryEmail + '\'' +
                ", isActive=" + active +
                ", department=" + department +
                '}';
    }
}
