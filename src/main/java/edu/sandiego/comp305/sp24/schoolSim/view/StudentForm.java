package edu.sandiego.comp305.sp24.schoolSim.view;

import edu.sandiego.comp305.sp24.schoolSim.enums.Grade;
import edu.sandiego.comp305.sp24.schoolSim.model.Alumni;
import edu.sandiego.comp305.sp24.schoolSim.model.Student;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudentForm extends PersonForm {

    @NotNull
    @NotBlank
    String major;

    @NotNull
    Grade grade;

    @Override
    public void build(){
        new Student(
                getFirstName(),
                getLastName(),
                getBirthDate(),
                getPhoneNumber(),
                getUsername(),
                getOrganizationEmail(),
                getSecondaryEmail(),
                getActive(),
                getDepartmentInstance(),
                getMajor(),
                getGrade()
        );
    }

    @Override
    public String getFormPath() {
        return "studentForm";
    }

    @Override
    public String getSuccessRedirect() {
        return "redirect:/person?type=student";
    }

    @Override
    public String getUnsuccessfulRedirect() {
        return "redirect:/person?type=student&status=error";
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "StudentForm{" +
                "major='" + major + '\'' +
                ", grade=" + grade +
                '}';
    }
}
