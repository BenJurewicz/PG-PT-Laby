package jkz;

import jkz.Database.Entities.Company;
import jkz.Database.Entities.Employee;
import jkz.Database.Managers.Manager;
import jkz.Logging.Log;

import java.util.List;

public class Requests {
    public static List<?>  getCandidatesForRaise(){
        Manager<Employee> employeeManager = new Manager<>(Employee.class);
        String sqlQuery = "SELECT e.name AS EmployeeName, d.name AS DepartmentName, e.employedFrom FROM Employee e JOIN Department d ON e.department_id = d.id WHERE e.employedFrom = (SELECT MIN(employedFrom) FROM Employee WHERE department_id = e.department_id);";
        List<?> result = employeeManager.sql(sqlQuery);
        Log.debug("Candidates for raise: ", result.toString());
        return result;
    }

    public static List<?>  countLocationsForDepartments(){
        Manager<Company> companyManager = new Manager<>(Company.class);
        String sqlQuery = "SELECT c.name AS CompanyName, COUNT(DISTINCT d.location) AS NumberOfLocations FROM Company c JOIN Department d ON c.id = d.company_id GROUP BY c.name;";
        List<?> result = companyManager.sql(sqlQuery);
        Log.debug("For every company, in how many location it has departments: ", result.toString());
        return result;
    }

    public static List<?>  averageSalaryForEveryCompany(){
        Manager<Company> companyManager = new Manager<>(Company.class);
        String sqlQuery = "SELECT c.name AS CompanyName, AVG(e.salary) AS AverageSalary FROM Company c JOIN Department d ON c.id = d.company_id JOIN Employee e ON d.id = e.department_id GROUP BY c.name ORDER BY AverageSalary DESC;";
        List<?> result = companyManager.sql(sqlQuery);
        Log.debug("For every company, calculate average salary: ", result.toString());
        return result;
    }

    public static List<?>  countEmployeesAndDepartments(){
        Manager<Company> companyManager = new Manager<>(Company.class);
        String sqlQuery = "SELECT c.name AS CompanyName, COUNT(DISTINCT d.id) AS NumberOfDepartments, COUNT(e.id) AS NumberOfEmployees FROM Company c JOIN Department d ON c.id = d.company_id JOIN Employee e ON d.id = e.department_id GROUP BY c.name ORDER BY NumberOfDepartments DESC, NumberOfEmployees DESC;";
        List<?> result = companyManager.sql(sqlQuery);
        Log.debug("For every company, count employees and departments: ", result.toString());
        return result;
    }

    public static List<?>  avarageSalaryForIndustries(){
        Manager<Company> companyManager = new Manager<>(Company.class);
        String sqlQuery = "SELECT c.industry AS Industry, AVG(e.salary) AS AverageSalary FROM Company c JOIN Department d ON c.id = d.company_id JOIN Employee e ON d.id = e.department_id GROUP BY c.industry ORDER BY AverageSalary DESC;";
        List<?> result = companyManager.sql(sqlQuery);
        Log.debug("For every industry, calculate average salary: ", result.toString());
        return result;
    }
}