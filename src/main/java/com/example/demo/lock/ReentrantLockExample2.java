package com.example.demo.lock;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample2 {
    public static void main(String[] args) {
        ReentrantLock rc = new ReentrantLock();
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Thread t1 = new Thread(new Counter("Thread 1",rc));
        Thread t2 = new Thread(new Counter("Thread 2",rc));
        Thread t3 = new Thread(new Counter("Thread 3",rc));
        Thread t4 = new Thread(new Counter("Thread 4",rc));
        System.out.println("Starting thread");
        pool.submit(t1);
        pool.submit(t2);
        pool.submit(t3);
        pool.submit(t4);
        pool.shutdown();
    }
}

class Counter implements Runnable {
    private String threadName;
    private ReentrantLock lock;

    public Counter(String threadName, ReentrantLock reentrantLock) {
        this.threadName = threadName;
        this.lock = reentrantLock;
    }
    @Override
    public void run() {
        try {
            System.out.println("No of Before lock..>"+lock.getHoldCount());
            lock.tryLock(3, TimeUnit.SECONDS);
            System.out.println("Thread..> " + threadName + " has acquired lock");

            System.out.println("Number of thread acquired lock..>"+lock.getHoldCount());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }finally {
            System.out.println("Thread...> " + threadName + " released lock");
            lock.unlock();
            System.out.println("After release " + threadName + " Number of thread in pool.>"+lock.getHoldCount());
        }
    }
}