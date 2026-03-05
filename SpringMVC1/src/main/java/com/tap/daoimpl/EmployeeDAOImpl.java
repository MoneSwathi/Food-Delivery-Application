package com.tap.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tap.dao.EmployeeDAO;
import com.tap.entity.Employee;

@Component
public class EmployeeDAOImpl implements EmployeeDAO 
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addEmployee(Employee employee) 
	{
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		session.persist(employee);
		
		tx.commit();
		
		
		
	}

}
