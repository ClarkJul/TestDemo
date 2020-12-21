package com.example.myapplication.test_synchornized;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.util.TimeUtils;
import android.view.View;

import com.example.myapplication.R;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TestSyncActivity extends AppCompatActivity {

    private static final String TAG = "Test_Sync";

    private ReentrantLock lock = new ReentrantLock();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sync);
//        testSynchronized();
        testSynchronized2();

    }

    @Override
    protected void onStart() {
        Log.e(TAG, "onStart: " );
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.e(TAG, "onResume: " );
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e(TAG, "onPause: " );
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e(TAG, "onStop: " );
        super.onStop();
    }

    private void testSynchronized() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    TestSynchronized.getInstance().add();
                    Log.e(TAG, "run: thread 1==>" + TestSynchronized.getInstance().getTestCount());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    TestSynchronized.getInstance().add();
                    Log.e(TAG, "run: thread 2==>" + TestSynchronized.getInstance().getTestCount());
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void testSynchronized2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
/*                    TestSynchronized2.add();
                    TestSynchronized2 testSynchronized2 = new TestSynchronized2();
                    Log.e(TAG, "run: thread 1==>"+testSynchronized2.getTestCount());*/
                    TestSynchronized2 testSynchronized2 = new TestSynchronized2();
                    testSynchronized2.add2();
                    Log.e(TAG, "run: thread 1==>" + testSynchronized2.getTestCount2());
                    try {
                        Thread.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
/*                    TestSynchronized2.add();
                    TestSynchronized2 testSynchronized2 = new TestSynchronized2();
                    Log.e(TAG, "run: thread 2==>"+testSynchronized2.getTestCount());*/
                    TestSynchronized2 testSynchronized2 = new TestSynchronized2();
                    testSynchronized2.add2();
                    Log.e(TAG, "run: thread 2==>" + testSynchronized2.getTestCount2());
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void onReentrantLockClick(View view) {
        for (int i=0;i<3;i++){
            final int temp=i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    testReentrantLock2(temp);
                }
            }).start();
        }
    }

    private void testReentrantLock() {
        lock.lock();
        Log.e(TAG, "run: currentThread =" +Thread.currentThread());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.e(TAG, "run: currentThread =" +Thread.currentThread());
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e(TAG, "run: unlock");
                            lock.unlock();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private synchronized void testReentrantLock2(final int i) {
        Log.e(TAG, "run: "+i+",currentThread =" +Thread.currentThread());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.e(TAG, "run: "+i+",currentThread =" +Thread.currentThread());
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e(TAG, "run: unlock"+i);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}