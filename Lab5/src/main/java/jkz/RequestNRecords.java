package jkz;

import jkz.Database.Entities.Company;
import jkz.Database.Entities.Department;
import jkz.Database.Entities.Employee;
import jkz.Database.Managers.Manager;
import jkz.Logging.Log;

import java.util.List;

public class RequestNRecords {
    public static List<?>  getRecords(String className, int n){
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
        return List.of();
    }
    public static List<?>  getAllEmployees(){
        Manager<Employee> employeeManager = new Manager<>(Employee.class);
        String sqlQuery = "SELECT * FROM Employee;";
        List<?> result = employeeManager.sql(sqlQuery);
        Log.debug("Select all Employee records");
        prettyPrintQueryResult(result);
        return result;
    }
    public static List<?>  getAllDepartments(){
        Manager<Department> departmentManager = new Manager<>(Department.class);
        String sqlQuery = "SELECT * FROM Department;";
        List<?> result = departmentManager.sql(sqlQuery);
        Log.debug("Select all Department records");
        prettyPrintQueryResult(result);
        return result;
    }
    public static List<?>  getAllCompanies(){
        Manager<Company> companyManager = new Manager<>(Company.class);
        String sqlQuery = "SELECT * FROM Company;";
        List<?> result = companyManager.sql(sqlQuery);
        Log.debug("Select all Company records");
        prettyPrintQueryResult(result);
        return result;
    }
    public static List<?>  getNEmployees(int n){
        Manager<Employee> employeeManager = new Manager<>(Employee.class);
        String sqlQuery = "SELECT * FROM Employee ORDER BY id LIMIT " + n + ";";
        List<?> result = employeeManager.sql(sqlQuery);
        Log.debug("Select " + n + " Employee records");
        prettyPrintQueryResult(result);
        return result;
    }
    public static List<?>  getNDepartments(int n){
        Manager<Department> departmentManager = new Manager<>(Department.class);
        String sqlQuery = "SELECT * FROM Department ORDER BY id LIMIT " + n + ";";
        List<?> result = departmentManager.sql(sqlQuery);
        Log.debug("Select " + n + " Department records");
        prettyPrintQueryResult(result);
        return result;
    }
    public static List<?>  getNCompanies(int n){
        Manager<Company> companyManager = new Manager<>(Company.class);
        String sqlQuery = "SELECT * FROM Department ORDER BY id LIMIT " + n + ";";
        List<?> result = companyManager.sql(sqlQuery);
        Log.debug("Select " + n + " Company records");
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
