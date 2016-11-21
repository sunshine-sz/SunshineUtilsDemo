package com.sunshine.rxjavademo.adapter;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.sunshine.rxjavademo.R;
import com.sunshine.rxjavademo.bean.MeiZiData;
import com.sunshine.rxjavademo.inter.FrescoBitmapCallback;
import com.sunshine.rxjavademo.utils.FrescoLoadUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Lizhao
 * 时间：2016/11/7 17:13
 * 邮箱：44493547@qq.com
 * 备注：
 */
public class MeiZiAdapter extends RecyclerView.Adapter<MeiZiAdapter.ViewHolder>{

    private List<MeiZiData.ResultsEntity> results = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mz,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MeiZiData.ResultsEntity resultsEntity = results.get(position);
        holder.setData(resultsEntity);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    /**
     * 添加最新条目
     * @param results 新的妹子
     */
    public void addItem(List<MeiZiData.ResultsEntity> results) {
        this.results.addAll(results);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView simpleDraweeView;
        public ViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = (ImageView) itemView.findViewById(R.id.simple_view);
        }

        public void setData(MeiZiData.ResultsEntity resultsEntity) {
            FrescoLoadUtil.getInstance().loadImageBitmap(resultsEntity.getUrl(), new FrescoBitmapCallback<Bitmap>() {
                @Override
                public void onSuccess(Uri uri, Bitmap result) {
                    simpleDraweeView.setImageBitmap(result);
                }

                @Override
                public void onFailure(Uri uri, Throwable throwable) {
                    ToastUtils.showMessage(throwable.getMessage());
                }

                @Override
                public void onCancel(Uri uri) {

                }
            });
        }
    }
}
