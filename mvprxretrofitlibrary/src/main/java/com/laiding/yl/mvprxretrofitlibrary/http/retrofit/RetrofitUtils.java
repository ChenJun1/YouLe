package com.laiding.yl.mvprxretrofitlibrary.http.retrofit;


import com.laiding.yl.mvprxretrofitlibrary.http.interceptor.LoggingInterceptor;
import com.laiding.yl.mvprxretrofitlibrary.utlis.LogUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitUtils工具类
 *
 * @author JunChen
 */
public class RetrofitUtils {
    /**
     * 接口地址
     */
    public static final String BASE_API = "http://47.97.185.141/youle/frontend/web/";
    public static final int CONNECT_TIME_OUT = 15;//连接超时时长x秒
    public static final int READ_TIME_OUT = 15;//读数据超时时长x秒
    public static final int WRITE_TIME_OUT = 15;//写数据接超时时长x秒
    private static RetrofitUtils mInstance = null;

    private RetrofitUtils() {
    }

    public static RetrofitUtils get() {
        if (mInstance == null) {
            synchronized (RetrofitUtils.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 设置okHttp
     *
     * @author JunChen
     */
    private static OkHttpClient okHttpClient() {
        //开启Log
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtils.d("okHttp:" + message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .build();
        return client;
    }

    /**
     * 获取Retrofit
     *
     * @author Chenjun
     */
    public Retrofit retrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient())
                .baseUrl(BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

}
