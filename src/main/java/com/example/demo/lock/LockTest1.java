package com.example.demo.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest1 {
    static final int MAX_T = 3;
    public static void main(String[] args)
    {
        ReentrantLock rel = new ReentrantLock();
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
        Runnable w1 = new ReentrantLockExample("Job1", rel);
        Runnable w2 = new ReentrantLockExample("Job2", rel);
        Runnable w3 = new ReentrantLockExample("Job3", rel);
        Runnable w4 = new ReentrantLockExample("Job4", rel);
        pool.execute(w1);
        pool.execute(w2);
        pool.execute(w3);
        pool.execute(w4);
        pool.shutdown();
    }
}
