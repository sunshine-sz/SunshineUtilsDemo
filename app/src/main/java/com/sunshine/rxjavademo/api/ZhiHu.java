package com.sunshine.rxjavademo.api;

import com.sunshine.rxjavademo.bean.ZHBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * 作者: Sunshine
 * 时间: 2016/11/3.
 * 邮箱: 44493547@qq.com
 * 描述: 新闻
 */

public interface ZhiHu {

    @GET("/api/4/news/latest")
    Observable<ZHBean> getLastDaily();

}
