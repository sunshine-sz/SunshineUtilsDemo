package com.sunshine.rxjavademo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * 作者: Sunshine
 * 时间: 2016/9/20.
 * 邮箱: 44493547@qq.com
 * 描述: 仿win10系统搜索动画
 */
public class Win10Search extends View{
    private Paint mPaint;
    private Path mPath;
    private PathMeasure mPathMeasure;
    private int mWidth,mHeight;
    private ValueAnimator valueAnimator;
    //用这个来接受ValueAnimator的返回值，代表整个动画的进度
    private float t;
    private Path dst;

    public Win10Search(Context context) {
        super(context);
    }

    public Win10Search(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(15);
        mPaint.setColor(Color.BLUE);
        //设置画笔为园笔
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //抗锯齿
        mPaint.setAntiAlias(true);

        mPath = new Path();
        RectF rect = new RectF(-150,-150,150,150);
        mPath.addArc(rect,-90,359.9f);

        mPathMeasure = new PathMeasure(mPath,false);

        valueAnimator = ValueAnimator.ofFloat(0f,1f).setDuration(2000);
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        valueAnimator.setInterpolator(linearInterpolator);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                t = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth/2,mHeight/2);
        dst = new Path();
        if(t>=0.95){
            canvas.drawPoint(0,-150,mPaint);
        }
        int num = (int) (t/0.05);
        float s,y,x;
        switch(num){
            default:
            case 5:
                x = t-0.25f*(1-t);
                s = mPathMeasure.getLength();
                y = -s*x*x+2*s*x;
                mPathMeasure.getSegment(y,y+1, dst,true);
            case 4:
                x = t-0.20f*(1-t);
                s = mPathMeasure.getLength();
                y = -s*x*x+2*s*x;
                mPathMeasure.getSegment(y,y+1, dst,true);
            case 3:
                x = t-0.15f*(1-t);
                s = mPathMeasure.getLength();
                y = -s*x*x+2*s*x;
                mPathMeasure.getSegment(y,y+1, dst,true);
            case 2:
                x = t-0.10f*(1-t);
                s = mPathMeasure.getLength();
                y = -s*x*x+2*s*x;
                mPathMeasure.getSegment(y,y+1, dst,true);
            case 1:
                x = t-0.05f*(1-t);
                s = mPathMeasure.getLength();
                y = -s*x*x+2*s*x;
                mPathMeasure.getSegment(y,y+1, dst,true);
            case 0:
                x = t;
                s = mPathMeasure.getLength();
                y = -s*x*x+2*s*x;
                mPathMeasure.getSegment(y,y+1, dst,true);
                break;
        }
        canvas.drawPath(dst,mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    public void startAnimation(){
        valueAnimator.start();
    }

    public void stopAnimation(){
        valueAnimator.end();
    }
}
