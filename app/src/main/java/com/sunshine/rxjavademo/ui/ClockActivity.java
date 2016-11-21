package com.sunshine.rxjavademo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sunshine.rxjavademo.R;

public class ClockActivity extends AppCompatActivity {

    private static final String TAG = ClockActivity.class.getSimpleName();
    private long minData = 1447568280000l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

    }
}
