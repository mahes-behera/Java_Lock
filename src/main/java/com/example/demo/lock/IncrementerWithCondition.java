package com.example.demo.lock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class IncrementerWithCondition {
    int count = 0;
    Lock lock = new ReentrantLock(); // Non-fair lock.
    Condition condition = lock.newCondition();

    public static void main(String[] args) {
        IncrementerWithCondition incrementer = new IncrementerWithCondition();

        Thread thread1 = new Thread(() -> {
            try {
                incrementer.incrementByFirstThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            incrementer.incrementBySecondThread();
        });

        thread1.start(); thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        incrementer.finished();
    }
    public void incrementByFirstThread() throws InterruptedException {
        lock.lock();
        System.out.println("Waiting for condition in incrementByFirstThread....");
        condition.await();

        try{
            System.out.println("First Thread .."+Thread.currentThread());
            increment();
        } finally {
            lock.unlock();
            System.out.println("First Thread .unlock.");
        }
    }

    public void incrementBySecondThread() {
        lock.lock();
        System.out.println("Waiting for return key ....");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        scanner.close();
        System.out.println("Hurray got return key notifying condition now..");
        condition.signal();

        try{
            System.out.println("Second Thread .."+Thread.currentThread());
            increment();
        } finally {
            lock.unlock();
            System.out.println("Second Thread .unlock.");
        }
    }

    private void increment() {
        for(int i=0; i<1000; i++) {
            count++;
        }
    }

    public void finished() {
        System.out.println("Finished with value: " + count);
    }
}