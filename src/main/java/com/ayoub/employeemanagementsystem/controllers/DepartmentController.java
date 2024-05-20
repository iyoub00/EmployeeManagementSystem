package com.ayoub.employeemanagementsystem.controllers;

import com.ayoub.employeemanagementsystem.entities.Department;
import com.ayoub.employeemanagementsystem.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    @GetMapping("/search")
    public String searchDepartments(@RequestParam("keyword") String keyword, Model model) {
        List<Department> departments = departmentService.searchDepartmentsByName(keyword);
        model.addAttribute("departments", departments);
        return "departments/list";
    }

    @GetMapping
    public String listDepartments(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "departments/list";
    }

    @GetMapping("/{id}")
    public String getDepartmentById(@PathVariable long id, Model model) {
        model.addAttribute("department", departmentService.getDepartmentById(id));
        return "department_detail";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("department", new Department());
        return "departments/create";
    }

    @PostMapping
    public String createDepartment(@ModelAttribute Department department) {
        departmentService.createDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        model.addAttribute("department", departmentService.getDepartmentById(id));
        return "departments/edit";
    }

    @PostMapping("/update/{id}")
    public String updateDepartment(@PathVariable long id, @ModelAttribute Department department) {
        departmentService.updateDepartment(id, department);
        return "redirect:/departments";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }

    @PostMapping("/{departmentId}/employees/{employeeId}/add")
    public String addEmployeeToDepartment(@PathVariable long departmentId, @PathVariable long employeeId) {
        departmentService.addEmployeeToDepartment(departmentId, employeeId);
        return "redirect:/departments/" + departmentId;
    }

    @PostMapping("/{departmentId}/employees/{employeeId}/remove")
    public String removeEmployeeFromDepartment(@PathVariable long departmentId, @PathVariable long employeeId) {
        departmentService.removeEmployeeFromDepartment(departmentId, employeeId);
        return "redirect:/departments/" + departmentId;
    }
    @GetMapping("/detail/{id}")
    public String showDepartmentDetail(@PathVariable long id, Model model) {
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        return "departments/department_detail";
    }
}
