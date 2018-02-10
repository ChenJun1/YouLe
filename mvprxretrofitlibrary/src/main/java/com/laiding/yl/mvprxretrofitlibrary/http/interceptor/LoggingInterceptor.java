package com.laiding.yl.mvprxretrofitlibrary.http.interceptor;

import com.laiding.yl.mvprxretrofitlibrary.utlis.LogUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by JunChen on 2018/2/8.
 * Remarks
 */

public class LoggingInterceptor implements Interceptor {
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
       LogUtils.d(String.format("发送请求: [%s] %s%n%s",
                request.url(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);
        
        long t2 = System.nanoTime();
        ResponseBody body = response.body();
        LogUtils.d(body.string());
        LogUtils.d(String.format("接收响应: [%s] %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}
