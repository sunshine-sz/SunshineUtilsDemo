package com.sunshine.rxjavademo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fitsleep.sunshinelibrary.utils.ConvertUtils;
import com.sunshine.rxjavademo.R;
import com.sunshine.rxjavademo.bean.ZHBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: Sunshine
 * 时间: 2016/11/3.
 * 邮箱: 44493547@qq.com
 * 描述:
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{


    private List<ZHBean.StoriesEntity> entityList = new ArrayList<>();

    public void addItem(List<ZHBean.StoriesEntity> entityList){
        this.entityList.addAll(entityList);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ZHBean.StoriesEntity storiesEntity = entityList.get(position);
        holder.setData(storiesEntity);
    }

    @Override
    public int getItemCount() {
        return entityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        SimpleDraweeView simpleDraweeView;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.simple_view);
            textView = (TextView) itemView.findViewById(R.id.text);
        }

        public void setData(ZHBean.StoriesEntity storiesEntity){
            simpleDraweeView.setImageURI(storiesEntity.getImages().get(0));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = ConvertUtils.dp2px(textView.getContext(),10);
            textView.setLines(2);
            textView.setLayoutParams(layoutParams);
            textView.setText(storiesEntity.getTitle());
        }
    }
}
