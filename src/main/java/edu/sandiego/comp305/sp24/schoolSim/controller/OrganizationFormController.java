package edu.sandiego.comp305.sp24.schoolSim.controller;


import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseItem;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseTable;
import edu.sandiego.comp305.sp24.schoolSim.model.Department;
import edu.sandiego.comp305.sp24.schoolSim.service.DepartmentTable;
import edu.sandiego.comp305.sp24.schoolSim.service.RoomTable;
import edu.sandiego.comp305.sp24.schoolSim.view.TableVisualizer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;

@Controller
class OrganizationFormController implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/groups");
    }

    DatabaseTable getTableFromKey(Optional<String> key) {
        String tableKey = "all";
        if (key.isPresent()) {
            tableKey = key.get();
        }
        // Expand to switch if other organization types begin appearing
        return new DepartmentTable();
    }

    @GetMapping("/groups")
    public String form(@RequestParam Optional<String> type, Model model) {
        DatabaseTable table = getTableFromKey(type);
        List<DatabaseItem> items = table.getAllPaged(0);
        model.addAttribute("tableData", TableVisualizer.generateTableView(table, items));
        model.addAttribute("tableName", table.getTableName());
        return "table";
    }
}