package edu.sandiego.comp305.sp24.schoolSim.controller;

import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseItem;
import edu.sandiego.comp305.sp24.schoolSim.model.Person;
import edu.sandiego.comp305.sp24.schoolSim.service.PersonTable;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.ui.Model;

import java.util.List;

@Controller
class PersonFormController implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/person");
    }

    @GetMapping("/person")
    public String form(Model model) {
        PersonTable table = new PersonTable();
        List<Person> allPeople = table.getAllPaged(0);
        List<DatabaseItem> peopleAsItems = allPeople.stream().map((x) -> {return (DatabaseItem) x;}).toList();
        model.addAttribute("tableData", TableVisualizer.generateTableView(table, peopleAsItems));
        model.addAttribute("tableName", table.getTableName());
        return "table";
    }

    @PostMapping("/person/input")
    //TODO: Check if this is possible since person is abstract
    public String addPerson(@Valid Person personForm, BindingResult result) {
        return "redirect:/";
    }
}
