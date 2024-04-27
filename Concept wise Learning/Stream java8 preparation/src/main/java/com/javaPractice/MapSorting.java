package com.javaPractice;

import java.util.*;

public class MapSorting {

    public static void main(String[] args){
        // Creating a HashMap of int keys and String values
        HashMap<Integer, String> hashmap = new HashMap<Integer, String>();

        // Adding Key and Value pairs to HashMap
        hashmap.put(22,"A");
        hashmap.put(55,"B");
        hashmap.put(33,"Z");
        hashmap.put(44,"M");
        hashmap.put(99,"I");
        hashmap.put(88,"X");

        System.out.println("Before Sorting:");

        Iterator<Integer> it = hashmap.keySet().iterator();
        while(it.hasNext()){
            int num = it.next();
            System.out.println("Key"+num+"Value"+hashmap.get(num));
        }

        //To sort the keys
        TreeMap<Integer,String> treeMap = new TreeMap<>(hashmap);
        System.out.println(treeMap);

        System.out.println(hashmap);
        System.out.println(hashmap.keySet());
        System.out.println(hashmap.entrySet());

        List list = new LinkedList(hashmap.entrySet());
        System.out.println(list);
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry)(o2)).getValue());
            }
        });
        System.out.println(list);


    }
}
