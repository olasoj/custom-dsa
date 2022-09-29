package com.dsa.misc.collection;

import java.time.LocalDate;
import java.util.HashMap;

public class MapTest {

    public static void main(String[] args) {
        var staff = new HashMap<String, Employee>();
        staff.put("144-25-5464", new Employee("Amy Lee"));
        staff.put("567-24-2546", new Employee("Harry Hacker"));
        staff.put("157-62-7935", new Employee("Gary Cooper"));
        staff.put("456-62-5527", new Employee("Francesca Cruz"));

        // print all entries
        System.out.println(staff);

        // remove an entry
        staff.remove("567-24-2546");

        // replace an entry
        staff.put("456-62-5527", new Employee("Francesca Miller"));

        // look up a value
        System.out.println(staff.get("157-62-7935"));

        // iterate through all entries
        staff.forEach((k, v) ->
                System.out.println("key=" + k + ", value=" + v));
    }

    static class Employee {
        private String name;
        private double salary;
        private LocalDate hireDay;

        public Employee(String name) {
            this.name = name;
        }

        public Employee(String name, double salary, int year, int month, int day) {
            this.name = name;
            this.salary = salary;
            this.hireDay = LocalDate.of(year, month, day);
        }

        public Employee() {
        }

        public Employee(String name, double salary, LocalDate hireDay) {
            this.name = name;
            this.salary = salary;
            this.hireDay = hireDay;
        }

        public String getName() {
            return name;
        }

        public double getSalary() {
            return salary;
        }

        public LocalDate getHireDay() {
            return hireDay;
        }
    }
}

