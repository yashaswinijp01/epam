package com.javaPractice;

import java.util.*;

public class FindDuplicateElementInArray {
    public static void main(String[] args){

        List list = new ArrayList();

        String arr[] = {"java", "c#", "c++","python", "java", "python"};

//Approach 1
        for(int i=0; i< arr.length; i++){
            for(int j=i+1; j< arr.length; j++){
                if(arr[i]==arr[j]){
                    System.out.println("Duplicate element is :" + arr[i]);
                }
            }
        }

//Approach 2
        HashSet<String> hashSet = new HashSet<>();
        //for(String s :arr) or
        for(int i=0; i< arr.length; i++){
            if(hashSet.add(arr[i])==false){
                System.out.println("Duplicate element is :" + arr[i]);
            }
        }

//Approach 3
        HashMap<String,Integer> hashmap = new HashMap<>();
        for(String s :arr){
            if(hashmap.get(s)==null){
                hashmap.put(s,1);
            }
            else {
                hashmap.put(s, hashmap.get(s) + 1);
                System.out.println("Duplicate element is :" + s);
                list.add(s);
            }
        }
        System.out.println(hashmap);
        System.out.println(list);

    }
}
