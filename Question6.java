
import java.util.*;
import java.util.stream.Collectors;

class Employee {
    int id;
    String name;
    String department;
    double salary;

    Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    void display() {
        System.out.println(id + " " + name + " " + department + " " + salary);
    }
}

public class Question6{

    public static void main(String[] args) {

        ArrayList<Employee> list = new ArrayList<>();

        list.add(new Employee(101, "Amit", "IT", 50000));
        list.add(new Employee(102, "Rahul", "HR", 40000));
        list.add(new Employee(103, "Priya", "IT", 70000));
        list.add(new Employee(104, "Neha", "HR", 60000));
        list.add(new Employee(105, "Karan", "IT", 70000));

        

        Map<String, List<Employee>> group = list.stream()
                .collect(Collectors.groupingBy(
                        e -> e.department,
                        LinkedHashMap::new,
                        Collectors.toList()
                ));

        System.out.println("1. Department Wise Employees");

        ArrayList<String> departments = new ArrayList<>(group.keySet());

        for (int i = 0; i < departments.size(); i++) {
            String dept = departments.get(i);

            System.out.println("\nDepartment: " + dept);

            List<Employee> employees = group.get(dept);

            for (int j = 0; j < employees.size(); j++) {
                employees.get(j).display();
            }
        }


        System.out.println("\n2. Highest Paid Employee");

        /*
        ye krna hai mujhe
         */

        // 3. Second Highest Distinct Salary

        double secondSalary = list.stream()
                .map(e -> e.salary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(0.0);

        System.out.println("\n3. Second Highest Salary: " + secondSalary);

        // 4. Sort employees by salary and then by name

        List<Employee> sortedList = list.stream()
                .sorted(Comparator.comparingDouble((Employee e) -> e.salary)
                        .thenComparing(e -> e.name))
                .collect(Collectors.toList());

        System.out.println("\n4. Employees Sorted By Salary and Name");

        for (int i = 0; i < sortedList.size(); i++) {
            sortedList.get(i).display();
        }

   
        
    }
}
