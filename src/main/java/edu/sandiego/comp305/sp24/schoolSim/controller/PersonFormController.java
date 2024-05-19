package edu.sandiego.comp305.sp24.schoolSim.controller;

import edu.sandiego.comp305.sp24.schoolSim.model.Alumni;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseItem;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseTable;
import edu.sandiego.comp305.sp24.schoolSim.model.Person;
import edu.sandiego.comp305.sp24.schoolSim.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Controller
class PersonFormController implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/person");
    }

    @GetMapping("/person")
    public String form(@RequestParam Optional<String> type, Model model) {
        String tableKey = "all";
        DatabaseTable table;
        List<DatabaseItem> chosenRows;
        if (type.isPresent()) {
            tableKey = type.get();
        }
        table = switch (tableKey) {
            case "alumni" -> new AlumniTable();
            case "faculty" -> new FacultyTable();
            case "employee" -> new EmployeeTable();
            case "student" -> new StudentTable();
            default -> new PersonTable();
        };
        List<DatabaseItem> items = table.getAllPaged(0);
        model.addAttribute("tableData", TableVisualizer.generateTableView(table, items));
        model.addAttribute("tableName", table.getTableName());
        return "table";
    }

    @PostMapping("/person/input")
    //TODO: Check if this is possible since person is abstract
    public String addPerson(@Valid Person personForm, BindingResult result) {
        return "redirect:/";
    }
}
