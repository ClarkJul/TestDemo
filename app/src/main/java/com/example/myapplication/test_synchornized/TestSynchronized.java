package com.example.myapplication.test_synchornized;

/**
 * 单例类的方法并不是线程安全的
 */
public class TestSynchronized {
    private static volatile TestSynchronized instance=null;

    public TestSynchronized() {

    }
    public static TestSynchronized getInstance(){
        if (instance==null){
            synchronized (TestSynchronized.class){
                if (instance==null){
                    instance=new TestSynchronized();
                }
            }
        }
        return instance;
    }

    private int testCount=0;
    public synchronized void add(){
        testCount++;
    }

    public int getTestCount() {
        return testCount;
    }
}

