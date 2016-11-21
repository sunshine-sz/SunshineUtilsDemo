package com.sunshine.rxjavademo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sunshine.rxjavademo.R;
import com.sunshine.rxjavademo.fragment.MzFragment;
import com.sunshine.rxjavademo.fragment.NewsFragment;

public class RxJavaActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = RxJavaActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        findViewById(R.id.mz).setOnClickListener(this);
        findViewById(R.id.zh).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.zh:
                NewsFragment newsFragment = new NewsFragment();
                replaceFragment(newsFragment);
                break;
            case R.id.mz:
                MzFragment mzFragment = new MzFragment();
                replaceFragment(mzFragment);
                break;
        }
    }

    /**
     * 替换页面
     * @param fragment
     */
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment).commit();
    }
}
