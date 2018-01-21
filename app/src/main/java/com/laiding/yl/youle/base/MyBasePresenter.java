package com.laiding.yl.youle.base;

import com.laiding.yl.mvprxretrofitlibrary.base.BasePresenter;

/**
 * Created by JunChen on 2018/1/18.
 * Remarks
 */

public class MyBasePresenter<V,T> extends BasePresenter<V,T> {
    public MyBasePresenter(V view, T activity) {
        super(view, activity);
    }
}
