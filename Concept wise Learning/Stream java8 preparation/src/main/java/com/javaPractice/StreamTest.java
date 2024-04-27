package com.javaPractice;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
//import java.util.stream.*;

public class StreamTest {
    public static void main(String[] args) {

        Student student1 = new Student(
                "Jayesh",
                20,
                new Address("1234"),
                Arrays.asList(new MobileNumber("1233"), new MobileNumber("1234")));

        Student student2 = new Student(
                "Khyati",
                20,
                new Address("1235"),
                Arrays.asList(new MobileNumber("1111"), new MobileNumber("3333"), new MobileNumber("1233")));

        Student student3 = new Student(
                "Jason",
                20,
                new Address("1236"),
                Arrays.asList(new MobileNumber("3333"), new MobileNumber("4444")));

        List<Student> students = Arrays.asList(student1, student2, student3);


        //1.Get student with exact match name "jayesh"
        Student student = students.stream()
                .filter(e->e.getName().equals("Jayesh")).findAny().get();
        System.out.println(student);

        //2.Get student with matching address "1235"
        Optional<Student> studentx = students.stream()
        .filter(e->e.getAddress().getZipcode().equals("1235")).findAny();
        System.out.println(studentx);

        //3. Get all student having mobile numbers 3333
        List<Student> studentlist = students.stream()
                //.filter(e->e.getMobileNumbers()).stream()
                .filter(f->f.getMobileNumbers().stream().filter(e-> Boolean.parseBoolean(e.getNumber())).equals("3333")).collect(Collectors.toList());
        System.out.println(studentlist);




        List<String> nameList = Arrays.asList("Jayesh", "Dany", "Khyati", "Hello", "Mango");
        nameList.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

//New example
//1. How to find duplicate elements in a given integers list in java using Stream functions?
        List<Integer> myList = Arrays.asList(10,15,8,49,25,98,98,32,15);
        Set<Integer> set = new HashSet();
        myList.stream()
                .filter(n -> !set.add(n))
                .forEach(System.out::println);

//`2. First element
        myList.stream().findFirst().ifPresent(System.out::println);

        long count = myList.stream().count();
        System.out.println(count);
        System.out.println(myList.stream().max(Integer::compare));

        System.out.println(myList.stream().max((e1,e2)->e1.compareTo(e2)));


//sorting
        myList.stream().sorted().forEach(System.out::println);
        myList.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        myList.stream().sorted(Collections.reverseOrder()).forEach(System.out::println); //both 75 and 76 are same



//Given a String, find the first non-repeated character in it using Stream functions?
        String input = "Java Hungry Blog Alive is Awesome";

        Character result = input.chars() // Stream of String
                .mapToObj(s -> Character.toLowerCase(Character.valueOf((char) s))) // First convert to Character object and then to lowercase
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) //Store the chars in map with count
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1L)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();
        System.out.println(result);

//To find the numbers starts with 1
        System.out.println(myList.stream().map(s-> s+"").filter(s->s.startsWith("1")).collect(Collectors.toList()));



//To reverse a number
        int number = 5672;
        int reverse = 0;
        int rem;
        while(number!=0){
            rem =number%10;
            reverse = reverse*10 +rem;
            number = number/10;
        }
        System.out.println(reverse);

    }
}


