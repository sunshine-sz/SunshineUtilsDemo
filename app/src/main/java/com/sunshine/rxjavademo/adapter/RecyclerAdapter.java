package com.sunshine.rxjavademo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sunshine.rxjavademo.utils.MyHandler;
import com.sunshine.rxjavademo.R;

import java.util.Random;

/**
 * 作者: Sunshine
 * 时间: 2016/10/31.
 * 邮箱: 44493547@qq.com
 * 描述:
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private Context context;
    public RecyclerAdapter(Context context){
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_recycler, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String url = MyHandler.getImageUrls()[position];
        holder.setData(url);
    }

    private int getHeight() {
        Random random = new Random();
        int nextInt = random.nextInt(300);
        return nextInt+100;
    }

    @Override
    public int getItemCount() {
        return MyHandler.getImageUrls().length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        SimpleDraweeView simpleDraweeView;
        public ViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.simple_view);
        }

        public void setData(String url){
            simpleDraweeView.setImageURI(url);
        }
    }
}
