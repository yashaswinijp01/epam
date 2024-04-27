package com.javaPractice;

import java.util.Comparator;

public class NameComparator implements Comparator<StudentClass> {

    @Override
    public int compare(StudentClass o1, StudentClass o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
