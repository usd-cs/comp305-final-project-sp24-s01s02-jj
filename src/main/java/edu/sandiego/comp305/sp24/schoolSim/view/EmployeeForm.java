package edu.sandiego.comp305.sp24.schoolSim.view;

import edu.sandiego.comp305.sp24.schoolSim.model.Employee;
import edu.sandiego.comp305.sp24.schoolSim.model.Person;
import edu.sandiego.comp305.sp24.schoolSim.service.PersonTable;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.sql.Date;
import java.util.Optional;

public class EmployeeForm extends PersonForm{
    Employee manager;
    @NotNull
    @Past
    Date startDate;

    double hourlyWage;


    public String getManager() {
        if (manager != null) {
            return manager.toString();
        }
        return "";
    }

    public Employee getManagerInstance() {
        return this.manager;
    }

    public void setManager(String managerUsername) {
        if (managerUsername != null) {
            Optional<Person> potentialManager = new PersonTable().getByUsername(managerUsername);
            if (potentialManager.isPresent()) {
                manager = new Employee(potentialManager.get().getId());
            } else {
                if (!managerUsername.isBlank()) {
                    throw new IllegalArgumentException("Manager does not exist");
                } else {
                    this.manager = null;
                }
            }
        } else {
            this.manager = null;
        }
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    @Override
    public void build() {
        new Employee(getFirstName(),
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
                getManagerInstance());
    }

    @Override
    public String getFormPath() {
        return "employeeForm";
    }

    @Override
    public String getSuccessRedirect() {
        return "redirect:/person?type=employee";
    }

    @Override
    public String getUnsuccessfulRedirect() {
        return "redirect:/person?type=employee&status=error";
    }

    @Override
    public String toString() {
        return "EmployeeForm{" +
                "manager=" + manager +
                ", startDate=" + startDate +
                ", hourlyWage=" + hourlyWage +
                '}';
    }
}
