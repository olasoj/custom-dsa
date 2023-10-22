package com.dsa.misc.stream;

import java.util.ArrayList;
import java.util.List;

public class StreamReduceToDifferentType {

    public static void main(String[] args) {

        /*
         * List of employees
         */
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("John", "IT"));
        list.add(new Employee("Tom", "Service"));
        list.add(new Employee("Ravi", "Development"));
        list.add(new Employee("Jimmy", "Management"));

        /*
         * With System.out.println in accumulator for understanding.
         */
        String reducedString1 = list
                .stream()
                .reduce("", (nameDept, employee) -> {
                    System.out.println("nameDept = " + nameDept + " | employee = " + employee);

                    return nameDept + " (" + employee.name + "-" + employee.department + ")";
                }, (a, b) -> a + b);

        System.out.println("Final reduced string = " + reducedString1);

        /*
         * Without any System.out.println
         */
        String reducedString = list.stream().reduce("",
                (nameDeptString, employeeObject) -> nameDeptString = nameDeptString + " (" + employeeObject.name + "-"
                        + employeeObject.department + ")",
                (a, b) -> a + b);

        System.out.println("Final reduced string = " + reducedString);
    }

}

class Employee {
    String name;
    String department;

    public Employee(String name, String department) {
        super();
        this.name = name;
        this.department = department;
    }

}
