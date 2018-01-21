package com.laiding.yl.mvprxretrofitlibrary.http.retrofit;

import io.reactivex.disposables.Disposable;

/**
 * RxJavaAction管理接口
 *
 * @author JunChen
 */

public interface RxActionManager<T> {
    /**
     * 添加
     *
     * @param tag
     * @param disposable
     */
    void add(T tag, Disposable disposable);

    /**
     * 移除
     *
     * @param tag
     */
    void remove(T tag);

    /**
     * 取消
     *
     * @param tag
     */
    void cancel(T tag);

}