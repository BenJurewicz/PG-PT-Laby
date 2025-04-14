-- -- Table: Company
-- CREATE TABLE Company (
--     id INT PRIMARY KEY,
--     name VARCHAR(100),
--     industry VARCHAR(100)
-- );

-- -- Table: Department
-- CREATE TABLE Department (
--     id INT PRIMARY KEY,
--     name VARCHAR(100),
--     location VARCHAR(100),
--     company_id INT,
--     FOREIGN KEY (company_id) REFERENCES Company(id)
-- );

-- -- Table: Employee
-- CREATE TABLE Employee (
--     id INT PRIMARY KEY,
--     name VARCHAR(100),
--     salary DECIMAL(10, 2),
--     PESEL VARCHAR(11),
--     employment_date DATE,
--     department_id INT,
--     manager_id INT,
--     FOREIGN KEY (department_id) REFERENCES Department(id),
--     FOREIGN KEY (manager_id) REFERENCES Employee(id)
-- );

-- -- Insert data into Company
-- INSERT INTO Company (id, name, industry) VALUES
-- (1, 'TechCorp', 'Technology'),
-- (2, 'HealthInc', 'Healthcare'),
-- (3, 'FinServ', 'Finance'),
-- (4, 'EduWorld', 'Education'),
-- (5, 'AutoMakers', 'Automotive'),
-- (6, 'RetailGiant', 'Retail'),
-- (7, 'Foodies', 'Food and Beverage'),
-- (8, 'TravelCo', 'Travel and Tourism'),
-- (9, 'RealEstatePro', 'Real Estate'),
-- (10, 'MediaHouse', 'Media and Entertainment');

-- -- Insert data into Department
-- INSERT INTO Department (id, name, location, company_id) VALUES
-- (1, 'Research and Development', 'New York', 1),
-- (2, 'Human Resources', 'San Francisco', 1),
-- (3, 'Operations', 'Chicago', 2),
-- (4, 'Finance', 'Los Angeles', 3),
-- (5, 'Marketing', 'Seattle', 4),
-- (6, 'Sales', 'Miami', 5),
-- (7, 'Customer Service', 'Boston', 6),
-- (8, 'Logistics', 'Dallas', 7),
-- (9, 'Legal', 'Denver', 8),
-- (10, 'IT Support', 'Atlanta', 9);

-- -- Insert data into Employee
-- INSERT INTO Employee (id, name, salary, PESEL, employment_date, department_id, manager_id) VALUES
-- (1, 'Alice Johnson', 90000.00, '12345678901', '2020-01-15', 1, NULL), -- Manager of R&D
-- (2, 'Bob Smith', 60000.00, '23456789012', '2021-06-01', 1, 1), -- Works in R&D
-- (3, 'Charlie Brown', 50000.00, '34567890123', '2022-03-10', 2, NULL), -- Manager of HR
-- (4, 'Diana Prince', 45000.00, '45678901234', '2023-07-20', 2, 3), -- Works in HR
-- (5, 'Eve Adams', 70000.00, '56789012345', '2019-11-05', 3, NULL), -- Manager of Operations
-- (6, 'Frank Castle', 55000.00, '67890123456', '2020-08-15', 3, 5), -- Works in Operation
-- (7, 'Grace Lee', 80000.00, '78901234567', '2021-12-01', 1, 1), -- Works in R&D
-- (8, 'Hank Pym', 75000.00, '89012345678', '2022-05-20', 2, 3), -- Works in HR
-- (9, 'Ivy Wong', 65000.00, '90123456789', '2023-02-14', 3, 5),
-- (10, 'Jack Daniels', 72000.00, '01234567890', '2020-09-30', 4, NULL), -- Manager of Finance
-- (11, 'Kathy Bates', 68000.00, '12345678901', '2021-04-25', 4, 10), -- Works in Finance
-- (12, 'Leo Messi', 90000.00, '23456789012', '2022-11-11', 5, NULL), -- Manager of Sales
-- (13, 'Mia Khalifa', 85000.00, '34567890123', '2023-03-15', 5, 12), -- Works in Sales
-- (14, 'Nina Simone', 70000.00, '45678901234', '2020-07-07', 6, NULL), -- Manager of Customer Service
-- (15, 'Oscar Wilde', 72000.00, '56789012345', '2021-08-18', 6, 14), -- Works in Customer Service
-- (16, 'Paul Atreides', 80000.00, '67890123456', '2022-09-09', 7, NULL), -- Manager of Logistics
-- (17, 'Quinn Fabray', 78000.00, '78901234567', '2023-10-10', 7, 16), -- Works in Logistics
-- (18, 'Rick Grimes', 95000.00, '89012345678', '2020-12-12', 8, NULL), -- Manager of Legal
-- (19, 'Steve Rogers', 90000.00, '90123456789', '2021-01-01', 8, 18), -- Works in Legal
-- (20, 'Tony Stark', 120000.00, '01234567890', '2022-02-02', 9, NULL); -- Manager of IT Support

-- select candidate for the raise of the department
SELECT e.name AS EmployeeName,
       d.name AS DepartmentName,
       e.employment_date
FROM Employee e
         JOIN Department d ON e.department_id = d.id
WHERE e.employment_date = (SELECT MIN(employment_date)
                           FROM Employee
                           WHERE department_id = e.department_id);

-- for every company show in hom many locations it has departments
SELECT c.name                     AS CompanyName,
       COUNT(DISTINCT d.location) AS NumberOfLocations
FROM Company c
         JOIN Department d ON c.id = d.company_id
GROUP BY c.name;

-- for every company show the average salary of employees in the company
SELECT c.name AS CompanyName,
AVG(e.salary) AS AverageSalary
FROM Company c
JOIN Department d ON c.id = d.company_id
JOIN Employee e ON d.id = e.department_id
GROUP BY c.name
ORDER BY AverageSalary DESC;

-- count how many employees and departments are in the company
SELECT c.name               AS CompanyName,
       COUNT(DISTINCT d.id) AS NumberOfDepartments,
       COUNT(e.id)          AS NumberOfEmployees
FROM Company c
         JOIN Department d ON c.id = d.company_id
         JOIN Employee e ON d.id = e.department_id
GROUP BY c.name
ORDER BY NumberOfDepartments DESC, NumberOfEmployees DESC;

-- show the average salary of employees in every industry
SELECT c.industry    AS Industry,
       AVG(e.salary) AS AverageSalary
FROM Company c
         JOIN Department d ON c.id = d.company_id
         JOIN Employee e ON d.id = e.department_id
GROUP BY c.industry
ORDER BY AverageSalary DESC;

