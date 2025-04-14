package jkz.Database.Managers;

import jkz.Database.Entities.Department;
import jkz.Database.Entities.Employee;

public class DepartmentManager extends Manager<Department> {
	public DepartmentManager() {
		super(Department.class);
	}
}
