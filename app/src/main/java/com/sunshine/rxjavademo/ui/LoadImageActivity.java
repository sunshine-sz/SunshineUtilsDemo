package com.sunshine.rxjavademo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sunshine.rxjavademo.R;
import com.sunshine.rxjavademo.adapter.RecyclerAdapter;
import com.sunshine.rxjavademo.view.GridItemDividerDecoration;

public class LoadImageActivity extends AppCompatActivity {
    private SimpleDraweeView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        initView();
    }


    private void initView() {
//        /**
//         * 远程图片	http://, https://	HttpURLConnection
//         * 本地文件	file://	FileInputStream
//         * Content provider	content://	ContentResolver
//         * asset目录下的资源	asset://	AssetManager
//         * res目录下的资源	res://	Resources.openRawResource    image.setImageURI("res://com.sunshine.rxjavademo/" + R.mipmap.wcdescr);
//         */
//        image = (SimpleDraweeView) findViewById(R.id.my_image_view);
//        Uri uri = Uri.parse(MyHandler.getImageUrls()[0]);
//        image.setImageURI(uri);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.addItemDecoration(new GridItemDividerDecoration(this, R.dimen.size_divier,R.color.color3F3F3F));
        recyclerView.setAdapter(new RecyclerAdapter(this));
    }
}
