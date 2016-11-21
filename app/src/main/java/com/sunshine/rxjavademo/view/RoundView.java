package com.sunshine.rxjavademo.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.fitsleep.sunshinelibrary.utils.Logger;
import com.sunshine.rxjavademo.bean.Point;

/**
 * 作者: Sunshine
 * 时间: 2016/10/25.
 * 邮箱: 44493547@qq.com
 * 描述: 圆形
 */

public class RoundView extends View {

    private float radius = 100f;
    private Paint paint;
    private Point currentPoint;
    private String color;

    public RoundView(Context context) {
        super(context);
        init();
    }

    public RoundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (currentPoint ==null){
            currentPoint = new Point(radius,radius);
            startAnimation();
        }
        canvas.drawCircle(currentPoint.getX(), currentPoint.getY(),radius,paint);
    }

    private void startAnimation() {
        Point startPoint = new Point(radius,radius);
        Point endPoint = new Point(getWidth()-radius,getHeight()-radius);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currentPoint = (Point) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(this,"color",new ColorEvaluator(),"#0000FF","#FF0000");
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                String currentColor = (String) valueAnimator.getAnimatedValue();
                Logger.e(RoundView.class.getSimpleName(),currentColor);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(valueAnimator).with(objectAnimator);
        animatorSet.setDuration(5000);
        animatorSet.start();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        paint.setColor(Color.parseColor(color));
        invalidate();
    }
}
