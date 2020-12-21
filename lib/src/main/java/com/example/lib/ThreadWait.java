package com.example.lib;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadWait {

    final Lock lock =new ReentrantLock();
    final Object object =new Object();

    public static void main(String[] args){
        ThreadWait threadWait=new ThreadWait();
//        ThreadA threadA=threadWait.new ThreadA();
//        threadA.start();
//        ThreadB threadB=threadWait.new ThreadB();
//        threadB.start();

        threadWait.new ThreadRun().run();
    }

    class ThreadRun extends Thread{
        @Override
        public void run() {
            super.run();
            System.out.println("test");
        }
    }

    class ThreadA extends Thread{
        @Override
        public void run() {
            super.run();
            lock.lock();
            for (int i = 0; i < 100; i++) {
                object.notify();
                System.out.println("ThreadA:"+i);
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ThreadB extends Thread{
        @Override
        public void run() {
            super.run();
            lock.lock();
            for (int i = 0; i < 100; i++) {
                object.notify();
                System.out.println("ThreadB:"+i);
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
