package com.sunshine.rxjavademo.bean;

import android.graphics.Point;
import android.graphics.Rect;

/**
 * 作者: Sunshine
 * 时间: 2016/10/28.
 * 邮箱: 44493547@qq.com
 * 描述: 条目成员
 */
public class HourItem {

    public String time; //时间点
    public Rect windyBoxRect; //表示风力的box
    public int windy; //风力
    public int temperature; //温度
    public Point tempPoint; //温度的点坐标
    public int res = -1; //图片资源(有则绘制)
}
