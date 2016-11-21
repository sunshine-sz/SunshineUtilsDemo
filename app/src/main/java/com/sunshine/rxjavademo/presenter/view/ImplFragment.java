package com.sunshine.rxjavademo.presenter.view;

import com.sunshine.rxjavademo.bean.MeiZiData;
import com.sunshine.rxjavademo.bean.ZHBean;

/**
 * 作者: Sunshine
 * 时间: 2016/11/3.
 * 邮箱: 44493547@qq.com
 * 描述: UI操作接口
 */

public interface ImplFragment {
    void updateList(MeiZiData imageBean);
    void updateNews(ZHBean zhBean);
    void error(Throwable e);
}
