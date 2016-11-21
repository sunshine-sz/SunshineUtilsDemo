package com.sunshine.rxjavademo.ui;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.fitsleep.sunshinelibrary.utils.IntentUtils;
import com.fitsleep.sunshinelibrary.utils.Logger;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.sunshine.rxjavademo.R;
import com.sunshine.rxjavademo.helper.FontHelper;
import com.sunshine.rxjavademo.view.ClockView;

import java.util.List;

import fitsleep.library.AppUtils;
import fitsleep.library.UseBean;

public class MainActivity extends AppCompatActivity {

    private static final java.lang.String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

    }


    public void night(View view) {
        IntentUtils.startActivity(MainActivity.this, NightModeActivity.class, null);
    }

    public void photo(View view) {
        IntentUtils.startActivity(MainActivity.this, PhotoActivity.class, null);
    }

    public void fresco(View view) {
        IntentUtils.startActivity(MainActivity.this, ImageActivity.class, null);
    }

    public void rxJava(View view) {
        IntentUtils.startActivity(this, RxJavaActivity.class, null);
    }

    public void fontImage(View view) {
        IntentUtils.startActivity(this, FontImageActivity.class, null);
    }

    public void permission(View view) {
        IntentUtils.startActivity(this, PermissionRequstActivity.class, null);
    }

    public void music(View view) {
        IntentUtils.startActivity(this, PalyMusicActivity.class, null);
    }

    public void frescoPhoto(View view){
        IntentUtils.startActivity(this, LoadImageActivity.class, null);
    }

    public void camera(View view){
        IntentUtils.startActivity(this,CameraActivity.class);
    }

    public void clock(View view){
        IntentUtils.startActivity(this,ClockActivity.class,null);
    }

    public void today24(View view){
        IntentUtils.startActivity(this,Today24HourActivity.class,null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void dragHelper(View view){
        startActivity(new Intent(this, DragLayoutActivity.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        overridePendingTransition(0,0);
//        IntentUtils.startActivity(this,DragLayoutActivity.class,null);
    }

    public void viewPager(View view){
        IntentUtils.startActivity(this,MorePagerActivity.class);
    }

    public void CalendarView(View view){
        IntentUtils.startActivity(this,CalendarActivity.class);
    }
}
