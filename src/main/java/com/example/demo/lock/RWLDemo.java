package com.example.demo.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class RWLDemo {
    private final Map<String, String> numberMap = new HashMap<String, String>();
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock(true);
    // get method with read lock
    public String get(String key) {
        System.out.println("Waiting to acquire lock in get method...");
        rwl.readLock().lock();
        System.out.println("Acquired read lock in get method");
        try {
            try {
                //Thread.sleep(500);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return numberMap.get(key);
        }
        finally {
            System.out.println("releasing read lock in get method ");
            rwl.readLock().unlock();
        }
    }

    // Put method with write lock
    public String put(String key, String value) {
        System.out.println("Waiting to acquire lock in put method...");
        rwl.writeLock().lock();
        System.out.println("Acquired write lock in put method");
        try {
            try {
                //Thread.sleep(1000);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return numberMap.put(key, value);
        }
        finally {
            System.out.println("Releasing write lock in put method ");
            rwl.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
        RWLDemo rwlDemo = new RWLDemo();
        // To put some initial values in the Map
        rwlDemo.initialValueInMap();
        // Starting Three read threads and two write threads
        Thread wThread1 = new Thread(new WriterThread(rwlDemo, "3", "Three"));
        Thread rThread1 = new Thread(new ReadThread(rwlDemo, "1"));
        Thread rThread2 = new Thread(new ReadThread(rwlDemo, "1"));
        Thread wThread2 = new Thread(new WriterThread(rwlDemo, "4", "Four"));
        Thread rThread3 = new Thread(new ReadThread(rwlDemo, "2"));

        wThread1.start();
        rThread1.start();
        rThread2.start();
        rThread3.start();
        wThread2.start();
    }

    private void initialValueInMap(){
        // Putting some values in the map
        numberMap.put("1", "One");
        numberMap.put("2", "Two");
    }
}

class ReadThread implements Runnable {
    RWLDemo rwDemo;
    String key;
    ReadThread(RWLDemo rwDemo, String key){
        this.rwDemo = rwDemo;
        this.key = key;
    }
    public void run() {
        System.out.println("Value - " + rwDemo.get(key));
    }
}

class WriterThread implements Runnable {
    RWLDemo rwDemo;
    String key;
    String value;
    WriterThread(RWLDemo rwDemo, String key, String value){
        this.rwDemo = rwDemo;
        this.key = key;
        this.value = value;
    }
    public void run() {
        rwDemo.put(key, value);
    }
}