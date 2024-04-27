package com.javaPractice;

import java.util.HashMap;
import java.util.Iterator;

public class FindDuplicateCharacter {

    public static void main(String[] args) {

        String str = "I am am learning java java";

        String s1 = str.replaceAll(" ","");
        char[] arr = s1.toCharArray();
        System.out.println(s1);

        HashMap<Character,Integer> hashMap = new HashMap<>();

        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if(hashMap.get(c)!=null){
                hashMap.put(c,hashMap.get(c)+1);
            }
            else{
                hashMap.put(c,1);
            }
        }
        System.out.println(hashMap);

        Iterator<Character> it = hashMap.keySet().iterator();
        while(it.hasNext()){
            char c = it.next();
            if(hashMap.get(c)>1){
                System.out.println("Duplicate characters are : "+ c);
            }
        }
    }
}
