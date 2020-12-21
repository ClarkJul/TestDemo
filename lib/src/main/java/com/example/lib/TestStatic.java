package com.example.lib;

public class TestStatic {
    private static int count = 0;

    public static void main(String[] a) {
        TestStatic testStatic = new TestStatic();
        testStatic.test(testStatic);

/*        TestStatic testStaticA = new TestStatic();
        TestStatic testStaticB = new TestStatic();
        test(testStaticA,testStaticB);*/
    }

    private void test(final TestStatic testStatic) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                testStatic.add(0,testStatic);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                testStatic.add(0,testStatic);
            }
        }).start();

    }

    private static void test(final TestStatic testStaticA, final TestStatic testStaticB) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                testStaticA.add(0);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                testStaticB.add(0);
            }
        }).start();

    }

    private void add(TestStatic testStatic) {
        for (int i = 0; i < 1000; i++) {
            synchronized (testStatic) {
                count++;
                System.out.println(Thread.currentThread() + "," + count);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void add(int fa, TestStatic testStatic) {
        synchronized (testStatic) {
            for (int i = 0; i < 1000; i++) {
                count++;
                System.out.println(Thread.currentThread() + "," + count);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void add(int a) {
        synchronized (TestStatic.class) {
            for (int i = 0; i < 1000; i++) {
                count++;
                System.out.println(Thread.currentThread() + "," + count);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private synchronized void add() {
        for (int i = 0; i < 1000; i++) {
            count++;
            System.out.println(Thread.currentThread() + "," + count);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
