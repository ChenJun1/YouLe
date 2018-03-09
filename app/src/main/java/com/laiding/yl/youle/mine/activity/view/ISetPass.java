package com.laiding.yl.youle.mine.activity.view;

import com.laiding.yl.mvprxretrofitlibrary.base.IBaseView;

/**
 * Created by JunChen on 2018/3/1.
 * Remarks
 */

public interface ISetPass extends IBaseView {
    CharSequence getPass();

    CharSequence getPass2();

    void isSuccess();
}
