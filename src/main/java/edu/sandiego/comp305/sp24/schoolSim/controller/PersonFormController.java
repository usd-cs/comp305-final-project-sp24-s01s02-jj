package edu.sandiego.comp305.sp24.schoolSim.controller;

import edu.sandiego.comp305.sp24.schoolSim.model.*;
import edu.sandiego.comp305.sp24.schoolSim.service.*;
import edu.sandiego.comp305.sp24.schoolSim.view.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.ui.Model;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

@Controller
class PersonFormController implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/person").setViewName("person");
        registry.addViewController("/person?type=alumni").setViewName("alumni");
    }

    DatabaseTable getTableFromKey(Optional<String> key) {
        String tableKey = "all";
        DatabaseTable table;
        if (key.isPresent()) {
            tableKey = key.get();
        }
        table = switch (tableKey) {
            case "alumni" -> new AlumniTable();
            case "faculty" -> new FacultyTable();
            case "employee" -> new EmployeeTable();
            case "student" -> new StudentTable();
            default -> new PersonTable();
        };

        return table;
    }

    String getPOSTPathFromKey(Optional<String> key) {
        String tableKey = "person";
        String path = "/person/person";
        if (key.isPresent()) {
            tableKey = key.get();
        }
        path = switch (tableKey) {
            case "alumni" -> "/person/alumni";
            case "faculty" -> "/person/faculty";
            case "employee" -> "/person/employee";
            case "student" -> "/person/student";
            default -> "/person/person";
        };
        return path;
    }

    void addErrorMessage(Model model, Optional<String> status) {
        if (status.isPresent()) {
            String code = status.get();
            String message = switch (code) {
                case "error" -> TableVisualizer.generateErrorContent("Error encountered while attempting to add item to the database");
                case "error1" -> TableVisualizer.generateErrorContent("Attempted to delete unknown id");
                default -> TableVisualizer.generateErrorContent("An Unknown Error Occurred");
            };
            model.addAttribute("errorMessage", message);
        }
    }

    @GetMapping("/person")
    public String showTable(@RequestParam Optional<String> type, @RequestParam Optional<String> status, Model model) {
        DatabaseTable table = getTableFromKey(type);
        addErrorMessage(model, status);
        List<DatabaseItem> items = table.getAllPaged(0);
        model.addAttribute("tableData", TableVisualizer.generateTableView(table, items));
        model.addAttribute("tableName", table.getTableName());
        model.addAttribute("formLink", getPOSTPathFromKey(type));
        return "table";
    }

    public boolean testID(long id, Function<? super Long, ? extends DatabaseItem> idConstructor) {
        try {
            idConstructor.apply(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public DatabaseTable getDeepestTable(int id) {
        if (testID(id, Faculty::new)) {
            return new FacultyTable();
        } else if (testID(id, Employee::new)) {
            return new EmployeeTable();
        } else if (testID(id, Student::new)) {
            return new StudentTable();
        } else if (testID(id, Alumni::new)) {
            return new AlumniTable();
        } else if (testID(id, Person::new)) {
            return new PersonTable();
        } else {
            throw new IllegalArgumentException("Invalid ID");
        }
    }

    @GetMapping("/person/{id}")
    public String deletePerson(@PathVariable int id, @RequestParam Optional<String> type) {
        String redirect = "redirect:/person";
        redirect += "?type="+type.orElse("person");
        try {
            DatabaseTable table = getDeepestTable(id);
            table.deleteFromDatabase(id);
        } catch (IllegalArgumentException e) {
            redirect += "&status=error1";
        }
        return redirect;
    }

    @GetMapping("/person/person")
    public String showForm(PersonForm personForm) {
        return personForm.getFormPath();
    }

    @GetMapping("/person/alumni")
    public String showAlumniForm(AlumniForm form) { return form.getFormPath(); }

    @GetMapping("/person/employee")
    public String showEmployeeForm(EmployeeForm form) { return form.getFormPath(); }

    @GetMapping("/person/faculty")
    public String showFacultyForm(FacultyForm form) { return form.getFormPath(); }

    @GetMapping("/person/student")
    public String showStudentForm(StudentForm form) { return form.getFormPath(); }

    public String handleWebForm(WebForm form, BindingResult result) {
        if (result.hasErrors()) {
            return form.getFormPath();
        }
        try {
            form.build();
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return form.getUnsuccessfulRedirect();
        }
        return form.getSuccessRedirect();
    }

    @PostMapping("/person")
    public String addPerson(@Valid PersonForm form, BindingResult result) {
        return handleWebForm(form, result);
    }

    @PostMapping("/alumni")
    public String addAlumni(@Valid AlumniForm form, BindingResult result) {
        return handleWebForm(form, result);
    }

    @PostMapping("/faculty")
    public String addFaculty(@Valid FacultyForm form, BindingResult result) {
        return handleWebForm(form, result);
    }

    @PostMapping("/employee")
    public String addEmployee(@Valid EmployeeForm form, BindingResult result) {
        return handleWebForm(form, result);
    }

    @PostMapping("/student")
    public String addStudent(@Valid StudentForm form, BindingResult result) {
        return handleWebForm(form, result);
    }
}
