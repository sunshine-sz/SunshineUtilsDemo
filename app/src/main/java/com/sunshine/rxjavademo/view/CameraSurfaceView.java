package com.sunshine.rxjavademo.view;

import android.content.Context;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.OrientationEventListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.sunshine.rxjavademo.api.FlashMode;
import com.sunshine.rxjavademo.inter.CameraControl;

import java.util.List;

/**
 * 作者: Sunshine
 * 时间: 2016/10/26.
 * 邮箱: 44493547@qq.com
 * 描述:
 */

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback, CameraControl {

    private static final String TAG = CameraSurfaceView.class.getSimpleName();
    private Context context;
    private int width;
    private int height;
    private SurfaceHolder holder;
    private Camera camera;
    /**
     * 当前摄像头的朝向，true为前摄像头，false为后置
     */
    private boolean isFrontCamera = false;
    /**
     * 上一次屏幕的方向
     */
    private int mOrientation;

    public CameraSurfaceView(Context context) {
        super(context);
        this.context = context;
        getWidthHeight();
        initView();
    }

    public CameraSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        getWidthHeight();
        initView();
    }

    public CameraSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        getWidthHeight();
        initView();
    }

    /**
     * 获取屏幕的宽高
     */
    private void getWidthHeight() {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
    }

    /**
     * 初始化组件
     */
    private void initView() {
        holder = getHolder();
        holder.addCallback(this);
        isFrontCamera = false;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        //初始化相机
        try {
            if (null == camera) {
                openCamera();
                //旋转屏幕90度
                camera.setDisplayOrientation(90);
            }
            setCameraParameters();
            //设置实时预览
            camera.setPreviewDisplay(holder);
        } catch (Exception e) {
            ToastUtils.showMessage("打开相机失败" + e.getMessage());
        }
        //开启预览
        camera.startPreview();
    }


    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        updateCameraOrientation();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        camera.stopPreview();
        camera.release();
        camera = null;
    }


    /**
     * 根据flag打开对应相机摄像头
     */
    private void openCamera() {
        //如果当前camera不为空，需要关闭释放
        if (null != camera) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }

        if (isFrontCamera) {
            //获取相机信息对象
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            //遍历相机物理摄像头个数
            for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
                //获取当前相机信息
                Camera.getCameraInfo(i, cameraInfo);
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                    try {
                        camera = Camera.open(i);
                    } catch (Exception e) {
                        camera = null;
                    }

                }
            }
        } else {
            try {
                camera = Camera.open();
            } catch (Exception e) {
                camera = null;
            }
        }
    }


    /**
     * 设置相机的参数
     */
    private void setCameraParameters() {
        //获取相机参数对象
        Camera.Parameters parameters = camera.getParameters();
        //获取相机支持的预览大小
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        //选择适合的大小
        Camera.Size previewSize = supportedPreviewSizes.get(0);
        parameters.setPreviewSize(previewSize.width, previewSize.height);
        //获取相机支持的图片大小
        List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
        //选择适合的大小
        Camera.Size pictureSize = supportedPictureSizes.get(0);
        parameters.setPictureSize(pictureSize.width, pictureSize.height);

        //设置相片的格式
        parameters.setPictureFormat(ImageFormat.JPEG);
        //设置相片的质量 0-100
        parameters.setJpegQuality(100);
        //设置相片缩略图的质量
        parameters.setJpegThumbnailQuality(100);
        //如果是后摄像设置聚焦模式
        if (isFrontCamera) {
            if (parameters.getSupportedFocusModes().contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);// 连续对焦模式
            } else {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);//自动对焦模式
            }
        }
        parameters.setZoom(0);
        //最后一定不能忘记把参数设置给相机，否则不起作用
        camera.setParameters(parameters);
        //开启屏幕朝向监听
        startOrientationChangeListener();
    }

    /**
     * 屏幕监听
     */
    private void startOrientationChangeListener() {
        OrientationEventListener orientationEventListener = new OrientationEventListener(getContext()) {
            @Override
            public void onOrientationChanged(int rotation) {
                //在屏幕方向改变时调用，在这里可以做图片方向的调整
                if (((rotation >= 0) && (rotation <= 45)) || (rotation > 315)) {
                    rotation = 0;
                } else if ((rotation > 45) && (rotation <= 135)) {
                    rotation = 90;
                } else if ((rotation > 135) && (rotation <= 225)) {
                    rotation = 180;
                } else if ((rotation > 225) && (rotation <= 315)) {
                    rotation = 270;
                } else {
                    rotation = 0;
                }
                if (rotation == mOrientation)
                    return;
                mOrientation = rotation;
                updateCameraOrientation();
            }
        };
        //开启屏幕监听
        orientationEventListener.enable();
    }

    /**
     * 根据方向改变图片的旋转
     */
    private void updateCameraOrientation() {
        if (null != camera) {
            Camera.Parameters parameters = camera.getParameters();
            //rotation 度数是0 90 180 270  水平方向是0
            int rotation = 90 + mOrientation == 360 ? 0 : 90 + mOrientation;
            //前置摄像头需要对垂直方向做变换，否则照片是颠倒的
            if (isFrontCamera) {
                if (rotation == 90) {
                    rotation = 270;
                } else if (rotation == 270) {
                    rotation = 90;
                }
            }
            parameters.setRotation(rotation);
            camera.setDisplayOrientation(90);
            camera.setParameters(parameters);
        }
    }

    @Override
    public void switchCamera() {

    }

    @Override
    public FlashMode getFlashMode() {
        return null;
    }

    @Override
    public void setFlashMode(FlashMode flashMode) {

    }

    @Override
    public void takePicture(final Camera.PictureCallback callback) {
        camera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean b, Camera camera) {
                if (b) {
                    camera.takePicture(null, null, callback);
                }
            }
        });
    }

    public Camera getCamera() {
        return camera;
    }
}
