CREATE DATABASE EmployeeDB;

USE EmployeeDB;
CREATE TABLE Employee (
    ID INT PRIMARY KEY,
    Name VARCHAR(50),
    Department VARCHAR(50),
    Salary DECIMAL(10,2)
);
INSERT INTO Employee (ID, Name, Department, Salary)
VALUES
(1, 'Amit', 'IT', 50000),
(2, 'Rahul', 'HR', 40000),
(3, 'Priya', 'IT', 80000),
(4, 'Neha', 'HR', 60000),
(5, 'Karan', 'IT', 80000),
(6, 'Simran', 'Sales', 45000),
(7, 'Mohit', 'Sales', 55000);

/*.	Find the second-highest distinct salary.
*/
SELECT MAX(Salary) as second_highest
FROM Employee
WHERE Salary < (SELECT MAX(Salary) FROM Employee);


/* 23.	Find the highest salary in each department.*/

SELECT Department, MAX(Salary) AS highest_Salary FROM Employee GROUP BY Department;


/*
24.	Find employees earning more than their department's average salary.
*/

SELECT *
FROM Employee e
WHERE Salary > (
    SELECT AVG(Salary)
    FROM Employee
    WHERE Department = e.Department
);

/*25.	Find departments having more than one employee.*/

SELECT Department, COUNT(*) AS total_Employees
FROM Employee
GROUP BY Department
HAVING COUNT(*) > 1;



/*26.	Find employees having duplicate salaries.*/

SELECT *
FROM Employee
WHERE Salary IN (
    SELECT Salary
    FROM Employee
    GROUP BY Salary
    HAVING COUNT(*) > 1
);

/* 27.	Find the third-highest distinct salary.*/

SELECT DISTINCT Salary
FROM Employee
ORDER BY Salary DESC
LIMIT 1 OFFSET 2;


/* 28.	Display the employee(s) receiving the maximum salary in the company.*/

SELECT *
FROM Employee
WHERE Salary = (
    SELECT MAX(Salary)
    FROM Employee
);




/*
 user_regestraion database
CREATE DATABASE UserDB;

USE UserDB;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(50)
);





// Jdbc-money_transfer


CREATE DATABASE BankDB;

USE BankDB;

CREATE TABLE accounts (
    id INT PRIMARY KEY,
    account_name VARCHAR(50),
    balance DECIMAL(10,2)
);

INSERT INTO accounts VALUES
(1, 'A', 50000),
(2, 'B', 20000);

CREATE TABLE transaction_history (
    id INT PRIMARY KEY AUTO_INCREMENT,
    from_account INT,
    to_account INT,
    amount DECIMAL(10,2),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
*
/