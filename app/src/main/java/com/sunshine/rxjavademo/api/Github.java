package com.sunshine.rxjavademo.api;

import com.sunshine.rxjavademo.bean.ImageBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 接口
 * Created by Sunshine on 2016/8/30.
 */
public interface Github {

    @GET("/{owner}/{repo}/gars?rid=1")///GLA1/article/gars?rid=1
    Observable<ImageBean> getImageData(@Path("owner") String owner, @Path("repo") String repo);
}
