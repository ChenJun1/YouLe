package com.laiding.yl.youle.base;

import com.laiding.yl.mvprxretrofitlibrary.base.BaseFrgPresenter;

/**
 * Created by JunChen on 2018/1/18.
 * Remarks
 */

public class MyBaseFrgPresenter<V,T> extends BaseFrgPresenter<V,T> {

    public MyBaseFrgPresenter(V view, T fragmet) {
        super(view, fragmet);
    }
}
