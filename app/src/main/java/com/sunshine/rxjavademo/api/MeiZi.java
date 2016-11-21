package com.sunshine.rxjavademo.api;

import com.sunshine.rxjavademo.bean.MeiZiData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 妹子接口
 * Created by Lizhao on 2016/11/7.
 */

public interface MeiZi {
    @GET("/api/data/福利/10/{page}")
    Observable<MeiZiData> getMeizhiData(@Path("page") int page);
}
