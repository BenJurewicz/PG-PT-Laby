package jkz;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Lab5 {

	public static void main(String[] args) {
		silenceLogging();
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
