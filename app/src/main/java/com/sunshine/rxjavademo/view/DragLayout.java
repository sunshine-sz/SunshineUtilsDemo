package com.sunshine.rxjavademo.view;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.fitsleep.sunshinelibrary.utils.Logger;
import com.sunshine.rxjavademo.R;

/**
 * 作者: Sunshine
 * 时间: 2016/10/31.
 * 邮箱: 44493547@qq.com
 * 描述: 可拖动的View,ViewDragHelper助手使用
 */

public class DragLayout extends LinearLayout {

    private ViewDragHelper viewDragHelper;
    private View mDragView;

    public DragLayout(Context context) {
        this(context,null);
    }

    public DragLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        viewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelperCallback());
    }

    /**
     * 加载布局完毕
     */
    @Override
    protected void onFinishInflate() {
        mDragView = findViewById(R.id.list_view);
    }

    /**
     * 回调处理类
     */
    class ViewDragHelperCallback extends ViewDragHelper.Callback{

        @Override//是否处理事件
        public boolean tryCaptureView(View child, int pointerId) {
            if (child==mDragView){
                return true;
            }
            return false;
        }

        @Override  //限制沿水平轴拖动子视图的运动
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Logger.d("DragLayout", "clampViewPositionHorizontal " + left + "," + dx);
            final int leftBound = getPaddingLeft();
            final int rightBound = getWidth() - mDragView.getWidth();
            //min获取两个数中较小的数,max返回两个数中较大的
            final int newLeft = Math.min(Math.max(left, leftBound), rightBound);
            return newLeft;
        }

        @Override //限制沿垂直轴拖动子视图的运动
        public int clampViewPositionVertical(View child, int top, int dy) {
            int topBound = getPaddingTop();
            int bw = getHeight()-mDragView.getHeight();
            int newTop = Math.min(Math.max(top, topBound), bw);
            return newTop;

        }

        @Override //当子视图捕获拖动或沉淀调用。
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            viewDragHelper.cancel();
            return false;
        }
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }
}
