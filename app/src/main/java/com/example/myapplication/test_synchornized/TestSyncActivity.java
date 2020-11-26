package com.example.myapplication.test_synchornized;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;

public class TestSyncActivity extends AppCompatActivity {

    private static final String TAG="Test_Sync";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sync);
        testSynchronized();
    }

    private void testSynchronized(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<1000;i++){
                    TestSynchronized.getInstance().add();
                    Log.e(TAG, "run: thread 1==>"+TestSynchronized.getInstance().getTestCount());
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
                for (int i=0;i<1000;i++){
                    TestSynchronized.getInstance().add();
                    Log.e(TAG, "run: thread 2==>"+TestSynchronized.getInstance().getTestCount());
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}