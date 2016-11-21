package com.sunshine.rxjavademo.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者: Sunshine
 * 时间: 2016/11/3.
 * 邮箱: 44493547@qq.com
 * 描述: api管理者
 */

public class ApiManage {
    private static ApiManage apiManage;
    private String url = "http://news.onethird.com.cn";
    private Github github;
    private ZhiHu zhiHu;
    private MeiZi meiZi;
    private Object ob = new Object();

    public static ApiManage getInstance() {
        return ApiHolder.getInstance();
    }

    public Github getGithub() {
        if (github == null) {
            synchronized (ob) {
                if (github == null) {
                    github = new Retrofit.Builder()
                            .baseUrl(url)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build().create(Github.class);
                }
            }

        }

        return github;
    }

    public ZhiHu getZhihu(){
        if (zhiHu==null){
            synchronized (ob){
                if (zhiHu==null){
                    zhiHu = new Retrofit.Builder()
                            .baseUrl("http://news-at.zhihu.com/")
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(ZhiHu.class);
                }
            }
        }
        return zhiHu;
    }

    public MeiZi getMeiZi(){
        if (meiZi==null){
            synchronized (ob){
                if (meiZi==null){
                    meiZi = new Retrofit.Builder()
                            .baseUrl("http://gank.io")
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(MeiZi.class);
                }
            }
        }
        return meiZi;
    }

    private static class ApiHolder {
        public static ApiManage getInstance() {
            return new ApiManage();
        }
    }
}
