package com.sunshine.rxjavademo.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.sunshine.rxjavademo.R;

/**
 * 作者：LiZhao
 * 时间：2017.1.4 11:15
 * 邮箱：44493547@qq.com
 * 备注：可拖动的radioGroup
 */
public class FreeRadioGroup extends RadioGroup {

    private int mMillisInFuture;
    private float mToAlpha;
    private boolean mMoveable;
    private boolean mAutoBack;
    private MyCountDownTimer mTimer;
    private long countDownInterval = 500;
    private int mViewWidth;
    private int mViewHeight;
    private int mParentWidth;
    private int mParentHeight;
    private int mMinLeftMargin;
    private int mPaddingLeft;
    private int mRightDistance;
    private int mMaxLeftMargin;
    private int mMinTopMargin;
    private int mMaxTopMargin;
    private int mTopPadding;
    private int mBottomDistance;
    private float mCurrentX;
    private float mCurrentY;
    private int mCurrentLeft;
    private int mCurrentTop;

    public FreeRadioGroup(Context context) {
        this(context, null);
    }

    public FreeRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * 初始化属性参数
     *
     * @param context 上下文
     * @param attrs   属性
     */
    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.free);
        //松手后多久淡出
        mMillisInFuture = ta.getInt(R.styleable.free_millisInFuture, 3 * 1000);
        //透明值
        mToAlpha = ta.getFloat(R.styleable.free_toAlpha, 0.2f);
        //是否可拖动
        mMoveable = ta.getBoolean(R.styleable.free_moveable, false);
        //松手后是否自动归回侧边
        mAutoBack = ta.getBoolean(R.styleable.free_autoBack, false);
        ta.recycle();
        mTimer = new MyCountDownTimer(mMillisInFuture, countDownInterval);
        mTimer.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (mMoveable) {
            ViewGroup parentView = (ViewGroup) getParent();
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) getLayoutParams();
            //获取view的宽和高
            mViewWidth = getRight() - getLeft();
            mViewHeight = getBottom() - getTop();
            //获取父布局的宽和高
            mParentWidth = parentView.getMeasuredWidth();
            mParentHeight = parentView.getMeasuredHeight();
            //最小左边距
            mMinLeftMargin = marginLayoutParams.leftMargin;
            //获取父左padding
            mPaddingLeft = parentView.getPaddingLeft();
            //右边总边距
            mRightDistance = marginLayoutParams.rightMargin + parentView.getPaddingRight();
            //view最大左边距
            mMaxLeftMargin = mParentWidth - mRightDistance - mPaddingLeft - mViewWidth;
            //最小上边距
            mMinTopMargin = marginLayoutParams.topMargin;
            mTopPadding = parentView.getPaddingTop();
            mBottomDistance = marginLayoutParams.bottomMargin + parentView.getPaddingBottom();
            //最大上边距
            mMaxTopMargin = mParentHeight - mBottomDistance - mViewHeight - mTopPadding;
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //按下时view不在透明
                setAlpha(1f);
                mTimer.cancel();
                if (mMoveable) {
                    //获取当前的位置和边距
                    mCurrentX = ev.getRawX();
                    mCurrentY = ev.getRawY();
                    MarginLayoutParams lp = (MarginLayoutParams) getLayoutParams();
                    mCurrentLeft = lp.leftMargin;
                    mCurrentTop = lp.topMargin;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mMoveable) {
                    mCurrentLeft += ev.getRawX() - mCurrentX;
                    mCurrentTop += ev.getRawY() - mCurrentY;
                    //判断左边界
                    mCurrentLeft = mCurrentLeft < mMinLeftMargin ? mMinLeftMargin : mCurrentLeft;
                    //判断右边界
                    mCurrentLeft = (mPaddingLeft + mCurrentLeft + mViewWidth + mRightDistance) > mParentWidth ? mMaxLeftMargin : mCurrentLeft;
                    //判断上边界
                    mCurrentTop = mCurrentTop < mMinTopMargin ? mMinTopMargin : mCurrentTop;
                    //判断下边界
                    mCurrentTop = (mTopPadding + mCurrentTop + mViewHeight + mBottomDistance) > mParentHeight ? mParentHeight : mCurrentTop;
                    MarginLayoutParams lp = (MarginLayoutParams) getLayoutParams();
                    lp.leftMargin = mCurrentLeft;
                    lp.topMargin = mCurrentTop;
                    setLayoutParams(lp);
                    mCurrentX = ev.getRawX();
                    mCurrentY = ev.getRawY();
                }
                break;
            case MotionEvent.ACTION_UP:
                //抬起时开始倒计时淡化view
                mTimer.start();
                //归位
                if (mMoveable && mAutoBack) {
                    MarginLayoutParams lp = (MarginLayoutParams) getLayoutParams();
                    int fromLeftMargin = lp.leftMargin;
                    if (getLeft() < (mParentWidth - getLeft() - mViewWidth)) {
                        lp.leftMargin = mMinLeftMargin;
                    } else {
                        lp.leftMargin = mMaxLeftMargin;
                    }
                    ObjectAnimator marginChange = ObjectAnimator.ofInt(new Wrapper(this), "leftMargin", fromLeftMargin, lp.leftMargin);
                    marginChange.setDuration(500);
                    marginChange.start();
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    /**
     * 包装类
     */
    class Wrapper {
        private ViewGroup mTarget;

        public Wrapper(ViewGroup mTarget) {
            this.mTarget = mTarget;
        }

        public int getLeftMargin() {
            MarginLayoutParams lp = (MarginLayoutParams) mTarget.getLayoutParams();
            return lp.leftMargin;
        }

        public void setLeftMargin(int leftMargin) {
            MarginLayoutParams lp = (MarginLayoutParams) mTarget.getLayoutParams();
            lp.leftMargin = leftMargin;
            mTarget.requestLayout();
        }
    }

    /**
     * 倒计时淡出
     */
    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {

        }

        @Override
        public void onFinish() {
            setAlpha(mToAlpha);
        }
    }
}
