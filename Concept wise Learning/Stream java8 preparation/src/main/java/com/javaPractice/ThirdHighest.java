package com.javaPractice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ThirdHighest {

    public static void main(String[] args){

        Integer arr[] = {1,4,21,82,92,24};

        //Converting from array to list
        List<Integer> list = Arrays.asList(arr);

        //list.stream().sorted().forEach(System.out::println);

        //list.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

        //list.stream().sorted(Comparator.reverseOrder()).skip(2).forEach(System.out::println);

        System.out.println(list.stream().sorted(Comparator.reverseOrder()).skip(2).findFirst().get());

        list.stream().sorted(Comparator.reverseOrder()).skip(2).limit(1).forEach(System.out::println);

        System.out.println(list.stream().sorted(Comparator.reverseOrder()).skip(2).max(Integer::compare).get());


    }



}
