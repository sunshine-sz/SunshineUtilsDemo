package com.sunshine.rxjavademo.inter;

import android.net.Uri;

/**
 * 作者: Sunshine
 * 时间: 2016/11/2.
 * 邮箱: 44493547@qq.com
 * 描述:
 */

public interface FrescoBitmapCallback<T> {
    void onSuccess(Uri uri, T result);

    void onFailure(Uri uri, Throwable throwable);

    void onCancel(Uri uri);
}
