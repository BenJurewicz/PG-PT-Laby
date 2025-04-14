package jkz;

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