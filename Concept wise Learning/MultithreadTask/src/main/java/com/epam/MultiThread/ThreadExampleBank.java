package com.epam.MultiThread;


public class ThreadExampleBank {
    private int balance =0;

    public static void main(String[] args) {
        ThreadExampleBank b=new ThreadExampleBank();
        b.ThreadEx();
    }
    public void ThreadEx(){
        Thread t1=new Thread(new Runnable(){
            @Override
            public void run() {
                for(int i=0;i<10000;i++){
                    add();
                }
            }
        });
        Thread t2=new Thread(new Runnable(){
            @Override
            public void run() {
                for(int i=0;i<10000;i++){
                    subtract();
                }
            }
        });
        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }
        catch(Exception e){
            System.out.println(e);
        }
        System.out.println("Balance="+balance);
    }
    public synchronized void add(){
        balance++;
    }
    public synchronized void subtract(){
        balance--;
    }
}
