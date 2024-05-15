package edu.sandiego.comp305.sp24.schoolSim.service;

import edu.sandiego.comp305.sp24.schoolSim.model.Department;
import edu.sandiego.comp305.sp24.schoolSim.model.Person;

import java.sql.Date;

public class SimplePerson extends Person {
    SimplePerson(String firstName, String lastName, Date birthdate, String phoneNumber, String username,
                 String organizationEmail, String secondaryEmail, boolean isActive, Department department)
    {
        super(firstName, lastName, birthdate, phoneNumber, username,
                organizationEmail, secondaryEmail, isActive, department);

    }

    SimplePerson(long id){
        super(id);
    }
}