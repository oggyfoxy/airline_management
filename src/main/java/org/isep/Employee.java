package org.isep;

import java.util.ArrayList;
import java.util.List;

public abstract class Employee extends Person {
    private int NumberEmp;
    private String HiringDate;
    private static List<Employee> employeeList = new ArrayList<>();

    public Employee(int ID, String Name, String Address, String Contact, int NumberEmp, String HiringDate) {
        super(ID, Name, Address, Contact);
        this.NumberEmp = NumberEmp;
        this.HiringDate = HiringDate;
    }

    // Getters and Setters
    public int getNumberEmp() {
        return NumberEmp;
    }
    public void setNumberEmp(int NumberEmp) {
        this.NumberEmp = NumberEmp;
    }

    public String getHiringDate() {
        return HiringDate;
    }
    public void setHiringDate(String HiringDate) {
        this.HiringDate = HiringDate;
    }

    public abstract String getRole();

    // CRUD Operations
    public static void addEmployee(Employee e) {
        employeeList.add(e);
    }

    public static void modifyEmployee(int NumberEmp, Employee newEmployee) {
        for (Employee e : employeeList) {
            if (e.getNumberEmp() == NumberEmp) {
                e.setName(newEmployee.getName());
                e.setAddress(newEmployee.getAddress());
                e.setContact(newEmployee.getContact());
                e.setHiringDate(newEmployee.getHiringDate());
                break;
            }
        }
    }

    public static Employee retrieveEmployee(int NumberEmp) {
        for (Employee e : employeeList) {
            if (e.getNumberEmp() == NumberEmp) {
                return e;
            }
        }
        return null;
    }

    public static void deleteEmployee(int NumberEmp) {
        employeeList.removeIf(e -> e.getNumberEmp() == NumberEmp);
    }
}
