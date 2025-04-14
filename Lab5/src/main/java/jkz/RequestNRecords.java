package jkz;

import jkz.Database.Entities.Company;
import jkz.Database.Entities.Department;
import jkz.Database.Entities.Employee;
import jkz.Database.Managers.Manager;
import jkz.Database.SqlQuery;
import jkz.Logging.Log;

import java.util.List;

public class RequestNRecords {
    public static void getRecords(String className, int n) {
        String lowerClassName = className.toLowerCase();
        if (n == 0) {
            switch (lowerClassName) {
                case "company" -> getAllCompanies();
                case "department" -> getAllDepartments();
                case "employee" -> getAllEmployees();
            }
        } else {
            switch (lowerClassName) {
                case "company" -> getNCompanies(n);
                case "department" -> getNDepartments(n);
                case "employee" -> getNEmployees(n);
            }
        }
    }

    public static SqlQuery getAllEmployees() {
        Manager<Employee> employeeManager = new Manager<>(Employee.class);
        String sqlQuery = "SELECT * FROM Employee;";
        SqlQuery query = new SqlQuery(sqlQuery);
        query = employeeManager.sql(query);
        Log.debug("Select all Employee records");

        return query;
    }

    public static SqlQuery getAllDepartments() {
        Manager<Department> departmentManager = new Manager<>(Department.class);
        String sqlQuery = "SELECT * FROM Department;";
        SqlQuery query = new SqlQuery(sqlQuery);
        query = departmentManager.sql(query);
        Log.debug("Select all Department records");
        query.prettyPrint("Department Name", "Location", "Company Name");
        return query;
    }

    public static SqlQuery getAllCompanies() {
        Manager<Company> companyManager = new Manager<>(Company.class);
        String sqlQuery = "SELECT * FROM Company;";
        SqlQuery query = new SqlQuery(sqlQuery);
        query = companyManager.sql(query);
        Log.debug("Select all Company records");
        query.prettyPrint("Company Name", "Industry");
        return query;
    }

    public static SqlQuery getNEmployees(int n) {
        Manager<Employee> employeeManager = new Manager<>(Employee.class);
        String sqlQuery = "SELECT * FROM Employee ORDER BY id LIMIT " + n + ";";
        SqlQuery query = new SqlQuery(sqlQuery);
        query = employeeManager.sql(query);
        Log.debug("Select " + n + " Employee records");
        query.prettyPrint("Employee Name", "Pesel", "Salary", "Employed From");
        return query;
    }

    public static SqlQuery getNDepartments(int n) {
        Manager<Department> departmentManager = new Manager<>(Department.class);
        String sqlQuery = "SELECT * FROM Department ORDER BY id LIMIT " + n + ";";
        SqlQuery query = new SqlQuery(sqlQuery);
        query = departmentManager.sql(query);
        Log.debug("Select " + n + " Department records");
        query.prettyPrint("Department Name", "Location", "Company Name");
        return query;
    }

    public static SqlQuery getNCompanies(int n) {
        Manager<Company> companyManager = new Manager<>(Company.class);
        String sqlQuery = "SELECT * FROM Department ORDER BY id LIMIT " + n + ";";
        SqlQuery query = new SqlQuery(sqlQuery);
        query = companyManager.sql(query);
        Log.debug("Select " + n + " Company records");
        query.prettyPrint("Company Name", "Industry");
        return query;
    }
}
