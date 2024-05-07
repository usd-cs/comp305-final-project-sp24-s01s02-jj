package edu.sandiego.comp305.sp24.schoolSim.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

class DashboardController {
    @GetMapping("/")
    public String renderDashboard(Model model) {
        // TODO: Do once enum reworks done
        // model.addAttribute("");
        return "index";
    }

    protected String renderSidebar() {
        return "";
    }

    protected String renderDataPanels() {
        return "";
    }
}