package jkz;

import jkz.Database.Entities.Company;
import jkz.Database.Entities.Employee;
import jkz.Database.Managers.Manager;
import jkz.Logging.Log;

import java.util.List;

public class Requests {
    public static List<?>  getCandidatesForRaise(){
        Manager<Employee> employeeManager = new Manager<>(Employee.class);
        String sqlQuery = "SELECT name as Names FROM Employee";
        List<?> result = employeeManager.sql(sqlQuery);
        Log.debug("Candidates for raise: ", result.toString());

//        prettyPrintQueryResult(result, "Names");
        prettyPrintQueryResult(result);
        return result;
    }

    public static List<?>  countLocationsForDepartments(){
        Manager<Company> companyManager = new Manager<>(Company.class);
        String sqlQuery = "SELECT c.name AS CompanyName, COUNT(DISTINCT d.location) AS NumberOfLocations FROM Company c JOIN Department d ON c.id = d.company_id GROUP BY c.name;";
        List<?> result = companyManager.sql(sqlQuery);
        Log.debug("For every company, in how many location it has departments");
        prettyPrintQueryResult(result);
        return result;
    }

    public static List<?>  averageSalaryForEveryCompany(){
        Manager<Company> companyManager = new Manager<>(Company.class);
        String sqlQuery = "SELECT c.name AS CompanyName, AVG(e.salary) AS AverageSalary FROM Company c JOIN Department d ON c.id = d.company_id JOIN Employee e ON d.id = e.department_id GROUP BY c.name ORDER BY AverageSalary DESC;";
        List<?> result = companyManager.sql(sqlQuery);
        Log.debug("For every company, calculate average salary");
        prettyPrintQueryResult(result);
        return result;
    }

    public static List<?>  countEmployeesAndDepartments(){
        Manager<Company> companyManager = new Manager<>(Company.class);
        String sqlQuery = "SELECT c.name AS CompanyName, COUNT(DISTINCT d.id) AS NumberOfDepartments, COUNT(e.id) AS NumberOfEmployees FROM Company c JOIN Department d ON c.id = d.company_id JOIN Employee e ON d.id = e.department_id GROUP BY c.name ORDER BY NumberOfDepartments DESC, NumberOfEmployees DESC;";
        List<?> result = companyManager.sql(sqlQuery);
        Log.debug("For every company, count employees and departments");
        prettyPrintQueryResult(result);
        return result;
    }

    public static List<?>  avarageSalaryForIndustries(){
        Manager<Company> companyManager = new Manager<>(Company.class);
        String sqlQuery = "SELECT c.industry AS Industry, AVG(e.salary) AS AverageSalary FROM Company c JOIN Department d ON c.id = d.company_id JOIN Employee e ON d.id = e.department_id GROUP BY c.industry ORDER BY AverageSalary DESC;";
        List<?> result = companyManager.sql(sqlQuery);
        Log.debug("For every industry, calculate average salary");
        prettyPrintQueryResult(result);
        return result;
    }

    public static void prettyPrintQueryResult(List<?> result) {
        if (result == null || result.isEmpty()) {
            Log.println("No results found.");
            return;
        }

        Log.println("Query Results:");
        Log.println("-----------------------------------");

//        for (String columnName : colNames) {
//            Log.print(columnName + "\t");
//        }
        Log.println();
        Log.println("-----------------------------------");

        for (Object row : result) {
            if (row instanceof Object[]) {
                Object[] columns = (Object[]) row;
                for (Object column : columns) {
                    Log.print(column + "\t");
                }
                Log.println();
            } else {
                Log.println(row.toString());
            }
        }
        Log.println("-----------------------------------");
    }
}