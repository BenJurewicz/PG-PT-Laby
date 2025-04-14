package jkz.Database.Managers;

import jkz.Database.Entities.Employee;

public class EmployeeManager extends Manager<Employee> {
	public EmployeeManager() {
		super(Employee.class);
	}
}
