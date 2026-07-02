package com.cognizant.deepskilling.solid;

import java.util.HashMap;
import java.util.Map;

// ============================================================
// Single Responsibility Principle (SRP)
// "A class should have one, and only one, reason to change."
// ============================================================

// --- VIOLATION: One class doing everything ---
class EmployeeBad {
    private String name;
    private double salary;

    public EmployeeBad(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public double calculateTax() {
        return salary * 0.2;
    }

    public void saveToDatabase() {
        System.out.println("INSERT INTO employee VALUES (" + name + ", " + salary + ")");
    }

    public void printPaySlip() {
        System.out.println("Payslip for " + name + ": $" + salary);
    }
}

// --- CORRECT: Separated responsibilities ---
class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() { return name; }
    public double getSalary() { return salary; }
}

class TaxCalculator {
    public double calculateTax(Employee emp) {
        return emp.getSalary() * 0.2;
    }
}

class EmployeeRepository {
    public void save(Employee emp) {
        System.out.println("INSERT INTO employee VALUES (" + emp.getName() + ", " + emp.getSalary() + ")");
    }
}

class PaySlipPrinter {
    public void print(Employee emp) {
        System.out.println("Payslip for " + emp.getName() + ": $" + emp.getSalary());
    }
}

public class SRP_Demo {
    public static void main(String[] args) {
        System.out.println("=== SRP - Single Responsibility Principle ===");
        Employee emp = new Employee("Alice", 75000);
        new TaxCalculator().calculateTax(emp);
        new EmployeeRepository().save(emp);
        new PaySlipPrinter().print(emp);
        System.out.println("Each class has exactly one reason to change.\n");
    }
}
