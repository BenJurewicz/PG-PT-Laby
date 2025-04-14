package jkz.Database.Managers;

import jkz.Database.Entities.Department;
import jkz.Database.Entities.Employee;

public class DepartmentManager extends Manager<Department> {
	public DepartmentManager() {
		super(Department.class);
	}

	public void remove(Long id) {
		EmployeeManager employeeManager = new EmployeeManager();
		Department department = super.find(id);
		for (Employee employee : department.getEmployees()) {
//			employee.setDepartment(null);
//			employeeManager.update(employee);
			employeeManager.remove(employee.getId());
		}
		super.remove(id);
	}
}
