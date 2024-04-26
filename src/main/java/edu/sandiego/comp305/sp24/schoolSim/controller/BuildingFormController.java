package edu.sandiego.comp305.sp24.schoolSim.controller;

import edu.sandiego.comp305.sp24.schoolSim.model.Building;
import edu.sandiego.comp305.sp24.schoolSim.model.Department;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

class BuildingFormController implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        // pass
    }

    @GetMapping("/building/input")
    public String form(Building buildingForm) {
        return "index";
    }

    @PostMapping("/building/input")
    public String addBuilding(@Valid Building buildingForm, BindingResult result) {
        return "redirect:/";
    }
}
