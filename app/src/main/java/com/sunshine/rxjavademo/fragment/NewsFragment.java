package com.sunshine.rxjavademo.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fitsleep.sunshinelibrary.utils.Logger;
import com.fitsleep.sunshinelibrary.utils.ToastUtils;
import com.sunshine.rxjavademo.R;
import com.sunshine.rxjavademo.adapter.NewsAdapter;
import com.sunshine.rxjavademo.base.BaseFragment;
import com.sunshine.rxjavademo.bean.MeiZiData;
import com.sunshine.rxjavademo.bean.ZHBean;
import com.sunshine.rxjavademo.presenter.PresenterImpl;
import com.sunshine.rxjavademo.presenter.view.ImplFragment;
import com.sunshine.rxjavademo.view.DividerItemDecoration;

import java.util.List;

/**
 * 新闻页.
 */
public class NewsFragment extends BaseFragment implements ImplFragment{

    private static final String TAG = NewsFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    //是否加载
    private boolean loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new NewsAdapter();
        recyclerView.setAdapter(adapter);
        PresenterImpl presenter = new PresenterImpl(this);
        presenter.getNews();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {//
                super.onScrollStateChanged(recyclerView, newState);
                Logger.e(TAG,"onScrollStateChanged");
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {//滚动中调用
                super.onScrolled(recyclerView, dx, dy);
                Logger.e(TAG,"onScrolled"+dx+"--"+dy);
                if (dy>0){
                    int childCount = linearLayoutManager.getChildCount();
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();
                    if (!loading &&(childCount + pastVisiblesItems)>=totalItemCount){
                        loading = true;
                        loadMore();
                    }
                }
            }
        });
    }

    private void loadMore() {
       ToastUtils.showMessage("加载更多");
    }


    @Override
    public void updateList(MeiZiData imageBean) {
        Logger.e(TAG,"imageBean:"+imageBean.getResults().get(0).getUrl());
    }

    @Override
    public void updateNews(ZHBean zhBean) {
        List<ZHBean.StoriesEntity > zhBeanStories = zhBean.getStories();
        if (adapter==null){
            adapter = new NewsAdapter();
        }
        adapter.addItem(zhBeanStories);
        Logger.e(TAG,zhBean.getStories().get(0).getTitle());
    }

    @Override
    public void error(Throwable e) {
        ToastUtils.showMessage(e.getMessage());
    }
}
