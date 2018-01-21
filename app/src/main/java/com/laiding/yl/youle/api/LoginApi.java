package com.laiding.yl.youle.api;

import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.youle.login.entity.UserBean;


import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by JunChen on 2018/1/3.
 */

public interface LoginApi {
    @GET("user/login")
    Observable<HttpResponse<UserBean>> login(@QueryMap Map<String, Object> request);
}
