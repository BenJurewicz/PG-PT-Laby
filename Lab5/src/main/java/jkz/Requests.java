package jkz;

import jkz.Database.Entities.Employee;
import jkz.Database.Managers.Manager;
import jkz.Database.SqlQuery;
import jkz.Logging.Log;

import java.util.List;
import java.util.Arrays;

public class Requests {
    public static void  getCandidatesForRaise(){
        Manager<Employee> employeeManager = new Manager<>(Employee.class);
        String sqlQuery = "SELECT name, pesel, salary, employedFrom FROM Employee";
        SqlQuery query = new SqlQuery(sqlQuery);
        query = employeeManager.sql(query);
        query.prettyPrint("Names", "Pesel", "Salary", "Employed From");
    }
}