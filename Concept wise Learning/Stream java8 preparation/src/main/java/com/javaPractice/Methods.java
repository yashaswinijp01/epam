package com.javaPractice;

import java.util.ArrayList;
import java.util.Arrays;

public class Methods {

    public static void main(String[] args){

        char[] arr = {'f','h','d','u'};
        String str = "I am learning java";
        int[] integerArray = { 1, 2 ,5, 9};

        //to convert a char array into String
        String s1 = String.valueOf(arr);

        // to convert a int array into String is not possible --> String s2 = String.valueOf(integerArray);

        //to convert a String into char array
        char[] arr1 = str.toCharArray();

        //to get a char at ith position from a string
        char ch = str.charAt(2);


        System.out.println(s1+"---"+ch);
        System.out.println(arr1);

        String str1 = "Saket Saurav        is a QualityAna    list";

        //1. Using replaceAll() Method

        String str2 = str1.replaceAll("\\s", "");

        System.out.println(str2);



        String[] cities1={"Boston", "Dallas", "New York", "Chicago"};
        //Converting Array to ArrayList using Arrays.asList()
        ArrayList<String> list= new ArrayList<>(Arrays.asList(cities1));

        ArrayList<String> cities = new ArrayList<>();
        cities.add("Boston");
        cities.add("Dallas");
        cities.add("San jose");
        cities.add("Chicago");

        // ArrayList to String array conversion using toArray()
        String citinames[]=cities.toArray(new String[cities.size()]);

        // Printing elements using for-each loop
        for(String s : citinames) {
            System.out.println(str);
        }

        }
}
