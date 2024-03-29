package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springboot.model.Employee;
import com.example.springboot.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	//display list of employees
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		return "index";
		
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
		//save employee to database
		employeeService.saveEmployee(employee);
		return "redirect:/";
		
	}
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable( value="id") long id, Model model) {
		
		//get employee from the service
		Employee employee = employeeService.getEmployeeById(id);
		
		//set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id) {
		
		// call delete employee method
		
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}
	@GetMapping("/viewEmployee/{id}")
	public String viewEmployee(@PathVariable(value = "id") long id, Model model) {
	    // get employee from the service
	    Employee employee = employeeService.getEmployeeById(id);

	    // set employee as a model attribute to display in the view
	    model.addAttribute("employee", employee);
	    return "view_employee";
	}
}
