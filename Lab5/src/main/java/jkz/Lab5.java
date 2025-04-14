package jkz;

import jkz.Database.Entities.Company;
import jkz.Database.Entities.Department;
import jkz.Database.Entities.Employee;
import jkz.Database.Managers.Manager;
import jkz.Logging.Log;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Lab5 {

	public static void main(String[] args) {
		silenceLogging();

		Manager<Company> companyManager = new Manager<>(Company.class);
		Manager<Department> departmentManager = new Manager<>(Department.class);
		Manager<Employee> employeeManager = new Manager<>(Employee.class);

		Company company = new Company("Google", "Technology");
		companyManager.add(company);
		company = companyManager.find(1L);

		Department department = new Department("Engineering", "London", company);
		departmentManager.add(department);
		department = departmentManager.find(1L);
		Employee employee = new Employee("John Doe", "123456789", 1000, new Date(), department);
		employeeManager.add(employee);

		Requests.getCandidatesForRaise();
		Requests.countLocationsForDepartments();
		Requests.averageSalaryForEveryCompany();
		Requests.countEmployeesAndDepartments();
		Requests.avarageSalaryForIndustries();
	}

	public static void silenceLogging() {
		Logger rootLogger = LogManager.getLogManager().getLogger("");
		rootLogger.setLevel(Level.SEVERE);

		Logger hibernateLogger = Logger.getLogger("org.hibernate");
		Logger.getLogger("org.hibernate").setLevel(Level.OFF);
	}
}
