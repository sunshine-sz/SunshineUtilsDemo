package com.sunshine.rxjavademo.view;

import android.animation.TypeEvaluator;

import com.sunshine.rxjavademo.bean.Point;

/**
 * 作者: Sunshine
 * 时间: 2016/10/25.
 * 邮箱: 44493547@qq.com
 * 描述:
 */

public class PointEvaluator implements TypeEvaluator{
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        float x = startPoint.getX()+fraction*(endPoint.getX()-startPoint.getX());
        float y = startPoint.getY()+fraction*(endPoint.getY()-startPoint.getY());
        return new Point(x,y);
    }
}
