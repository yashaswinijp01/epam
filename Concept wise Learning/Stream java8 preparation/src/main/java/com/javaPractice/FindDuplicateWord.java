package com.javaPractice;

import java.util.*;

public class FindDuplicateWord {

    public static void main(String[] args){

        String str = "I am am learning java java";

        HashMap<String, Integer> hm = new HashMap<>();
        List<String> list = new ArrayList();

        String[] s = str.split(" ");

        //1.
        /*for(String temps : s ){
            if(hm.containsKey(temps)){
                hm.put(temps,hm.get(temps)+1);
                list.add(temps);
            }
            else{
                hm.put(temps,1);
            }
        }
        System.out.println(list);

         */


        //2.
        for(String temps : s){
            if(hm.get(temps)!=null){
                hm.put(temps,hm.get(temps)+1);
            }
            else{
                hm.put(temps,1);
            }
        }

        Iterator<String> it = hm.keySet().iterator();
        while(it.hasNext()){
            String st = it.next();
            if(hm.get(st)>1){
                System.out.println("Duplicate word is :" + st);
            }
        }
    }

    //3.
/*
    String[] a = str.split(" ");

    HashSet<String> hs = new HashSet<>();

        for(int i=0; i<a.length;i++) {
        if(hs.add(a[i])==false){
            System.out.println("Dupl is "+ a[i]);
        }
    }

 */

}
