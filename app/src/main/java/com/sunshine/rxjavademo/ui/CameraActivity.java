package com.sunshine.rxjavademo.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.fitsleep.sunshinelibrary.utils.Logger;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.sunshine.rxjavademo.R;
import com.sunshine.rxjavademo.view.CameraSurfaceView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CameraActivity extends Activity implements Camera.PictureCallback {

    private static final String TAG = CameraActivity.class.getSimpleName();
    private CameraSurfaceView cameraSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_camera);
        cameraSurfaceView = (CameraSurfaceView) findViewById(R.id.camera_view);
        findViewById(R.id.takePicture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraSurfaceView.takePicture(CameraActivity.this);
            }
        });
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        BufferedOutputStream bos = null;
        Bitmap bm = null;
        try {
            // 获得图片
            bm = BitmapFactory.decodeByteArray(data, 0, data.length);
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                Logger.i(TAG, "Environment.getExternalStorageDirectory()="+Environment.getExternalStorageDirectory());
                String filePath = "/sdcard/dyk"+System.currentTimeMillis()+".jpg";//照片保存路径
                File file = new File(filePath);
                if (!file.exists()){
                    file.createNewFile();
                }
                bos = new BufferedOutputStream(new FileOutputStream(file));
                bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);//将图片压缩到流中

            }else{
                ToastUtils.showMessage("没有检测到内存卡");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bos.flush();//输出
                bos.close();//关闭
                bm.recycle();// 回收bitmap空间
                cameraSurfaceView.getCamera().stopPreview();// 关闭预览
                cameraSurfaceView.getCamera().startPreview();// 开启预览
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
