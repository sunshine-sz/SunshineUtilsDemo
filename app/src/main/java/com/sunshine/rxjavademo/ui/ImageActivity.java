package com.sunshine.rxjavademo.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fitsleep.sunshinelibrary.utils.ConvertUtils;
import com.fitsleep.sunshinelibrary.utils.Logger;
import com.google.gson.Gson;
import com.sunshine.rxjavademo.adapter.MyPagerAdapter;
import com.sunshine.rxjavademo.R;
import com.sunshine.rxjavademo.bean.ImageBean;

import java.util.ArrayList;
import java.util.List;

public class ImageActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {


    private static final String TAG = ImageActivity.class.getSimpleName();
    private static final int HANDLER_MSG = 0;
    private ViewPager viewPager;
    private LinearLayout llGuidePointGroup;
    private View vGuidePointJd;
    private String imags = "{\"code\":0,\"data\":{\"list\":[{\"arid\":504,\"sindex\":1,\"article\":{\"aid\":248,\"aotime\":\"2016-05-19\",\"aouname\":\"Limt\",\"appath\":\"http:\\/\\/news.onethird.com.cn\\/FILE\\/PIC\\/248\\/63d06936-7653-49c5-b758-c4f98e317e53.png\",\"apcount\":659,\"ascount\":4,\"atitle\":\"Fitsleep 新手使用教程\",\"aucount\":289,\"alname\":\"睡眠\"},\"rid\":\"1\"},{\"arid\":505,\"sindex\":2,\"article\":{\"aid\":319,\"aotime\":\"2016-09-05\",\"aouname\":\"文\",\"appath\":\"http:\\/\\/news.onethird.com.cn\\/FILE\\/PIC\\/319\\/7148b85b-c101-4056-b9a1-4384aa7cfa66.jpg\",\"apcount\":19,\"ascount\":0,\"atitle\":\"人体排毒需要的营养素有哪些？\",\"aucount\":5,\"alname\":\"养生\"},\"rid\":\"1\"},{\"arid\":488,\"sindex\":3,\"article\":{\"aid\":315,\"aotime\":\"2016-08-30\",\"aouname\":\"文\",\"appath\":\"http:\\/\\/news.onethird.com.cn\\/FILE\\/PIC\\/315\\/a5b0d791-a0ed-4f81-af9e-33d13472ff98.jpg\",\"apcount\":34,\"ascount\":0,\"atitle\":\"我也想先定一个小目标，比方说先睡好1000天\",\"aucount\":14,\"alname\":\"睡眠\"},\"rid\":\"1\"},{\"arid\":489,\"sindex\":4,\"article\":{\"aid\":303,\"aotime\":\"2016-08-29\",\"aouname\":\"文\",\"appath\":\"http:\\/\\/news.onethird.com.cn\\/FILE\\/PIC\\/303\\/e8b7e5c2-b820-408c-a093-c8e988779fbb.jpg\",\"apcount\":43,\"ascount\":0,\"atitle\":\"失眠不是只有你一个，经常睡不好怎么办？\",\"aucount\":23,\"alname\":\"睡眠\"},\"rid\":\"1\"},{\"arid\":491,\"sindex\":5,\"article\":{\"aid\":310,\"aotime\":\"2016-08-26\",\"aouname\":\"文\",\"appath\":\"http:\\/\\/news.onethird.com.cn\\/FILE\\/PIC\\/310\\/b8cd4c3f-3490-44db-b01d-729ebcadfe99.jpg\",\"apcount\":42,\"ascount\":0,\"atitle\":\"饮茶虽好，可不要贪多贪浓哦！\",\"aucount\":19,\"alname\":\"养生\"},\"rid\":\"1\"}]},\"legal\":0}";
    private SimpleDraweeView simpleDraweeView;
    private List<SimpleDraweeView> imageViews = new ArrayList<>();
    private View view;
    private boolean isStoped = false;
    /**
     * 上一次高亮显示的位置
     */
    private int lastPosition = 0;
    private void assignViews() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        llGuidePointGroup = (LinearLayout) findViewById(R.id.ll_guide_point_group);
        vGuidePointJd = findViewById(R.id.v_guide_point_jd);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        assignViews();
        getImages();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        isStoped = true;
        handler.removeCallbacksAndMessages(null);

    }

    private void getImages() {
        Gson gson = new Gson();
        ImageBean imageBean = gson.fromJson(imags, ImageBean.class);
        ImageBean.DataBean data = imageBean.getData();
        List<ImageBean.DataBean.ListBean> dataList = data.getList();
        for (int i = 0; i < dataList.size(); i++) {
            simpleDraweeView = new SimpleDraweeView(this);
            simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            final ImageBean.DataBean.ListBean listBean = dataList.get(i);
            simpleDraweeView.setImageURI(listBean.getArticle().getAppath());
            imageViews.add(simpleDraweeView);
            view = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ConvertUtils.dp2px(this, 6),
                    ConvertUtils.dp2px(this, 6));
            if (i != 0) {
                params.leftMargin = 18;
            }
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.point_bg);
            if (i == 0) {
                view.setEnabled(true);
            } else {
                view.setEnabled(false);
            }
            llGuidePointGroup.addView(view);
        }

        MyPagerAdapter adapter = new MyPagerAdapter(this,imageViews);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);

        // 设置选中中间页面- 我一定要取中间值，并且一定要刚好是imageViews.size()整数倍
        int item = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageViews.size();
        // 需要5的倍数的值
        // 22 - 11 - 10
        viewPager.setCurrentItem(item);
        llGuidePointGroup.getChildAt(item % imageViews.size()).setEnabled(true);
        handler.sendEmptyMessage(HANDLER_MSG);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        int index = position % imageViews.size();// 0~4
        // 设置当前页面的下标点为高亮
        llGuidePointGroup.getChildAt(index).setEnabled(true);
        // 设置上次高亮的变为非高亮
        llGuidePointGroup.getChildAt(lastPosition).setEnabled(false);
        lastPosition = index;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Logger.e(TAG,"state"+state);
    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    if (!isStoped) {
                        handler.sendEmptyMessageDelayed(HANDLER_MSG, 5000);
                    }
                    break;
            }

        }
    };
}
