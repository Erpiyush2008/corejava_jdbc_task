
import java.util.*;

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

public class Question1 {

    static ArrayList<Integer> findDuplicate(ArrayList<Employee> list) {

        ArrayList<Integer> dup = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            int count = 0;
            int id = list.get(i).id;

            for (int j = 0; j < list.size(); j++) {

                if (id == list.get(j).id) {
                    count++;
                }
            }

            if (count > 1 && !dup.contains(id)) {
                dup.add(id);
            }
        }

        return dup;
    }

    static void showDuplicate(ArrayList<Employee> list) {

        ArrayList<Integer> dup = findDuplicate(list);

        for (int i = 0; i < list.size(); i++) {

            Employee e = list.get(i);

            if (dup.contains(e.id)) {
                e.display();
            }
        }
    }

  
    static ArrayList<Employee> removeDuplicate(ArrayList<Employee> list) {

        ArrayList<Employee> temp = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            Employee e = list.get(i);
            boolean found = false;

            for (int j = 0; j < temp.size(); j++) {

                Employee x = temp.get(j);

                if (e.id == x.id) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                temp.add(e);
            }
        }

        return temp;
    }

    
    static void secondSalary(ArrayList<Employee> list) {

        ArrayList<Employee> temp = removeDuplicate(list);
        ArrayList<Double> salary = new ArrayList<>();

        for (int i = 0; i < temp.size(); i++) {

            double s = temp.get(i).salary;

            if (!salary.contains(s)) {
                salary.add(s);
            }
        }

        Collections.sort(salary, Collections.reverseOrder());

      
            System.out.println("Second Highest Salary: " + salary.get(1));
      
    }

    static void sameSalary(ArrayList<Employee> list) {

        ArrayList<Employee> temp = removeDuplicate(list);
        ArrayList<Double> done = new ArrayList<>();

        for (int i = 0; i < temp.size(); i++) {

            double s = temp.get(i).salary;
            int count = 0;

            if (done.contains(s)) {
                continue;
            }

            for (int j = 0; j < temp.size(); j++) {

                if (s == temp.get(j).salary) {
                    count++;
                }
            }

            if (count > 1) {

                System.out.println("\nSalary: " + s);

                for (int j = 0; j < temp.size(); j++) {

                    Employee e = temp.get(j);

                    if (e.salary == s) {
                        e.display();
                    }
                }
            }

            done.add(s);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Employee> list = new ArrayList<>();

        list.add(new Employee(101, "Rahul", "IT", 50000));
        list.add(new Employee(102, "Amit", "HR", 40000));
        list.add(new Employee(103, "Priya", "IT", 60000));
        list.add(new Employee(104, "Neha", "Sales", 45000));
        list.add(new Employee(105, "Rohit", "IT", 50000));
        list.add(new Employee(106, "Karan", "HR", 70000));
        list.add(new Employee(107, "Simran", "Sales", 35000));
        list.add(new Employee(108, "Ankit", "IT", 55000));
        list.add(new Employee(109, "Pooja", "HR", 40000));
        list.add(new Employee(110, "Vikas", "Sales", 65000));
        list.add(new Employee(101, "Rahul", "IT", 50000));
        list.add(new Employee(102, "Amit", "HR", 40000));
        list.add(new Employee(111, "Ravi", "IT", 75000));
        list.add(new Employee(112, "Meena", "HR", 55000));
        list.add(new Employee(103, "Priya", "IT", 60000));

        while (true) {

            System.out.println("\n1. Find Duplicate IDs");
            System.out.println("2. Show Duplicate Employees");
            System.out.println("3. Remove Duplicates");
            System.out.println("4. Second Highest Salary");
            System.out.println("5. Same Salary Employees");
            System.out.println("6. Show All Employees");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {

                case 1 -> System.out.println(findDuplicate(list));

                case 2 -> showDuplicate(list);

                case 3 -> {
                    list = removeDuplicate(list);
                    System.out.println("Duplicates removed");

                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).display();
                    }
                }

                case 4 -> secondSalary(list);

                case 5 -> sameSalary(list);

                case 6 -> {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).display();
                    }
                }

                case 0 -> {
                    System.out.println("Exit");
                    sc.close();
                    return;
                }

                default -> System.out.println("Wrong choice");
            }
        }
    }
}
