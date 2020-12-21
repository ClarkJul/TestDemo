package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.SeekBar;

public class MyProgressBar extends ProgressBar {
    public MyProgressBar(Context context) {
        this(context,null);
    }

    public MyProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
