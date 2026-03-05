package com.tap.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee 
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int employeeid;
   private String name;
   private String email;
   private String address;
   private int age;
   
   public Employee() 
   {
	   
   }

public Employee(String name, String email, String address, int age) {
	super();
	this.name = name;
	this.email = email;
	this.address = address;
	this.age = age;
}

public int getEmployeeid() {
	return employeeid;
}

public void setEmployeeid(int employeeid) {
	this.employeeid = employeeid;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

@Override
public String toString() {
	return "Employee [employeeid=" + employeeid + ", name=" + name + ", email=" + email + ", address=" + address
			+ ", age=" + age + "]";
}
   
   
   
   
}
