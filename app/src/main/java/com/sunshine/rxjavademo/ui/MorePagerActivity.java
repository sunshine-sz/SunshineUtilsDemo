package com.sunshine.rxjavademo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fitsleep.sunshinelibrary.utils.ScreenUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.sunshine.rxjavademo.R;
import com.sunshine.rxjavademo.inter.OnItemClickListener;
import com.sunshine.rxjavademo.view.LoopRotarySwitchView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：LiZhao
 * 时间：2016/11/21 15:13
 * 邮箱：44493547@qq.com
 * 备注：
 */
public class MorePagerActivity extends AppCompatActivity{

    private String[] titles = {"闹钟","实时监测","阿尔法"};
    private List<View> views = new ArrayList<>();
    private LoopRotarySwitchView loopRotarySwitchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        initWidget();
        initData();
        initLoopRotarySwitchView();
    }

    private void initWidget() {
        loopRotarySwitchView = (LoopRotarySwitchView) findViewById(R.id.loop_view);
    }

    private void initData(){
        View view = LayoutInflater.from(this).inflate(R.layout.item_head_layout, null);
        View view1 = LayoutInflater.from(this).inflate(R.layout.item_head_layout, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.item_head_layout, null);
        views.add(view);
        views.add(view1);
        views.add(view2);
        for (int i=0;i<titles.length;i++){
            ((TextView) views.get(i).findViewById(R.id.tv_head)).setText(titles[i]);
            loopRotarySwitchView.addView(views.get(i));
        }
    }

    private void initLoopRotarySwitchView(){
        loopRotarySwitchView
                .setR(ScreenUtils.getScreenWidth(this)/1.8f)//设置R的大小
                .setAutoRotation(false)//是否自动切换
                .setAutoRotationTime(20000);//自动切换的时间  单位毫秒
        loopRotarySwitchView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int item, View view) {
                ToastUtils.showMessage(titles[item]);
            }
        });
    }
}
