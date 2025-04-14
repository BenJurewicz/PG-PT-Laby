package jkz;

// import jkz.Database.Entities.Company;
// import jkz.Database.Entities.Department;
// import jkz.Database.Entities.Employee;
// import jkz.Database.Managers.Manager;

// import java.util.Date;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Lab5 {

	public static void main(String[] args) {
		new DatabaseSeeder().seedAll();
	}

	public static void silenceLogging() {
		Logger rootLogger = LogManager.getLogManager().getLogger("");
		rootLogger.setLevel(Level.SEVERE);

		Logger hibernateLogger = Logger.getLogger("org.hibernate");
		Logger.getLogger("org.hibernate").setLevel(Level.OFF);
	}
}
