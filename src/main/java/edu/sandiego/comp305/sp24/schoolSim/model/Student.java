package edu.sandiego.comp305.sp24.schoolSim.model;

import edu.sandiego.comp305.sp24.schoolSim.enums.Grade;


public class Student /*extends Person*/ {
    private String major;
    private Grade grade;

    Student() {

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
}