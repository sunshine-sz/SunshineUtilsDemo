package com.sunshine.rxjavademo.view;

import android.support.v7.widget.RecyclerView;

/**
 * 作者: Sunshine
 * 时间: 2016/10/19.
 * 邮箱: 44493547@qq.com
 * 描述:
 */

public class CardLayoutManager extends RecyclerView.LayoutManager {

    public static final int DEFAULT_GROUP_SIZE = 5;
    public CardLayoutManager(boolean center) {
        this(DEFAULT_GROUP_SIZE,true);
    }

    public CardLayoutManager(int groupSize,boolean center) {


    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return null;
    }
}
