package com.ayoub.employeemanagementsystem.controllers;

import com.ayoub.employeemanagementsystem.entities.Manager;
import com.ayoub.employeemanagementsystem.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/managers")
    public String viewHomePage(Model model) {
        model.addAttribute("listManagers", managerService.getAllManagers());
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/showNewManagerForm")
    public String showNewManagerForm(Model model) {
        Manager manager = new Manager();
        model.addAttribute("manager", manager);
        return "new_manager";
    }

    @PostMapping("/saveManager")
    public String saveManager(@ModelAttribute("manager") Manager manager) {
        managerService.saveManager(manager);
        return "redirect:/managers";
    }

    @GetMapping("/showFormForUpdateManager/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Manager manager = managerService.getManagerById(id);
        model.addAttribute("manager", manager);
        return "update_manager";
    }

    @GetMapping("/deleteManager/{id}")
    public String deleteManager(@PathVariable(value = "id") long id) {
        managerService.deleteManagerById(id);
        return "redirect:/managers";
    }

    @GetMapping("/managers/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, Model model) {
        int pageSize = 5;

        Page<Manager> page = managerService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Manager> listManagers = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listManagers", listManagers);
        return "managers";
    }
}
