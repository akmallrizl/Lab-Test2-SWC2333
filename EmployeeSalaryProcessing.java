
/**
 * Programme Description: To calculate the salaries of each employee in the company
 * Programmer: akmalrizl
 * Date: 13 March 2024
 */
import java.io.*;
import java.util.*;

class Employee {
    private String name;
    private double baseSalary;
    private int yearsOfService;

    public Employee(String name, double baseSalary, int yearsOfService) {
        this.name = name;
        this.baseSalary = baseSalary;
        this.yearsOfService = yearsOfService;
    }

    public String getName() {
        return name;
    }

    public double calculateAnnualSalary() {
        return baseSalary + (baseSalary * 0.05 * yearsOfService);
    }

    public int getYearsOfService() {
        return yearsOfService;
    }
}

public class EmployeeSalaryProcessing {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ashfx\\Downloads\\employeeSalaries.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    double baseSalary = Double.parseDouble(parts[1]);
                    int yearsOfService = Integer.parseInt(parts[2]);
                    employees.add(new Employee(name, baseSalary, yearsOfService));
                } else {
                    System.err.println("Invalid data format: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading data: " + e.getMessage());
            return;
        }

        if (employees.isEmpty()) {
            System.out.println("No employee data found.");
            return;
        }

        // Task 2: Calculate and display annual salary for each employee
        System.out.println("Annual Salaries:");
        for (Employee emp : employees) {
            System.out.println(emp.getName() + ": $" + emp.calculateAnnualSalary());
        }

        // Task 3: Identify and display the top-performing employee
        Employee topEmployee = Collections.max(employees, Comparator.comparing(Employee::calculateAnnualSalary));
        System.out.println("Top Performing Employee: " + topEmployee.getName());

        // Task 4: Identify and display the employee with the least years of service
        Employee leastYearsEmployee = Collections.min(employees, Comparator.comparing(Employee::getYearsOfService));
        System.out.println("Employee with Least Years of Service: " + leastYearsEmployee.getName());

        // Task 6: Store employee data in output file
        try (PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\ashfx\\Downloads\\employeeSalaries.txt"))) {
            for (Employee emp : employees) {
                writer.println("Name: " + emp.getName() + ", Annual Salary: $" + emp.calculateAnnualSalary() +
                        ", Years of Service: " + emp.getYearsOfService());
            }
            writer.println("Top Performing Employee: " + topEmployee.getName());
            writer.println("Employee with Least Years of Service: " + leastYearsEmployee.getName());
            System.out.println("Employee data written to employeeData.txt");
        } catch (IOException e) {
            System.err.println("Error writing data: " + e.getMessage());
        }
    }
}

