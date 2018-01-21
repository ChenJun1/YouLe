package com.laiding.yl.mvprxretrofitlibrary.http.function;



import com.laiding.yl.mvprxretrofitlibrary.http.exception.ExceptionEngine;
import com.laiding.yl.mvprxretrofitlibrary.utlis.LogUtils;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * http结果处理函数
 *
 * @author JunChen
 */
public class HttpResultFunction<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(@NonNull Throwable throwable) throws Exception {
        //打印具体错误
        LogUtils.e("HttpResultFunction:" + throwable);
        return Observable.error(ExceptionEngine.handleException(throwable));
    }
}
