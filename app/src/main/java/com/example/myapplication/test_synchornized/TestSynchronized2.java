package com.example.myapplication.test_synchornized;

import java.util.concurrent.Semaphore;

/**
 * 单例类的方法并不是线程安全的
 */
public class TestSynchronized2 {
    public TestSynchronized2() {
    }

    private static int testCount = 0;
    private volatile static int testCount2 = 0;
    final Object object = new Object();

    public static synchronized void add() {
        Semaphore semaphore=new Semaphore(0);
        testCount++;
    }

    public void add2() {
        synchronized (object) {
            testCount2++;
        }
    }

    public int getTestCount2() {
        return testCount2;
    }

    public int getTestCount() {
        return testCount;
    }
}

