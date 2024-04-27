package com.javaPractice;

import java.util.Arrays;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Stream111 {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("Java", "python","csharp","JavaScript");
        String list1 = list.stream().map(x->x.toUpperCase()).collect(Collectors.joining(","));
        System.out.println(list1);

        //square of distinct elements
        List<Integer> integerList = Arrays.asList(1,3,5,6,2,3,8,5);
        List<Integer> distinctList = integerList.stream().distinct().map(x->x*x).collect(Collectors.toList());
        System.out.println(distinctList);

        List<Integer> integerList1 = Arrays.asList(9,23,5,71,20,8,4,5,10,18,14,32,41,9);

        System.out.println(integerList1.stream().count());
        System.out.println(integerList1.stream().max(Integer::compare));
        System.out.println(integerList1.stream().mapToInt(x->x).max());
        System.out.println(integerList1.stream().min(Integer::compare));
        System.out.println(integerList1.stream().mapToInt(x->x).min());
        System.out.println(integerList1.stream().mapToInt(x->x).average());
        System.out.println(integerList1.stream().limit(5).collect(Collectors.toList()));
        System.out.println(integerList1.stream().map(x->x+" ").filter(x->x.startsWith("1")).collect(Collectors.toList()));

        IntSummaryStatistics intSummaryStatistics = integerList1.stream().mapToInt(x->x).summaryStatistics();
        System.out.println("Max no in list is "+ intSummaryStatistics.getMax());
        System.out.println("Min no in list is "+ intSummaryStatistics.getMin());
        System.out.println("Count in list is "+ intSummaryStatistics.getCount());
        System.out.println("Avarege of list is "+ intSummaryStatistics.getAverage());
        System.out.println("Sum of no's in list is "+ intSummaryStatistics.getSum());


        //To get the frequency of each word in the given list
        List<String> input = Arrays.asList("AA","BB","AA","DD","CC","DD");
        System.out.println(input.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting())));

        //to get them words whose frequency is >=2
        System.out.println(input.stream().filter(word->Collections.frequency(input,word)>1).distinct().collect(Collectors.toList()));
        System.out.println(input.stream().filter(word->Collections.frequency(input,word)>1).collect(Collectors.groupingBy(Function.identity(),Collectors.counting())));
    }
}