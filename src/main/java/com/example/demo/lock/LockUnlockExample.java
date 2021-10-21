package com.example.demo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockUnlockExample {
    Lock lock = new ReentrantLock();
    int   count = 0;
    public static void main(String[] args) throws InterruptedException{

        LockUnlockExample lockUnlockExample = new LockUnlockExample() ;
        lockUnlockExample.method4();

        }

    private int getCount() {

        for (int i = 0; i < 10000; i++) {
            count  ++;
        }

        return count;
    }

    private int method1(){
        int countValue =0;
       try {
          lock.lock();
            countValue = getCount();
       }finally {
        lock.unlock();
       }
        return countValue;
    }

    private int method2(){
        int countValue = 0;

        try {
            lock.lock();
            countValue = getCount();
        }finally {
            lock.unlock();
        }
        return countValue;
    }

    private void method3(){
        System.out.println(count);
    }

    private void method4() throws InterruptedException{
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                method1();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                method2();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("t1,,"+t1.getName());
        System.out.println("t2,,"+t2.getName());

        method3();
    }
}
