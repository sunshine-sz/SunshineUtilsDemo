package com.sunshine.rxjavademo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;

import com.fitsleep.sunshinelibrary.utils.ConvertUtils;
import com.fitsleep.sunshinelibrary.utils.ScreenUtils;

import java.util.List;

/**
 * 作者: Sunshine
 * 时间: 2016/10/28.
 * 邮箱: 44493547@qq.com
 * 描述: IndexHorizontalScrollView
 */
public class IndexHorizontalScrollView extends HorizontalScrollView {

    private static final String TAG = IndexHorizontalScrollView.class.getSimpleName();
    private Paint textPaint;
    private Today24HourView today24HourView;

    public IndexHorizontalScrollView(Context context) {
        this(context, null);
    }

    public IndexHorizontalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndexHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        textPaint = new Paint();
        textPaint.setTextSize(ConvertUtils.sp2px(getContext(), 12));
        textPaint.setAntiAlias(true);
        textPaint.setColor(new Color().WHITE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int offset = computeHorizontalScrollOffset();
        int maxOffset = computeHorizontalScrollRange() - ScreenUtils.getScreenWidth(getContext());
        if(today24HourView != null){
//            today24HourView.drawLeftTempText(canvas, offset);
            today24HourView.setScrollOffset(offset, maxOffset);
        }
    }

    public void setToday24HourView(Today24HourView today24HourView){
        this.today24HourView = today24HourView;
    }
}
