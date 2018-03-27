package com.laiding.yl.mvprxretrofitlibrary.http.observer;


import android.text.TextUtils;


import com.laiding.yl.mvprxretrofitlibrary.R;
import com.laiding.yl.mvprxretrofitlibrary.base.IBaseView;
import com.laiding.yl.mvprxretrofitlibrary.http.exception.ApiException;
import com.laiding.yl.mvprxretrofitlibrary.http.exception.ExceptionEngine;
import com.laiding.yl.mvprxretrofitlibrary.http.exception.ServerException;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpRequestListener;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.RxActionManagerImpl;
import com.laiding.yl.mvprxretrofitlibrary.listener.ProgressListener;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * 适用Retrofit网络请求Observer(监听者)
 * 备注:
 * 1.重写onSubscribe，添加请求标识
 * 2.重写onError，封装错误/异常处理，移除请求
 * 3.重写onNext，移除请求
 * 4.重写cancel，取消请求
 *
 * @author JunChen
 */
public abstract class HttpRxObserver<T> implements Observer<T>, HttpRequestListener {

    private String mTag;//请求标识
    private ProgressListener mProgressListener; //请求等待
    private IBaseView mView;

    public HttpRxObserver() {
    }

    public HttpRxObserver(String tag, IBaseView view) {
        this.mTag = tag;
        this.mView = view;
    }

    public HttpRxObserver(String tag, ProgressListener progressListener) {
        this.mTag = tag;
        this.mProgressListener = progressListener;

    }

    public HttpRxObserver(String tag) {
        this.mTag = tag;
    }

    @Override
    public void onError(Throwable e) {
        RxActionManagerImpl.getInstance().remove(mTag);
        if (mView != null) {
            mView.closeLoading();
        }

        ApiException apiException = (ApiException) e;
        onError(apiException);
        if (mView != null)
            if(apiException.getCode()==ExceptionEngine.X1008||apiException.getCode()==ExceptionEngine.X1007){
                mView.isTokenExpired("登录失效，重新登陆");
            }else if(apiException.getCode()==ExceptionEngine.X1018){
                mView.isTokenExpired(apiException.getMsg());
            }else{
                mView.showError(apiException.getMsg());
            }
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onNext(@NonNull T t) {
        if (!TextUtils.isEmpty(mTag)) {
            RxActionManagerImpl.getInstance().remove(mTag);
        }
        if (mView != null) {
            mView.closeLoading();
        }
        onSuccess(t);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (!TextUtils.isEmpty(mTag)) {
            RxActionManagerImpl.getInstance().add(mTag, d);
        }
        if (mView != null) {
            mView.showLoading();
        }
            onStart(d);

    }

    @Override
    public void cancel() {
        if (!TextUtils.isEmpty(mTag)) {
            RxActionManagerImpl.getInstance().cancel(mTag);
        }
        if (mView != null) {
            mView.closeLoading();
        }
    }

    /**
     * 是否已经处理
     *
     * @author ZhongDaFeng
     */
    public boolean isDisposed() {
        if (TextUtils.isEmpty(mTag)) {
            return true;
        }
        return RxActionManagerImpl.getInstance().isDisposed(mTag);
    }

    protected abstract void onStart(Disposable d);

    /**
     * 错误/异常回调
     *
     * @author ZhongDaFeng
     */
    protected abstract void onError(ApiException e);

    /**
     * 成功回调
     *
     * @author ZhongDaFeng
     */
    protected abstract void onSuccess(T response);

}
