package edu.sandiego.comp305.sp24.schoolSim.controller;

import edu.sandiego.comp305.sp24.schoolSim.model.Person;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

class PersonFormController implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        // pass
    }

    @GetMapping("/person/input")
    public String form(Person personForm) {
        return "index";
    }

    @PostMapping("/person/input")
    //TODO: Check if this is possible since person is abstract
    public String addDepartment(@Valid Person personForm, BindingResult result) {
        return "redirect:/";
    }
}
