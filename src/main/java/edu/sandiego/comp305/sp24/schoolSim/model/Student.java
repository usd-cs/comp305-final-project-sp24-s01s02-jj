package edu.sandiego.comp305.sp24.schoolSim.model;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public class Student extends Person{
    private String major;
    private Grade grade;

    public Student(String name, Grade grade){
        super();
        this.major = name;
        this.grade = grade;
    }

    Student(int id, String firstName, String lastName, Date birthday, String phoneNumber, String username,
            String organizationEmail, String secondaryEmail, boolean isActive, String major, Grade grade){
        this.major = major;
        this.grade = grade;
    }
    public String getMajor(){
        return major;
    }
    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public void setMajor(String major) {
        this.major = major;
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