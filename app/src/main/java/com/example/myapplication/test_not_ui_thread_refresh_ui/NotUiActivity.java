package com.example.myapplication.test_not_ui_thread_refresh_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class NotUiActivity extends AppCompatActivity {
    AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_ui);

        builder = new AlertDialog.Builder(this);
        builder.setTitle("列表框")
                .setItems(new String[]{"列表项1", "列表项2", "列表项3"}, null)
                .setNegativeButton("确定", null);
        alertDialog = builder.create();
    }

    public void testNotUiThreadToast(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(NotUiActivity.this, "testNotUiThreadToast", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();

    }

    public void testNotUiThreadShowDialog(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();

                alertDialog.show();
                Looper.loop();
            }
        }).start();
    }

    private void startCallThread(){
        FutureTask<Integer> futureTask=new FutureTask<Integer>(new MyCallable());
        new Thread(futureTask).start();
        try {
            Integer integer = futureTask.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

   static class MyCallable implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            //逻辑代码
            return null;
        }
    }
}