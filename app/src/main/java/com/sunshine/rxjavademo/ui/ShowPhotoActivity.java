package com.sunshine.rxjavademo.ui;

import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sunshine.rxjavademo.R;
import com.sunshine.rxjavademo.helper.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class ShowPhotoActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photo);
        initView();
        initData();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
    }


    private void initData() {
        ArrayList<String> paths = (ArrayList<String>) getIntent().getSerializableExtra("paths");
        int position = getIntent().getIntExtra("position", 0);
        final List<ImageView> simpleDraweeViewList = new ArrayList<>();
        ImageView imageView;
        for (String path : paths) {
            if (!TextUtils.isEmpty(path)) {
                imageView = new ImageView(this);
                imageView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                ImageLoader.getInstance(3, ImageLoader.Type.LIFO).loadImage(path, imageView);
                simpleDraweeViewList.add(imageView);
            }
        }
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return simpleDraweeViewList.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView iv = simpleDraweeViewList.get(position);
                //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
                ViewParent vp = iv.getParent();
                if (vp != null) {
                    ViewGroup parent = (ViewGroup) vp;
                    parent.removeView(iv);
                }
                //把页面添加到容器中
                container.addView(iv);
                return iv;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
            }
        });

        viewPager.setCurrentItem(position-1);
    }
}
