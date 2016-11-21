package com.sunshine.rxjavademo.presenter;

import com.google.gson.Gson;
import com.sunshine.rxjavademo.api.ApiManage;
import com.sunshine.rxjavademo.bean.ImageBean;
import com.sunshine.rxjavademo.bean.MeiZiData;
import com.sunshine.rxjavademo.bean.ZHBean;
import com.sunshine.rxjavademo.presenter.view.ImplFragment;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者: Sunshine
 * 时间: 2016/11/3.
 * 邮箱: 44493547@qq.com
 * 描述: 网络请求实现类
 */

public class PresenterImpl implements PresenterInterface {

    private Gson gson = new Gson();
    private ImplFragment fragment;
    public PresenterImpl(ImplFragment fragment){
        this.fragment = fragment;
    }

    @Override
    public void getBanner() {
        ApiManage.getInstance().getGithub().getImageData("GLA1", "article")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ImageBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        fragment.error(e);
                    }

                    @Override
                    public void onNext(ImageBean imageBean) {
//                        fragment.updateList(imageBean);
                    }
                });
    }

    @Override
    public void getNews() {
        ApiManage.getInstance().getZhihu().getLastDaily()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZHBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        fragment.error(e);
                    }

                    @Override
                    public void onNext(ZHBean zhBean) {
                        fragment.updateNews(zhBean);
                    }
                });
    }

    @Override
    public void getMeiZi(int index) {
        ApiManage.getInstance().getMeiZi().getMeizhiData(index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MeiZiData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        fragment.error(e);
                    }

                    @Override
                    public void onNext(MeiZiData meiZiData) {
                        fragment.updateList(meiZiData);
                    }
                });
    }


}
