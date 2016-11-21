package com.sunshine.rxjavademo.helper;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 作者: Sunshine
 * 时间: 2016/9/14.
 * 邮箱: 44493547@qq.com
 * 描述: 字体助手
 */
public class FontHelper {
    public static final String FONTS_DIR = "fonts/";
    public static final String DEF_FONT = FONTS_DIR + "iconfont.ttf";//fontawesome-webfont

    public static final void injectFont(View rootView){
        injectFont(rootView,Typeface.createFromAsset(rootView.getContext().getAssets(),DEF_FONT));
    }

    public static final void injectFont(View rootView, Typeface tf){
        if (rootView instanceof ViewGroup){
            ViewGroup viewGroup = (ViewGroup) rootView;
            int childCount = viewGroup.getChildCount();
            for (int i=0;i<childCount;i++){
                injectFont(viewGroup.getChildAt(i),tf);
            }
        }else if (rootView instanceof TextView){
            ((TextView)rootView).setTypeface(tf);
        }
    }
}
