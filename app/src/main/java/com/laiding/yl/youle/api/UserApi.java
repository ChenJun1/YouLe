package com.laiding.yl.youle.api;

import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.youle.login.entity.UserBean;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.FormBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by JunChen on 2018/1/3.
 */

public interface UserApi {
    @GET("user/login")
    Observable<HttpResponse<UserBean>> login(@QueryMap Map<String, Object> request);

    @FormUrlEncoded
    @POST("?r=user/tel")
    Observable<HttpResponse>  getVerificationCode(@FieldMap Map<String, Object> request);


}
