package com.ayoub.employeemanagementsystem.controllers;

import com.ayoub.employeemanagementsystem.entities.Department;
import com.ayoub.employeemanagementsystem.entities.Employee;
import com.ayoub.employeemanagementsystem.entities.Manager;
import com.ayoub.employeemanagementsystem.entities.Team;
import com.ayoub.employeemanagementsystem.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ManagerService managerService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TeamService teamService;

    @GetMapping("/")
    public String viewHomePage(Model model, @RequestParam(required = false) String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            List<Employee> searchResults = employeeService.searchEmployeesByName(keyword);
            model.addAttribute("listEmployees", searchResults);
        } else {
            model.addAttribute("listEmployees", employeeService.getAllEmployees());
        }
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        Employee employee = new Employee();
        List<Manager> managers = managerService.getAllManagers();
        List<Department> departments = departmentService.getAllDepartments();
        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("employee", employee);
        model.addAttribute("managers", managers);
        model.addAttribute("departments", departments);
        model.addAttribute("teams", teamService.getAllTeams());
//        model.addAttribute("projects", projectService.getAllProjects());
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        List<Manager> managers = managerService.getAllManagers();
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("employee", employee);
        model.addAttribute("managers", managers);
        model.addAttribute("departments", departments);
        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }
    @GetMapping("/detailEmployee/{id}")
    public String detailEmployee(@PathVariable(value = "id") long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employee_detail";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Employee> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployees", listEmployees);
        return "index";
    }
}
