package com.streams;

import java.util.*;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args){
        /*Employee e1= new Employee("ram","CS",50000);
        Employee e2= new Employee("sagar","EC",20000);
        Employee e3= new Employee("arun","CS",70000);
        Employee e4= new Employee("yash","EC",10000);
        Employee e5= new Employee("mani","EE",90000);
         */

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("ram","CS",50000));
        employees.add(new Employee("sagar","EC",20000));
        employees.add(new Employee("arun","CS",70000));
        employees.add(new Employee("yash","EC",10000));
        employees.add(new Employee("rani","EE",90000));

        employees.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);


//1 To group the employees based on department
        Map<String, List<Employee>> emp = employees.stream().collect(Collectors.groupingBy(e->e.getDepartment()));
        Map<String, List<Employee>> emp1 = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println(emp);
        System.out.println(emp1);
        System.out.println(employees.stream().collect(Collectors.groupingBy(e->e.getDepartment())));
        System.out.println(emp.get("EC"));

//2 To get highest paid employee from each department
        Map<String, Employee> highestPaidEmployee = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(
                                                Comparator.comparingDouble(Employee::getSalary)
                                        ), Optional::get)
                                )
                        );

        System.out.println(highestPaidEmployee);
        System.out.println("--------------");
//or
        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)))));

//3 To get average salary of employee from each group
        Map<String, Double> avgSalary = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingInt(Employee::getSalary)));
        System.out.println(avgSalary);

//4 To get count of employees in each department
        Map<String, Long> countOfEmployeesFromEachDepartment = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting() ));
        System.out.println(countOfEmployeesFromEachDepartment);

//5 To get average salary of all employees
        Double avgSalaryOfAllEmployees = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avgSalaryOfAllEmployees);

//6 To get average salary of CS department employees
        Double avgSalaryOfCsDepartmentEmployees = employees.stream()
                .filter(e->e.getDepartment().equals("CS"))
                .mapToDouble(Employee::getSalary).average().getAsDouble();
        System.out.println(avgSalaryOfCsDepartmentEmployees);

//7 To get employee department whose names starts with r
        List<String> empDepartment = employees.stream()
                .filter(employee -> employee.getName().startsWith("r"))
                .map(Employee::getDepartment).collect(Collectors.toList());
        System.out.println(empDepartment);

// To get highest paid employee from department
        //System.out.println(employees.stream().map(employee -> employee.getDepartment()));
        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.maxBy(Comparator.comparing(Employee::getSalary)))));

    }
}
