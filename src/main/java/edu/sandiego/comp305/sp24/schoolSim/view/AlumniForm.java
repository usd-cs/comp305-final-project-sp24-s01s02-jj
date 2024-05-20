package edu.sandiego.comp305.sp24.schoolSim.view;

import edu.sandiego.comp305.sp24.schoolSim.enums.DegreeType;
import edu.sandiego.comp305.sp24.schoolSim.model.Alumni;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.sql.Date;

public class AlumniForm extends PersonForm {
    @Past
    @NotNull
    Date graduationDate;

    DegreeType degree;


    @Override
    public void build(){
        new Alumni(
                getFirstName(),
                getLastName(),
                getBirthDate(),
                getPhoneNumber(),
                getUsername(),
                getOrganizationEmail(),
                getOrganizationEmail(),
                getActive(),
                getDepartmentInstance(),
                getGraduationDate(),
                getDegree()
        );
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    public DegreeType getDegree() {
        return degree;
    }

    public void setDegree(DegreeType degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "AlumniForm{" +
                "graduationDate=" + graduationDate +
                ", degree=" + degree +
                '}';
    }
}
