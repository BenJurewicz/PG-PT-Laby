package jkz;

// import jkz.Database.Entities.Company;
// import jkz.Database.Entities.Department;
// import jkz.Database.Entities.Employee;
// import jkz.Database.Managers.Manager;

// import java.util.Date;
// import java.util.Date;
import jkz.Database.Managers.CompanyManager;
import jkz.Database.Managers.EmployeeManager;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Lab5 {

	public static void main(String[] args) {
		new DatabaseSeeder().seedAll();

		Requests.getCandidatesForRaise();
		Requests.countLocationsForDepartments();
		Requests.averageSalaryForEveryCompany();
		Requests.countEmployeesAndDepartments();
		Requests.avarageSalaryForIndustries();

		RequestNRecords.getRecords("Company",0 );
		RequestNRecords.getRecords("Department",0 );
		RequestNRecords.getRecords("Employee",0 );

		RequestNRecords.getRecords("Employee",1 );
		RequestNRecords.getRecords("Department",1 );
		RequestNRecords.getRecords("Company",1 );

	}

	public static void silenceLogging() {
		Logger rootLogger = LogManager.getLogManager().getLogger("");
		rootLogger.setLevel(Level.SEVERE);

		Logger hibernateLogger = Logger.getLogger("org.hibernate");
		Logger.getLogger("org.hibernate").setLevel(Level.OFF);
	}
}
