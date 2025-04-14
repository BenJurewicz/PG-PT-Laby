package jkz.Database.Managers;

import jkz.Database.Entities.Company;
import jkz.Database.Entities.Department;

import java.util.List;

public class CompanyManager extends Manager<Company> {
	public CompanyManager() {
		super(Company.class);
	}

	public void remove(Company company) {
		DepartmentManager departmentManager = new DepartmentManager();
		List<Department> departments = company.getDepartments();
		for (Department department : departments) {
			department.setCompany(null);
			departmentManager.update(department);
		}
		super.remove(company.getId());
	}
}
