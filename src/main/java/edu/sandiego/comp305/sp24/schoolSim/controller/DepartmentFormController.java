package edu.sandiego.comp305.sp24.schoolSim.controller;


import edu.sandiego.comp305.sp24.schoolSim.model.Department;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

class DepartmentFormController implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        // pass
    }

    @GetMapping("/department/input")
    public String form(Department departmentForm) {
        return "index";
    }

    @PostMapping("/department/input")
    public String addDepartment(@Valid Department departmentForm, BindingResult result) {
        return "redirect:/";
    }
}