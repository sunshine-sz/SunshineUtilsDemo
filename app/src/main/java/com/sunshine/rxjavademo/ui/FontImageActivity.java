package com.sunshine.rxjavademo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sunshine.rxjavademo.R;
import com.sunshine.rxjavademo.helper.FontHelper;

public class FontImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font_image);
        //这个方法一定要放在setContentView之后
        FontHelper.injectFont(findViewById(android.R.id.content));
    }
}
