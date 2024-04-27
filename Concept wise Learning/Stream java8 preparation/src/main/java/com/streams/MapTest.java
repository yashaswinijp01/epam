package com.streams;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

public class MapTest {
    public  static void main(String[] args){
        Map<Employee, String> map = new HashMap<>();
/*
        map.put(new Employee("ram","CS",2000),"hello");
        System.out.println(map);
        System.out.println(map.get(new Employee("ram","CS",2000)));

        Map<String, Employee> map1 = new HashMap<>();
        map1.put("hello", new Employee("ram","CS",2000));
        System.out.println(map1.get("hello"));

 */

        Employee e1= new Employee("ram","CS",50000);
        Employee e2= new Employee("sagar","EC",20000);
        Employee e3= new Employee("arun","CS",70000);
        Employee e4= new Employee("yash","EC",10000);
        Employee e5= new Employee("mani","EE",90000);

        map.put(e1,"one");
        map.put(e2,"two");
        map.put(e3,"three");
        map.put(e4,"four");
        map.put(e5,"three");
        System.out.println(map);

        Iterator<Employee> it = map.keySet().iterator();
        while (it.hasNext()){
            Employee emp = it.next();
            System.out.println(emp);
        }
//Write a Java Program to iterate HashMap using While and advance for loop.
        HashMap<Integer,String> map1 = new HashMap<>();
        map1.put(2, "Saket");
        map1.put(25, "Saurav");
        map1.put(12, "HashMap");
        System.out.println(map1.size());
        System.out.println("While Loop:");
        Iterator itr = map1.entrySet().iterator();
        while(itr.hasNext()) {
            Map.Entry me = (Map.Entry) itr.next();
            System.out.println("Key is " + me.getKey() + " Value is " + me.getValue());
        }
        System.out.println("For Loop:");
        for(Map.Entry me2: map1.entrySet()) {
            System.out.println("Key is: " + me2.getKey() + " Value is: " + me2.getValue());
        }

        //to get the employee name starts with a using streams
        System.out.println(map.entrySet());
        System.out.println(map.entrySet().stream().filter(e->e.getKey().getName().startsWith("a")).collect(Collectors.toList()));
        System.out.println(map.entrySet().stream().filter(e->e.getKey().getName().startsWith("a")).map(Map.Entry::getKey).collect(Collectors.toList()));
        map.entrySet().stream().filter(e->e.getKey().getName().startsWith("a")).map(Map.Entry::getKey).forEach(System.out::println);
        map.entrySet().stream().filter(e->e.getKey().getName().startsWith("a")).map(Map.Entry::getKey).map(Employee::getName).forEach(System.out::println);

        //sorting map

    }
}
