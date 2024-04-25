package edu.sandiego.comp305.sp24.schoolSim.controller;

import edu.sandiego.comp305.sp24.schoolSim.model.Department;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

class RoomFormController implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        // pass
    }

    @GetMapping("/room/input")
    //TODO: Replace with room when it exists
    public String form(Department departmentForm) {
        return "index";
    }

    @PostMapping("/room/input")
    //TODO: Replace with room when it exists
    public String addDepartment(@Valid Department departmentForm, BindingResult result) {
        return "redirect:/";
    }
}
