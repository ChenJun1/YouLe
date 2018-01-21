package com.laiding.yl.youle.base;

/**
 * Created by JunChen on 2018/1/18.
 * Remarks
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laiding.yl.mvprxretrofitlibrary.base.BaseFragment;

public abstract class MyBaseFragment extends BaseFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
