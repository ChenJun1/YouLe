package com.laiding.yl.mvprxretrofitlibrary.http.function;

import android.text.Html;

import com.laiding.yl.mvprxretrofitlibrary.http.exception.ServerException;
import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.mvprxretrofitlibrary.utlis.LogUtils;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 服务器结果处理函数
 *
 * @author JunChen
 */
public class ServerResultFunction<T> implements Function<HttpResponse<T>, HttpResponse<T>> {
    @Override
    public HttpResponse<T> apply(@NonNull HttpResponse<T> response) throws Exception {
        //打印服务器回传结果
        LogUtils.i(response.toString());

        if (!response.isSuccess()) {
            throw new ServerException(response.getCode(), response.getMsg());
        }
        return response;
    }
}
