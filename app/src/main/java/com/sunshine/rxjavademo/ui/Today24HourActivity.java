package com.sunshine.rxjavademo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sunshine.rxjavademo.R;
import com.sunshine.rxjavademo.view.IndexHorizontalScrollView;
import com.sunshine.rxjavademo.view.Today24HourView;

public class Today24HourActivity extends AppCompatActivity {
    private IndexHorizontalScrollView indexHorizontalScrollView;
    private Today24HourView today24HourView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today24_hour);
        indexHorizontalScrollView = (IndexHorizontalScrollView)findViewById(R.id.indexHorizontalScrollView);
        today24HourView = (Today24HourView)findViewById(R.id.today24HourView);
        indexHorizontalScrollView.setToday24HourView(today24HourView);
    }
}
