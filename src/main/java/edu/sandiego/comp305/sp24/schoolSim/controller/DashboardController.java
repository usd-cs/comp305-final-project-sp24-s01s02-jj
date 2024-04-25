package edu.sandiego.comp305.sp24.schoolSim.controller;

import org.springframework.web.bind.annotation.GetMapping;

class DashboardController {
    @GetMapping("/")
    public String renderDashboard() {
        return "index";
    }

    protected String renderSidebar() {
        return "";
    }

    protected String renderDataPanels() {
        return "";
    }
}