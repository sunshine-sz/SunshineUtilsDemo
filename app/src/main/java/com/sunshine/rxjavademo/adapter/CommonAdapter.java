package com.sunshine.rxjavademo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sunshine.rxjavademo.helper.ViewHolder;

import java.util.List;

/**
 * adapter封装的抽象类
 * Created by Sunshine on 2016/9/8.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {


    protected Context context;
    protected List<T> dataList;
    protected LayoutInflater layoutInflater;
    protected int itemLayoutId;

    public CommonAdapter(Context context, List<T> dataList, int itemLayoutId) {
        this.context = context;
        this.dataList = dataList;
        this.itemLayoutId = itemLayoutId;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public T getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = getViewHolder(i, view, viewGroup);
        convert(viewHolder,getItem(i));
        return viewHolder.getConvertView();
    }

    protected abstract void convert(ViewHolder viewHolder, T item);

    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder.get(context, convertView, parent, itemLayoutId,position);
    }
}
