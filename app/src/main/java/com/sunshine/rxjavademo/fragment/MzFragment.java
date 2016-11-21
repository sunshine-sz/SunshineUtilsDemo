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
import com.sunshine.rxjavademo.adapter.MeiZiAdapter;
import com.sunshine.rxjavademo.base.BaseFragment;
import com.sunshine.rxjavademo.bean.MeiZiData;
import com.sunshine.rxjavademo.bean.ZHBean;
import com.sunshine.rxjavademo.presenter.PresenterImpl;
import com.sunshine.rxjavademo.presenter.view.ImplFragment;
import com.sunshine.rxjavademo.view.DividerItemDecoration;

import java.util.List;

/**
 * 福利页面
 */
public class MzFragment extends BaseFragment implements ImplFragment {

    private static final String TAG = MzFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private MeiZiAdapter adapter;
    private boolean loadMore;
    private int index = 1;
    private PresenterImpl presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mz, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.mz_recycler);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy>0){
                    int childCount = linearLayoutManager.getChildCount();
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();
                    if (!loadMore &&(childCount + pastVisiblesItems)>=totalItemCount){
                        loadMore = true;
                        index+=1;
                        loadMore();
                    }
                }
            }
        });
        adapter = new MeiZiAdapter();
        recyclerView.setAdapter(adapter);
        presenter = new PresenterImpl(this);
        presenter.getMeiZi(index);
    }

    private void loadMore() {
        if (presenter!=null){
            Logger.e(TAG,"获取新妹子:"+index);
            presenter.getMeiZi(index);
        }
    }

    @Override
    public void updateList(MeiZiData imageBean) {
        List<MeiZiData.ResultsEntity> results = imageBean.getResults();
        loadMore = false;
        adapter.addItem(results);

    }

    @Override
    public void updateNews(ZHBean zhBean) {

    }

    @Override
    public void error(Throwable e) {
        ToastUtils.showMessage(e.getMessage());
    }
}
