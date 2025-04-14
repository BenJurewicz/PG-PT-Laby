package jkz;

import jkz.Database.Entities.Company;
import jkz.Database.Entities.Department;
import jkz.Database.Entities.Employee;
import jkz.Database.Managers.DepartmentManager;
import jkz.Database.Managers.CompanyManager;
import jkz.Database.Managers.EmployeeManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseSeeder {
    private final CompanyManager companyManager;
    private final DepartmentManager departmentManager;
    private final EmployeeManager employeeManager;

    public DatabaseSeeder() {
        this.companyManager = new CompanyManager();
        this.departmentManager = new DepartmentManager();
        this.employeeManager = new EmployeeManager();
    }

    public void seedAll() {
        try {
            addCompanyData("Google", "Technology",
                    new String[] { "Engineering", "Research", "Product" },
                    new String[] { "London", "Zurich", "New York" });

            addCompanyData("Amazon", "E-commerce",
                    new String[] { "Logistics", "Marketplace", "AWS" },
                    new String[] { "Seattle", "Berlin", "Tokyo" });

            addCompanyData("Tesla", "Automotive",
                    new String[] { "Design", "Manufacturing", "Autopilot" },
                    new String[] { "Fremont", "Shanghai", "Berlin" });

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void addCompanyData(String companyName, String industry, String[] deptNames, String[] locations)
            throws ParseException {
        Company company = new Company(companyName, industry);
        companyManager.add(company);

        for (int i = 0; i < deptNames.length; i++) {
            Department department = new Department(deptNames[i], locations[i], company);
            departmentManager.add(department);

            addEmployeesToDepartment(department);
        }
    }

    private void addEmployeesToDepartment(Department department) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String[] firstNames = {
                "Alice", "Bob", "Charlie", "Diana", "Ethan", "Fiona", "George", "Hannah",
                "Ivan", "Julia", "Kevin", "Laura", "Kuba", "Nina", "Oscar", "Paula"
        };

        String[] lastNames = {
                "Smith", "Johnson", "Brown", "Taylor", "Anderson", "Thomas", "Jackson",
                "White", "Harris", "Martin", "Le", "Walker", "Hall", "Allen", "Young"
        };

        for (int i = 1; i <= (5 + (int) (Math.random() * 6)); i++) { // 5-10 employees
            String fullName = firstNames[(int) (Math.random() * firstNames.length)] + " " +
                    lastNames[(int) (Math.random() * lastNames.length)];
            String pesel = String.valueOf(100000000 + (int) (Math.random() * 900000000));
            float salary = 3000 + (float) (Math.random() * 5000);
            Date employedFrom = formatter.parse("2020-0" + ((i % 9) + 1) + "-0" + ((i % 27) + 1));

            Employee employee = new Employee(fullName, pesel, salary, employedFrom, department);
            employeeManager.add(employee);
        }
    }
}
