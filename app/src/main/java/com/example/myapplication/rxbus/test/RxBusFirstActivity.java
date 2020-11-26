package com.example.myapplication.rxbus.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.TestBean;
import com.example.myapplication.rxbus.RxBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RxBusFirstActivity extends AppCompatActivity {

    private Disposable subscribe;
    private BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("RxBusFirst", "onReceive: receiver");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus_first);
//        RxBus.getDefault().register(this);
        subscribe = RxBus.getDefault().toObservable(TestBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TestBean>() {
                    @Override
                    public void accept(TestBean testBean) throws Exception {
                        Log.e("RxBusFirst", "accept: testBean.getName="+testBean.getName() );
                        TextView tv=findViewById(R.id.tv_content);
                        tv.setText(testBean.getName());
                    }
                });
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,new IntentFilter("com.example.myapplication.rxbus.test"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!subscribe.isDisposed()){
            subscribe.dispose();
        }
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
//        RxBus.getDefault().unregister(this);
    }

    public void enterNext(View view) {
        startActivity(new Intent(RxBusFirstActivity.this,RxBusSecondActivity.class));
    }
}
