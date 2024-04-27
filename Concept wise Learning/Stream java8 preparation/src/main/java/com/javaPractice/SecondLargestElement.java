package com.javaPractice;

import java.util.*;

public class SecondLargestElement {

    public static void main(String[] args) {

        Map<Integer, String> map = new HashMap<>();
        int a = 5;
        map.put(a, "hello");
        System.out.println(map);

        List<Integer> list5 = new ArrayList<>();
        list5.add(a);
        System.out.println(list5);

        Integer[] arr = {2,3,5,9,4};

        List list = Arrays.asList(arr);

        System.out.println(list.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get());




        /* All Lambda experssions are invalid
        Addable add3 = a,b -> a+b;
        Addable add1 = (int a, int b) -> (return a + b);
        Addable add2 = (int a, int b) -> (return int a + int b);
         */
    }
}

/*interface Addable {
    int add(int a,int b);
}

 */