package com.sunshine.rxjavademo.view;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sunshine.rxjavademo.R;
import com.sunshine.rxjavademo.adapter.CommonAdapter;
import com.sunshine.rxjavademo.bean.ImageFloder;
import com.sunshine.rxjavademo.helper.ViewHolder;

import java.util.List;

/**
 * PopupWindow实现类
 * Created by Sunshine on 2016/9/8.
 */
public class ListImageDirPopupWindow extends BasePopupWindowForListView<ImageFloder> {

    private ListView dirListView;

    public ListImageDirPopupWindow(View contentView, int width, int height, List<ImageFloder> mDatas) {
        super(contentView, width, height, true, mDatas);
    }

    @Override
    protected void beforeInitWeNeedSomeParams(Object... params) {

    }

    @Override
    public void initViews() {
        dirListView = (ListView) findViewById(R.id.list_view);
        dirListView.setAdapter(new CommonAdapter<ImageFloder>(context,mDatas,R.layout.list_dir_item) {
            @Override
            protected void convert(ViewHolder viewHolder, ImageFloder item) {
                viewHolder.setText(R.id.id_dir_item_name,item.getName());
                viewHolder.setText(R.id.id_dir_item_count,item.getCount()+"张");
                viewHolder.setImageView(R.id.id_dir_item_image,item.getFirstImagePath());
            }
        });
    }

    public OnSelectDirListener onSelectDirListener;

    public void setOnSelectDirListener(OnSelectDirListener onSelectDirListener){
        this.onSelectDirListener = onSelectDirListener;
    }

    /**
     * 选择文件夹监听
     */
    public interface OnSelectDirListener{
        void selectDir(ImageFloder imageFloder,int position);
    }

    @Override
    public void initEvents() {
        dirListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (onSelectDirListener!=null){
                    onSelectDirListener.selectDir(mDatas.get(i),i);
                }
            }
        });
    }

    @Override
    public void init() {

    }
}
