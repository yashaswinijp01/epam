package com.javaPractice;

import java.util.*;

public class SetExampleComparableAndComparator {

    public static void main(String[] args){

        Set<StudentClass> s = new HashSet<>();
        StudentClass s1 = new StudentClass(205,"Ravi");
        StudentClass s2 = new StudentClass(207,"Baskar");
        StudentClass s3 = new StudentClass(202,"Ashok");
        StudentClass s4 = new StudentClass(202,"Ashok");
        StudentClass s5 = new StudentClass(205,"Ashok");
        s.add(s1);
        s.add(s2);
        s.add(s3);
        s.add(s4);
        s.add(s5);

        System.out.println(s);
        List list = new ArrayList<>(s);
        System.out.println(list);

        Collections.sort(list,new NameComparator());
        System.out.println(list);
        Collections.sort(list,new IdComparator());
        System.out.println(list);
    }
}
