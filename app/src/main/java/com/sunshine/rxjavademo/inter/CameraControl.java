package com.sunshine.rxjavademo.inter;

import android.hardware.Camera;

import com.sunshine.rxjavademo.api.FlashMode;

/**
 * 作者: Sunshine
 * 时间: 2016/10/27.
 * 邮箱: 44493547@qq.com
 * 描述: 相机控制接口
 */

public interface CameraControl {
    /**
     * 切换摄像头
     */
    void switchCamera();

    /**
     * 获取相机的闪光灯类型
     * @return 类型
     */
    FlashMode getFlashMode();

    /**
     * 设置闪光灯类型
     * @param flashMode 类型
     */
    void setFlashMode(FlashMode flashMode);

    /**
     *  拍照
     *  @param callback 拍照回调函数
     */
    public void takePicture(Camera.PictureCallback callback);
}
