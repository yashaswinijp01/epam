package com.javaPractice;

import java.util.Arrays;
import java.util.HashMap;

public class ToFindGivenStringsAreAnnagramsOrNot {
    public static void main(String[] args) {

        String str1 = "silent";
        String str2 = "listen";

        HashMap<Character,Integer> hashMap = new HashMap<>();

        for(int i=0; i< str1.length();i++){
            char key1 = str1.charAt(i);
            if(hashMap.get(key1)!=null){
                hashMap.put(key1,hashMap.get(key1)+1);
            }
            else{
                hashMap.put(key1,1);
            }
        }

        for(int j=0; j< str2.length();j++){
            char key2 = str2.charAt(j);
            Integer value = hashMap.get(key2);
            if(value==null){
                System.out.println("false");
            }
            if(value==1){
                hashMap.remove(key2);
            }
            else{
                hashMap.put(key2,value-1);
            }
        }

        System.out.println(hashMap.isEmpty());

        checkAnnagram(str1,str2);

    }

    static void checkAnnagram(String s1, String s2){

        if(s1.length()!=s2.length()){
            System.out.println("Given Strings are not Annagrams");
        }

        if(s1==null || s2==null){
            System.out.println("Given Strings are not Annagrams");
        }


    }
}
