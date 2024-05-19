package com.ayoub.employeemanagementsystem.controllers;

import com.ayoub.employeemanagementsystem.entities.Employee;
import com.ayoub.employeemanagementsystem.services.EmployeeService;
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


//    @GetMapping("/search")
//    public String searchEmployees(@RequestParam("query") String query, Model model) {
//        List<Employee> employees = employeeService.search(query); // Implement your search logic in EmployeeService
//        model.addAttribute("listEmployees", employees);
//        model.addAttribute("query", query); // Add the query back to the model to display in the form
//        return "employees"; // Thymeleaf template name for displaying the list of employees
//    }
    // display list of employees
    @GetMapping("/")
    public String viewHomePage(Model model, @RequestParam(required = false) String keyword) {
        if (keyword != null) {
            List<Employee> searchResults = employeeService.searchEmployees(keyword);
            model.addAttribute("listEmployees", searchResults);
        } else {
            model.addAttribute("listEmployees", employeeService.getAllEmployees());
        }
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        // save employee to database
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {

        Employee employee = employeeService.getEmployeeById(id);

        model.addAttribute("employee", employee);
        return "update_employee";
    }
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value = "id") long id){
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
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