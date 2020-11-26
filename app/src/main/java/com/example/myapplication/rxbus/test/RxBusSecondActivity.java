package com.example.myapplication.rxbus.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.TestBean;
import com.example.myapplication.rxbus.RxBus;

public class RxBusSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus_second);

    }


    public void onClickSendMessage(View view) {
        TestBean bean=new TestBean("张三",20);

        RxBus.getDefault().post(bean);
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("com.example.myapplication.rxbus.test"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
