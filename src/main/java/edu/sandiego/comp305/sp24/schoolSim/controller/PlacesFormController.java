package edu.sandiego.comp305.sp24.schoolSim.controller;

import edu.sandiego.comp305.sp24.schoolSim.model.Building;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseItem;
import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseTable;
import edu.sandiego.comp305.sp24.schoolSim.service.*;
import edu.sandiego.comp305.sp24.schoolSim.view.TableVisualizer;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;

@Controller
class PlacesFormController implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/places");
    }

    DatabaseTable getTableFromKey(Optional<String> key) {
        String tableKey = "all";
        if (key.isPresent()) {
            tableKey = key.get();
        }
        DatabaseTable table;
        if (tableKey.equals("room")) {
            table = new RoomTable();
        } else {
            table = new BuildingTable();
        }
        return table;
    }

    String getPOSTPathFromKey(Optional<String> key) {
        String tableKey = "building";
        if (key.isPresent()) {
            tableKey = key.get();
        }
        String path = "/places/building";
        if (tableKey.equals("room")) {
            path = "/places/room";
        } else {
            path = "/places/building";
        }
        return path;
    }
    @GetMapping("/places")
    public String form(@RequestParam Optional<String> type, Model model) {
        DatabaseTable table = getTableFromKey(type);
        List<DatabaseItem> items = table.getAllPaged(0);
        model.addAttribute("tableData", TableVisualizer.generateTableView(table, items));
        model.addAttribute("tableName", table.getTableName());
        model.addAttribute("formLink", getPOSTPathFromKey(type));
        return "table";
    }
}
