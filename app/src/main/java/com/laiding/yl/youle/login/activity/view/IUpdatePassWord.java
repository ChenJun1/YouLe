package com.laiding.yl.youle.login.activity.view;

import com.laiding.yl.mvprxretrofitlibrary.base.IBaseView;

/**
 * Created by JunChen on 2018/3/1.
 * Remarks
 */

public interface IUpdatePassWord extends IBaseView {
    String getPhone();

    String getCode();

    String getPass();

    void isSuccess();
}
