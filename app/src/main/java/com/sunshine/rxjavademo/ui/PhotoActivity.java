package com.sunshine.rxjavademo.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fitsleep.sunshinelibrary.utils.Logger;
import com.fitsleep.sunshinelibrary.utils.ScreenUtils;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.sunshine.rxjavademo.R;
import com.sunshine.rxjavademo.adapter.PhotoAdapter;
import com.sunshine.rxjavademo.bean.ImageFloder;
import com.sunshine.rxjavademo.view.ListImageDirPopupWindow;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class PhotoActivity extends Activity {

    private static final int SCAN_OVER = 0;
    private static final String TAG = PhotoActivity.class.getSimpleName();
    /**
     * 全部图片路径
     */
    private ArrayList<String> imagePaths = new ArrayList<>();
    /**
     * 图片数量最多的文件夹
     */
    private File mImgDir;
    /**
     * 临时的辅助类，用于防止同一个文件夹的多次扫描
     */
    private HashSet<String> mDirPaths = new HashSet<>();
    /**
     * 扫描拿到所有的图片文件夹
     */
    private List<ImageFloder> mImageFloders = new ArrayList<>();
    /**
     * 存储文件夹中的图片数量
     */
    private int mPicsSize;
    int totalCount = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SCAN_OVER:
                    progressDialog.dismiss();
                    //添加相机位置
                    imagePaths.add(0, "");
                    //添加所有图片文件夹
                    ImageFloder imageFloder = new ImageFloder();
                    imageFloder.setCount(totalCount);
                    imageFloder.setFirstImagePath(imagePaths.get(0));
                    imageFloder.setDir(Environment.getExternalStorageDirectory().getAbsolutePath());
                    mImageFloders.add(0, imageFloder);
                    //数据绑定View
                    data2View();
                    //初始化PopupWindow
                    initPopupWindow();
                    break;
            }
        }
    };


    private GridView gridView;
    private ProgressDialog progressDialog;
    private RelativeLayout rlBottom;
    private TextView tvCount;
    private ListImageDirPopupWindow popupWindow;
    private PhotoAdapter photoAdapter;
    private ArrayList<String> selectImageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        initView();
        initEvent();
        getPhotos();
    }

    private void initView() {
        gridView = (GridView) findViewById(R.id.main_grid);
        rlBottom = (RelativeLayout) findViewById(R.id.id_bottom_ly);
        tvCount = (TextView) findViewById(R.id.id_total_count);
    }

    private void initEvent() {
        rlBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.setAnimationStyle(R.style.popupWindow);
                popupWindow.showAsDropDown(rlBottom, 0, 0);

                // 设置背景颜色变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = .3f;
                getWindow().setAttributes(lp);
            }
        });
    }

    /**
     * 数据绑定View
     */
    private void data2View() {
        if (mImgDir == null) {
            ToastUtils.showMessage("一张也没有");
            return;
        }

        photoAdapter = new PhotoAdapter(this, imagePaths, R.layout.item_all_img, null);
        photoAdapter.selectMax(9);
        gridView.setAdapter(photoAdapter);
        tvCount.setText(totalCount + "张");
    }

    /**
     * 初始化PopupWindow
     */
    private void initPopupWindow() {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_folder, null);
        float height = (ScreenUtils.getScreenHeight(getApplicationContext())) * 0.7f;
        popupWindow = new ListImageDirPopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, (int) height, mImageFloders);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // 设置背景颜色变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });

        popupWindow.setOnSelectDirListener(new ListImageDirPopupWindow.OnSelectDirListener() {
            @Override
            public void selectDir(ImageFloder imageFloder, int position) {
                popupWindow.dismiss();
                if (position == 0) {
                    photoAdapter = new PhotoAdapter(PhotoActivity.this, imagePaths, R.layout.item_all_img, null);
                    photoAdapter.selectMax(9);
                    gridView.setAdapter(photoAdapter);
                } else {
                    mImgDir = new File(imageFloder.getDir());
                    List<String> pathList = Arrays.asList(mImgDir.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String filename) {
                            if (filename.endsWith(".jpg") || filename.endsWith(".png")
                                    || filename.endsWith(".jpeg"))
                                return true;
                            return false;
                        }
                    }));
                    if (selectImageList == null) {
                        selectImageList = new ArrayList<String>();
                    } else {
                        selectImageList.clear();
                    }

                    for (String str : pathList) {
                        selectImageList.add(str);
                    }
                    photoAdapter = new PhotoAdapter(PhotoActivity.this, selectImageList, R.layout.item_all_img, mImgDir.getAbsolutePath());
                    photoAdapter.selectMax(9);
                    gridView.setAdapter(photoAdapter);
                }

            }
        });
    }

    /**
     * 获取SD中的jpg png文件
     */
    private void getPhotos() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            ToastUtils.showMessage("暂无外部存储");
            return;
        }
        progressDialog = ProgressDialog.show(this, null, "正在扫描照片");

        new Thread(new Runnable() {
            @Override
            public void run() {

                String firstImage = null;

                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = PhotoActivity.this.getContentResolver();

                // 只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null, MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_MODIFIED);

                while (mCursor.moveToNext()) {
                    // 获取图片的路径
                    String path = mCursor.getString(mCursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));
                    imagePaths.add(path);
                    Logger.i(TAG,path);
                    // 拿到第一张图片的路径
                    if (firstImage == null)
                        firstImage = path;
                    // 获取该图片的父路径名
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null)
                        continue;
                    String dirPath = parentFile.getAbsolutePath();
                    ImageFloder imageFloder = null;
                    // 利用一个HashSet防止多次扫描同一个文件夹（不加这个判断，图片多起来还是相当恐怖的~~）
                    if (mDirPaths.contains(dirPath)) {
                        continue;
                    } else {
                        mDirPaths.add(dirPath);
                        // 初始化imageFloder
                        imageFloder = new ImageFloder();
                        imageFloder.setDir(dirPath);
                        imageFloder.setFirstImagePath(path);
                    }

                    int picSize = parentFile.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String filename) {
                            if (filename.endsWith(".jpg")
                                    || filename.endsWith(".png")
                                    || filename.endsWith(".jpeg"))
                                return true;
                            return false;
                        }
                    }).length;
                    totalCount += picSize;

                    imageFloder.setCount(picSize);
                    mImageFloders.add(imageFloder);

                    if (picSize > mPicsSize) {
                        mPicsSize = picSize;
                        mImgDir = parentFile;
                    }
                }
                mCursor.close();

                // 扫描完成，辅助的HashSet也就可以释放内存了
                mDirPaths = null;

                // 通知Handler扫描图片完成
                handler.sendEmptyMessage(SCAN_OVER);
            }

        }).start();
    }
}
