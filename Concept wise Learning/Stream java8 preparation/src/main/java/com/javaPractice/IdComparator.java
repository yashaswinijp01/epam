package com.javaPractice;

import java.util.Comparator;

public class IdComparator implements Comparator<StudentClass> {

    @Override
    public int compare(StudentClass s1, StudentClass s2) {
        if(s1.getId()==s2.getId()){
            return s1.getName().compareTo(s2.getName());
        }
        else if(s1.getId()> s2.getId()){
            return 1;
        }
        else
        return -1;
    }
}
