package edu.sandiego.comp305.sp24.schoolSim.controller;

import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseItem;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseTable;
import edu.sandiego.comp305.sp24.schoolSim.model.Person;
import edu.sandiego.comp305.sp24.schoolSim.service.*;
import edu.sandiego.comp305.sp24.schoolSim.view.AlumniForm;
import edu.sandiego.comp305.sp24.schoolSim.view.PersonForm;
import edu.sandiego.comp305.sp24.schoolSim.view.TableVisualizer;
import edu.sandiego.comp305.sp24.schoolSim.view.WebForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.ui.Model;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/person")
    public String showTable(@RequestParam Optional<String> type, @RequestParam Optional<String> status, Model model) {
        DatabaseTable table = getTableFromKey(type);
        if (status.isPresent() && status.get().equals("error")) {
            model.addAttribute("errorMessage", TableVisualizer.generateErrorContent("Error encountered while attempting to add item to the database"));
        }
        List<DatabaseItem> items = table.getAllPaged(0);
        model.addAttribute("tableData", TableVisualizer.generateTableView(table, items));
        model.addAttribute("tableName", table.getTableName());
        model.addAttribute("formLink", getPOSTPathFromKey(type));
        return "table";
    }

    @GetMapping("/person/person")
    public String showForm(PersonForm personForm) {
        return "personForm";
    }

    @GetMapping("/person/alumni")
    public String showAlumniForm(AlumniForm form) { return "alumniForm"; }

    public String handleWebForm(WebForm form, BindingResult result) {
        if (result.hasErrors()) {
            return form.getFormPath();
        }
        try {
            form.build();
        } catch (IllegalArgumentException e) {
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
}
