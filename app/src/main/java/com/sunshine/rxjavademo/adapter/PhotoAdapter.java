package com.sunshine.rxjavademo.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.fitsleep.sunshinelibrary.utils.AlertAnimateUtil;
import com.fitsleep.sunshinelibrary.utils.IntentUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.sunshine.rxjavademo.R;
import com.sunshine.rxjavademo.helper.ViewHolder;
import com.sunshine.rxjavademo.ui.ShowPhotoActivity;

import java.util.ArrayList;

/**
 * 相片展示的填充器
 * Created by Sunshine on 2016/9/9.
 */
public class PhotoAdapter extends CommonAdapter<String> {

    private String dirPath;
    private int maxValue;
    private ArrayList<String> selectPathList = new ArrayList<>();
    private ArrayList<String> dataList;
    public PhotoAdapter(Context context, ArrayList<String> dataList, int itemLayoutId, String dirPath) {
        super(context, dataList, itemLayoutId);
        this.dirPath = dirPath;
        this.dataList = dataList;
    }

    public void selectMax(int max) {
        this.maxValue = max;
    }

    @Override
    protected void convert(final ViewHolder viewHolder, final String item) {
        //初始化图片
        viewHolder.setImageView(R.id.item_image_view, R.mipmap.zw);
        //查找控件
        final ImageView checkBox = viewHolder.getView(R.id.child_checkbox);
        final ImageView imageView = viewHolder.getView(R.id.item_image_view);
        //加载图片
        if (TextUtils.isEmpty(item)) {
            viewHolder.setImageView(R.id.item_image_view, R.mipmap.asv);
            checkBox.setVisibility(View.GONE);
        } else {
            checkBox.setVisibility(View.VISIBLE);
            if (TextUtils.isEmpty(dirPath)) {
                viewHolder.setImageView(R.id.item_image_view, item);
            } else {
                viewHolder.setImageView(R.id.item_image_view, dirPath + "/" + item);
            }
        }

        //点击事件
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertAnimateUtil.addAnimation(checkBox);
                if (selectPathList.contains(TextUtils.isEmpty(dirPath) ? item : (dirPath + "/" + item))) {
                    selectPathList.remove(TextUtils.isEmpty(dirPath) ? item : (dirPath + "/" + item));
                    checkBox.setImageResource(R.drawable.select_normal);
                    imageView.setColorFilter(null);
                }else {
                    if (selectPathList.size() >= maxValue) {
                        ToastUtils.showMessage("选择相片最大数不能超过" + maxValue + "张~~~");
                        return;
                    }
                    selectPathList.add(TextUtils.isEmpty(dirPath) ? item : (dirPath + "/" + item));
                    checkBox.setImageResource(R.drawable.select_pressed);
                    imageView.setColorFilter(Color.parseColor("#77000000"));
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(item)&&viewHolder.getPosition()==0){
                    ToastUtils.showMessage("打开相机");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("paths",dataList);
                bundle.putInt("position",viewHolder.getPosition());
                IntentUtils.startActivity((Activity) context,ShowPhotoActivity.class,bundle);
            }
        });

        if (selectPathList.contains(TextUtils.isEmpty(dirPath) ? item : (dirPath + "/" + item))) {
            checkBox.setImageResource(R.drawable.select_pressed);
            imageView.setColorFilter(Color.parseColor("#77000000"));
        }else {
            checkBox.setImageResource(R.drawable.select_normal);
            imageView.setColorFilter(null);
        }
    }
}
