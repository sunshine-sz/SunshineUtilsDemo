package com.sunshine.rxjavademo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.sunshine.rxjavademo.fresco.ImagePipelineConfigFactory;

/**
 * 程序入口
 * Created by Sunshine on 2016/9/1.
 */
public class MyApplication extends Application{

    private static MyApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        ToastUtils.init(this);
        //初始化
        Fresco.initialize(this, ImagePipelineConfigFactory.getImagePipelineConfig(this));
    }

    public static MyApplication getInstance(){
        return app;
    }
}
