package com.javaPractice;

public class PalidromeRecursive {

    static int reverse(int n, int temp){

        if(n==0){
            return temp;
        }
        temp = (temp*10) + (n%10);

        return reverse(n/10,temp);
    }

    public static void main(String[] args){
        int n=121;
        int rev = reverse(n,0);
        if(n==rev){
            System.out.println("Palindrome");
        }
        else {
            System.out.println("Not Palindrome");
        }
    }
}
