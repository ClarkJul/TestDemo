package com.example.lib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyClass {
    public static void main(String[] agrs) {
/*        testBeanEquals(new Testbean(),new Testbean());
        testBeanEquals(5,5);
        testBeanEquals(new String("5"),new String("5"));*/

//        testThreadOrder();

        testWait();

        //获取线程安全的list
        List list = Collections.synchronizedList(new ArrayList());


    }

    private static <T> void testBeanEquals(T a, T b) {
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(a.hashCode() + "," + b.hashCode());
        System.out.println("==============================================");

    }

    static class Testbean {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static void testThreadOrder() {
        T1 t1 = new T1();
        T2 t2 = new T2(t1);
        T3 t3 = new T3(t2);

        t3.start();
        t2.start();
        t1.start();

    }
    static Lock lock = new ReentrantLock();
    static void testWait() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MainThread mainThread = new MainThread();
                lock.lock();
                try {
                    mainThread.start();
                    Thread.sleep(2000);
                    mainThread.wait();
                    for (int i = 0; i < 3; i++) {
                        try {
                            System.out.println("WaitThread:" + i);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }).start();
    }

    static class MainThread extends Thread {
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("MainThread:" + i);
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            notifyWait();
        }

        public void notifyWait(){
            this.notify();
        }
    }


    static class T1 extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.println("这是T1");
        }
    }

    static class T2 extends Thread {
        private Thread thread1;

        public T2(Thread thread1) {
            this.thread1 = thread1;
        }

        @Override
        public void run() {
            super.run();
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("这是T2");
        }
    }

    static class T3 extends Thread {

        private Thread thread2;

        public T3(Thread thread2) {
            this.thread2 = thread2;
        }

        @Override
        public void run() {
            super.run();
            try {
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("这是T3");
        }
    }
}