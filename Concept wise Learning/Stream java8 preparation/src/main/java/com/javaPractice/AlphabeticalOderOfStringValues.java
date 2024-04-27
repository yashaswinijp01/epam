package com.javaPractice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlphabeticalOderOfStringValues {
    public static void main(String[] args){
        String s1= "hello world";
        String s2 = s1.replaceAll(" ","");
        //To convert a string into list
        List<Character> chars = new ArrayList<>();

        for (char ch: s2.toCharArray()) {
            chars.add(ch);
        }
        System.out.println(chars);

        List<Character> list = chars.stream().sorted().collect(Collectors.toList());
        System.out.println(list);
    }
}
