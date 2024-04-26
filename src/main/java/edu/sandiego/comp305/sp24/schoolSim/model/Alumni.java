package edu.sandiego.comp305.sp24.schoolSim.model;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Alumni extends Person{
    private Date graduationDate;
    private Degree degreeType;

    public Alumni(Date graduationDate, Degree degreeType){
        super();
        this.graduationDate = graduationDate;
        this.degreeType = degreeType;
    }

    Alumni(int id, String firstName, String lastName, Date birthday, String phoneNumber, String username,
            String organizationEmail, String secondaryEmail, boolean isActive, Date graduationDate, Degree degreeType){
        this.graduationDate = graduationDate;
        this.degreeType = degreeType;
    }

    public Date getGraduationDate() {
        return graduationDate;
    }
    public Degree getDegreeType() {
        return degreeType;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    public void setDegreeType(Degree degreeType) {
        this.degreeType = degreeType;
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