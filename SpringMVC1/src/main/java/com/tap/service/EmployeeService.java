package com.tap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tap.daoimpl.EmployeeDAOImpl;
import com.tap.entity.Employee;

@Component
public class EmployeeService 
{
	@Autowired
	private EmployeeDAOImpl employeeDAOImpl;

	public void saveEmployee(Employee emp) 
	{
		employeeDAOImpl.addEmployee(emp);
		 
	}
   
}
