package com.sunshine.rxjavademo.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ViewHolder 类
 * Created by Sunshine on 2016/9/8.
 */
public class ViewHolder {

    private SparseArray<View> views = null;
    private int position;
    private View convertView;

    public ViewHolder(Context context, ViewGroup parent, int position, int layoutId) {
        views = new SparseArray<>();
        this.position = position;
        convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        convertView.setTag(this);
    }

    /**
     * 获取ViewHolder对象
     * @param context  上下文
     * @param convertView View
     * @param parent ViewGroup
     * @param itemLayoutId item的布局ID
     * @param position 当前位置
     * @return holder对象
     */
    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int itemLayoutId, int position) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder(context, parent, position, itemLayoutId);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.position = position;
        }
        return holder;
    }

    public View getConvertView(){
        return convertView;
    }

    /**
     * 根据id获取控件
     * @param viewId 控件的ID
     * @param <T> 控件对象
     * @return 控件
     */
    public <T extends View> T getView(int viewId){
        View view = views.get(viewId);
        if (view==null){
            view = convertView.findViewById(viewId);
            views.put(viewId,view);
        }
        return (T) view;
    }

    /**
     * 给TextView设置文字
     * @param viewId 控件Id
     * @param msg 字符串
     * @return holder
     */
    public ViewHolder setText(int viewId,String msg){
        TextView textView = getView(viewId);
        textView.setText(msg);
        return this;
    }

    /**
     * 对ImageView设置图片
     * @param viewId id
     * @param res 资源id
     * @return holder
     */
    public ViewHolder setImageView(int viewId,int res){
        ImageView imageView = getView(viewId);
        imageView.setImageResource(res);
        return this;
    }

    /**
     * 对ImageView设置图片
     * @param viewId id
     * @param bitmap bitmap
     * @return holder
     */
    public ViewHolder setImageView(int viewId,Bitmap bitmap){
        ImageView imageView = getView(viewId);
        imageView.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 对ImageView设置图片
     * @param viewId id
     * @param url url
     * @return holder
     */
    public ViewHolder setImageView(int viewId,String url){
        ImageView imageView = getView(viewId);
        ImageLoader.getInstance(3, ImageLoader.Type.LIFO).loadImage(url, (ImageView) getView(viewId));
        return this;
    }

    public int getPosition(){
        return position;
    }
}
