package com.tap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tap.entity.Employee;
import com.tap.service.EmployeeService;

@Controller
public class HomeController 
{
	@Autowired
    private EmployeeService employeeService;
	
    @RequestMapping("/")
	public String welcome() 
	{
		return "welcome";
	}
    
    @RequestMapping("register")
    public String register(@ModelAttribute Employee employee) {
    	
        
    	employeeService.saveEmployee(employee);
    	
    	return "register";
    }
}
