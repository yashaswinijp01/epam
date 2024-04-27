package com.epam.ProducerConsumerProblemSolution;

import java.util.Queue;
import java.util.Random;

public class Producer implements Runnable{
    private Queue<Integer> sharedQueue;
    private final int MAX_SIZE=6;

    public Producer(Queue<Integer> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while(true){
            synchronized (sharedQueue){
                while (sharedQueue.size()==6){
                    try{
                        System.out.println("Producer is waiting to consume objects by consumer");
                        sharedQueue.wait();
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                Random random = new Random();
                int data=random.nextInt(MAX_SIZE);
                sharedQueue.add(data);
                System.out.println("Produced Resource:"+data);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sharedQueue.notify();
            }
        }
    }
}
