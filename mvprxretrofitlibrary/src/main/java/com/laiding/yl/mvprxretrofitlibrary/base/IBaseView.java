package com.laiding.yl.mvprxretrofitlibrary.base;

import com.laiding.yl.mvprxretrofitlibrary.listener.ProgressListener;

import java.util.List;

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
