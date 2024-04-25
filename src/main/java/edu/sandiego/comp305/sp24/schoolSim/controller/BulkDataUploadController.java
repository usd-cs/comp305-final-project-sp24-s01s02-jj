package edu.sandiego.comp305.sp24.schoolSim.controller;

import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseItem;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

class BulkDataUploadController implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        // pass
    }

    @GetMapping("/bulk")
    public String form(Model model) throws IOException {
        return "/";
    }

    @PostMapping("/bulk")
    public String processForm(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        return "/";
    }

    public void parseCSV(List<String> lines) {
        // do stuff
    }

    public DatabaseItem parseLineItem(String line) {
        return null;
    }
}