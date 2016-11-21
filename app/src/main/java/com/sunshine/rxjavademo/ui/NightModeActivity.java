package com.sunshine.rxjavademo.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunshine.rxjavademo.R;

import java.util.ArrayList;
import java.util.List;

public class NightModeActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private CheckBox cbMode;
    private TextView tvView;
    private LinearLayout llLayout;
    /**
     * checkBox的集合
     */
    private List<CheckBox> checkBoxList;
    /**
     * textView的集合
     */
    private List<TextView> textViewList;
    /**
     * LinearLayout的集合
     */
    private List<LinearLayout> linearLayoutList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.DayTheme);
        setContentView(R.layout.activity_night_mode);
        initView();
    }

    private void initView() {
        linearLayoutList = new ArrayList<>();
        llLayout = (LinearLayout) findViewById(R.id.ll_layout);
        linearLayoutList.add(llLayout);
        checkBoxList = new ArrayList<>();
        cbMode = (CheckBox) findViewById(R.id.cb_mode);
        cbMode.setOnCheckedChangeListener(this);
        checkBoxList.add(cbMode);
        tvView = (TextView) findViewById(R.id.tv_view);
        textViewList = new ArrayList<>();
        textViewList.add(tvView);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b){
            setTheme(R.style.NightTheme);
            refreshUI();
            showAnimation();
        }else {
            setTheme(R.style.DayTheme);
            refreshUI();
            showAnimation();
        }
    }

    private void refreshUI() {
        TypedValue background = new TypedValue();//背景色
        TypedValue textColor = new TypedValue();//字体颜色
        Resources.Theme theme = getTheme();
        theme.resolveAttribute(R.attr.myBackground, background, true);
        theme.resolveAttribute(R.attr.myTextColor, textColor, true);


        for (LinearLayout layout : linearLayoutList) {
            layout.setBackgroundResource(background.resourceId);
        }
        for (CheckBox checkBox : checkBoxList) {
            checkBox.setBackgroundResource(background.resourceId);
        }

        for (TextView textView : textViewList){
            textView.setBackgroundResource(background.resourceId);
        }

        Resources resources = getResources();
        for (CheckBox checkBox : checkBoxList) {
            checkBox.setTextColor(resources.getColor(textColor.resourceId));
        }

        for (TextView textView : textViewList) {
            textView.setTextColor(resources.getColor(textColor.resourceId));
        }
        refreshStatusBar();
    }

    private void refreshStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = getTheme();
            theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
            getWindow().setStatusBarColor(getResources().getColor(typedValue.resourceId));
        }
    }

    private void showAnimation() {
        final View decorView = getWindow().getDecorView();
        Bitmap cacheBitmap = getCacheBitmapFromView(decorView);
        if (decorView instanceof ViewGroup && cacheBitmap != null) {
            final View view = new View(this);
            view.setBackgroundDrawable(new BitmapDrawable(getResources(), cacheBitmap));
            ViewGroup.LayoutParams layoutParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) decorView).addView(view, layoutParam);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
            objectAnimator.setDuration(300);
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    ((ViewGroup) decorView).removeView(view);
                }
            });
            objectAnimator.start();
        }
    }

    private Bitmap getCacheBitmapFromView(View view) {
        final boolean drawingCacheEnabled = true;
        view.setDrawingCacheEnabled(drawingCacheEnabled);
        view.buildDrawingCache(drawingCacheEnabled);
        final Bitmap drawingCache = view.getDrawingCache();
        Bitmap bitmap;
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
        } else {
            bitmap = null;
        }
        return bitmap;
    }
}
