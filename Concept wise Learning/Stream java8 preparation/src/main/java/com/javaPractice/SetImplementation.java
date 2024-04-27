package com.javaPractice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class SetImplementation {
    public static void main(String[] args){

        //HASHSET
        Set<Integer> hashSet = new HashSet<>();
        hashSet.add(23);
        hashSet.add(4);
        hashSet.add(4);
        hashSet.add(4);                 //no duplicate elements are allowed
        hashSet.add(10);

        System.out.println(hashSet);    //unordered or random order set

        for(int i:hashSet){
            System.out.println("element="+i);
          // hashSet.remove(4);            //Causes ConcurrentModificationException
        }

        Iterator<Integer> it = hashSet.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
            /*it.remove();
            System.out.println(hashSet);//solution for ConcurrentModificationException

             */
        }

        System.out.println("---------------------------");

        //TREESET
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(23);
        treeSet.add(4);
        treeSet.add(4);
        treeSet.add(4);
        treeSet.add(10);

        System.out.println(treeSet);            //Ascending order

        for(int i:treeSet){
            System.out.println("element=" + i);
        }

        System.out.println("Operation on hashset and treeset");

        Set<Integer> hashSet1 = new HashSet<>();
        hashSet1.addAll(Arrays.asList(new Integer[] {99,3,8,19,5,7,0}));
        System.out.println(hashSet1);

        System.out.println(hashSet.isEmpty());

        System.out.println(hashSet.contains(4));

        System.out.println(hashSet.size());

        hashSet.add(93);
        System.out.println(hashSet);

        hashSet.remove(93);
        System.out.println(hashSet);

       /* hashSet.clear();
        System.out.println(hashSet);

        hashSet.add(null);
        System.out.println(hashSet);

        hashSet.add(null);
        System.out.println(hashSet);        //only 1 null value is allowed

        */

        System.out.println(hashSet.stream().findFirst());
        System.out.println(hashSet.stream().count());
        System.out.println(hashSet.stream().sorted());
        System.out.println(hashSet.toString());

    }
}
