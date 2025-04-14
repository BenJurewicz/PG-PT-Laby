package jkz;

import jkz.Database.Entities.Employee;
import jkz.Database.Managers.Manager;
import jkz.Logging.Log;

import java.util.List;

public class Requests {
    public static List<?>  getCandidatesForRaise(){
        Manager<Employee> employeeManager = new Manager<>(Employee.class);
        String sqlQuery = "SELECT e.name AS EmployeeName," +
                "       d.name AS DepartmentName," +
                "       e.employment_date" +
                "FROM Employee e" +
                "         JOIN Department d ON e.department_id = d.id" +
                "WHERE e.employment_date = (SELECT MIN(employment_date)" +
                "                           FROM Employee" +
                "                           WHERE department_id = e.department_id);";
        List<?> result = employeeManager.sql(sqlQuery);
        Log.debug("Candidates for raise: ", result.toString());
        return result;
    }
}