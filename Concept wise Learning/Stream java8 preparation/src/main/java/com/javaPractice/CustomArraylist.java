package com.javaPractice;

import com.streams.Employee;

import java.util.ArrayList;
import java.util.List;

//To Arraylist with only distinct elements

public class CustomArraylist extends ArrayList {

    @Override
    public boolean add(Object o){
        if(this.contains(o)){
            return false;
        }
        return super.add(o);
    }


    public static void main(String[] args){

        //List<Integer> list1 = new CustomArraylist();
        CustomArraylist list1 = new CustomArraylist();
        list1.add(1);
        list1.add(4);
        list1.add(4);
        list1.add(1);
        System.out.println(list1);

    }
}
