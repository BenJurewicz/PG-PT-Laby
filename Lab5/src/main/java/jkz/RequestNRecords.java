package jkz;

import jkz.Database.Entities.Company;
import jkz.Database.Entities.Department;
import jkz.Database.Entities.Employee;
import jkz.Database.Managers.Manager;
import jkz.Database.SqlQuery;
import jkz.Logging.Log;

import java.util.List;

public class RequestNRecords {
    public static void  getRecords(String className, int n){
        String lowerClassName = className.toLowerCase();
        if(n == 0){
            switch (lowerClassName) {
                case "company" -> getAllCompanies();
                case "department" -> getAllDepartments();
                case "employee" -> getAllEmployees();
            }
        }else{
            switch (lowerClassName){
                case "company" -> getNCompanies(n);
                case "department" -> getNDepartments(n);
                case "employee" -> getNEmployees(n);
            }
        }
    }
    public static List<?>  getAllEmployees(){
        Manager<Employee> employeeManager = new Manager<>(Employee.class);
        String sqlQuery = "SELECT * FROM Employee;";
        List<?> result = employeeManager.sql(sqlQuery);
        Log.debug("Select all Employee records");

        return result;
    }
    public static List<?>  getAllDepartments(){
        Manager<Department> departmentManager = new Manager<>(Department.class);
        String sqlQuery = "SELECT * FROM Department;";
        List<?> result = departmentManager.sql(sqlQuery);
        Log.debug("Select all Department records");
        SqlQuery query = new SqlQuery(sqlQuery, result);
        query.prettyPrint("Department Name", "Location", "Company Name");
        return result;
    }
    public static List<?>  getAllCompanies(){
        Manager<Company> companyManager = new Manager<>(Company.class);
        String sqlQuery = "SELECT * FROM Company;";
        List<?> result = companyManager.sql(sqlQuery);
        Log.debug("Select all Company records");
        SqlQuery query = new SqlQuery(sqlQuery, result);
        query.prettyPrint("Company Name", "Industry");
        return result;
    }
    public static List<?>  getNEmployees(int n){
        Manager<Employee> employeeManager = new Manager<>(Employee.class);
        String sqlQuery = "SELECT * FROM Employee ORDER BY id LIMIT " + n + ";";
        List<?> result = employeeManager.sql(sqlQuery);
        Log.debug("Select " + n + " Employee records");
        SqlQuery query = new SqlQuery(sqlQuery, result);
        query.prettyPrint("Employee Name", "Pesel", "Salary", "Employed From");
        return result;
    }
    public static List<?>  getNDepartments(int n){
        Manager<Department> departmentManager = new Manager<>(Department.class);
        String sqlQuery = "SELECT * FROM Department ORDER BY id LIMIT " + n + ";";
        List<?> result = departmentManager.sql(sqlQuery);
        Log.debug("Select " + n + " Department records");
        SqlQuery query = new SqlQuery(sqlQuery, result);
        query.prettyPrint("Department Name", "Location", "Company Name");
        return result;
    }
    public static List<?>  getNCompanies(int n){
        Manager<Company> companyManager = new Manager<>(Company.class);
        String sqlQuery = "SELECT * FROM Department ORDER BY id LIMIT " + n + ";";
        List<?> result = companyManager.sql(sqlQuery);
        Log.debug("Select " + n + " Company records");
        SqlQuery query = new SqlQuery(sqlQuery, result);
        query.prettyPrint("Company Name", "Industry");
        return result;
    }
}
