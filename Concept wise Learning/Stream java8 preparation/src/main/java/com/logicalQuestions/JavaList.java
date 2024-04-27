package com.logicalQuestions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JavaList {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution.
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> list = new ArrayList<>(n);
        for(int i=0; i<n; i++){
            list.add(scanner.nextInt());
        }

        int q= scanner.nextInt();
        for(int i=0; i<q;i++){
            String query = scanner.nextLine();
            if(query.equals("Insert")){
                int x= scanner.nextInt();
                int y = scanner.nextInt();
                list.add(x, y);
            }
            if(query.equals("Delete")){
                int x = scanner.nextInt();
                list.remove(x);
                System.out.println(list);
            }
        }
        for(int i: list){
            System.out.print(i+ " ");
        }

         */

        int n= 5;
        List<Integer> list = new ArrayList<>(n);
        list.add(12);
        list.add(0);
        list.add(1);
        list.add(78);
        list.add(12);

        int q= 2;
        String query = "Insert";
        String query1 = "Delete";



    }
}
