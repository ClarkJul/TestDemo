package com.example.lib;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {

    static Lock lock=new ReentrantLock();
    static int count=0;

    public static void main(String[] args){
        new T1().start();
        new T2().start();
    }

   static class T1 extends Thread{
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 100; i++) {
                add("t1");
            }

        }
    }

    static class T2 extends Thread{
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 100; i++) {
                add("t2");
            }
        }
    }

    private static void add(String flag){
        lock.lock();
        count++;
        System.out.println("count="+count+","+flag);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}
