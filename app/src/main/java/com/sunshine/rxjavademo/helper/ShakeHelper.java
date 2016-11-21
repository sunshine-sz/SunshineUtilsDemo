package com.sunshine.rxjavademo.helper;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.fitsleep.sunshinelibrary.utils.Logger;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;

/**
 * 作者: Sunshine
 * 时间: 2016/9/22.
 * 邮箱: 44493547@qq.com
 * 描述: 传感器帮助类
 */

public class ShakeHelper implements SensorEventListener {

    private Context mContext;
    //传感器管理器
    private SensorManager mSensorManager;
    //传感器
    private Sensor mSensor;
    //速度阀值
    private int mSpeed=3000;
    //时间间隔
    private int mInterval=50;
    //上一次摇晃的时间
    private long LastTime;
    //上一次的x、y、z坐标
    private float LastX,LastY,LastZ;

    public ShakeHelper(Context context){
        this.mContext = context;
    }

    public void start(){
        mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager!=null){
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        if (mSensor!=null){
            mSensorManager.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_GAME);
        }
    }

    public void stop(){
        if (mSensorManager!=null){
            mSensorManager.unregisterListener(this);
        }
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Logger.i(getClass().getSimpleName(),"onSensorChanged");
        long NowTime=System.currentTimeMillis();
        if((NowTime-LastTime)<mInterval)
            return;
        //将NowTime赋给LastTime
        LastTime=NowTime;
        //获取x,y,z
        float NowX=sensorEvent.values[0];
        float NowY=sensorEvent.values[1];
        float NowZ=sensorEvent.values[2];
        //计算x,y,z变化量
        float DeltaX=NowX-LastX;
        float DeltaY=NowY-LastY;
        float DeltaZ=NowZ-LastZ;
        //赋值
        LastX=NowX;
        LastY=NowY;
        LastZ=NowZ;
        //计算
        double NowSpeed = Math.sqrt(DeltaX * DeltaX + DeltaY * DeltaY + DeltaZ * DeltaZ)/mInterval * 10000;
        //判断
        if(NowSpeed>=mSpeed)
        {
            ToastUtils.showMessage("你摇晃了手机！");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        Logger.e(getClass().getSimpleName(),"onAccuracyChanged");
    }
}
