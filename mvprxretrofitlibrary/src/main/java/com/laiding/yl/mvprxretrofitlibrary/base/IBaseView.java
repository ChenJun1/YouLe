package com.laiding.yl.mvprxretrofitlibrary.base;

import com.laiding.yl.mvprxretrofitlibrary.listener.ProgressListener;

/**
 * IBaseView
 *
 * @author JunChen
 */

public interface IBaseView extends ProgressListener{
    void showError(String errorMsg);

    void isTokenExpired(String erreMsg);

    boolean isLogin();
}
