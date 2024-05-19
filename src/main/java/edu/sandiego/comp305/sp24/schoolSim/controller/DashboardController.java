package edu.sandiego.comp305.sp24.schoolSim.controller;

import edu.sandiego.comp305.sp24.schoolSim.Database;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseItem;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseTable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
class DashboardController {

    @GetMapping("/")
    public String renderDashboard(Model model) {
        Database database = Database.getInstance();
        List<DatabaseTable> tables = database.getTables();
        StringBuilder boxes = new StringBuilder();
        for (DatabaseTable table : tables) {
            boxes.append(TableVisualizer.generateTableStatbox(table));
        }
        model.addAttribute("tableBoxes", boxes.toString());
        return "index";
    }
}
