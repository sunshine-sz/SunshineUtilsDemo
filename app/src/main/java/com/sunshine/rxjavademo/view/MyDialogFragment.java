package com.sunshine.rxjavademo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunshine.rxjavademo.R;

/**
 * 作者: Sunshine
 * 时间: 2016/9/22.
 * 邮箱: 44493547@qq.com
 * 描述: fragment对话框
 */

public class MyDialogFragment extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_view,container,false);
    }
}
