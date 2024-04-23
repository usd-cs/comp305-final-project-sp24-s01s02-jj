package edu.sandiego.comp305.sp24.schoolSim.model;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public abstract class Person implements DatabaseItem {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private String phoneNumber;
    private String username;
    private String organizationEmail;
    private String secondaryEmail;
    private boolean isActive;
    private Department department;
    public static List<Person> getAllWithFirstName(String firstName) {
        return null;
    }
    public static List<Person> getAllWithLastName(String lastName) {
        return null;
    }
    public static List<Person> getByPhoneNumber(String phoneNumber) {
        return null;
    }
    public static Optional<Person> getByUsername(String username) {
        return null;
    }
    public static Optional<Person> getByOrganizationEmail(String organizationEmail) {
        return null;
    }
    public static Optional<Person> getBySecondaryEmail(String secondaryEmail) {
        return null;
    }
    public static List<Person> getActivePeople() {
        return null;
    }
    public static List<Person> getInactivePeople() {
        return null;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthdate() {
        return birthdate;
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

    public boolean isActive() {
        return isActive;
    }

    public Department getDepartment() {
        return department;
    }
    void setId(int id) {
        this.id = id;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    void setUsername(String username) {
        this.username = username;
    }

    void setOrganizationEmail(String organizationEmail) {
        this.organizationEmail = organizationEmail;
    }

    void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    void setActive(boolean active) {
        isActive = active;
    }

    void setDepartment(Department department) {
        this.department = department;
    }
}
