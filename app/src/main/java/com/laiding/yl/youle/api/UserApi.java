package com.laiding.yl.youle.api;

import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.youle.login.entity.User;
import com.laiding.yl.youle.login.entity.UserBean;
import com.laiding.yl.youle.mine.entity.UserInfo;


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
    String url = "index.php";

    /**
     * 密码登录
     * @param request
     * @return
     */
    @FormUrlEncoded
    @GET("?r=user/login")
    Observable<HttpResponse<UserBean>> login(@QueryMap Map<String, Object> request);

    /**
     * 密码登录
     * @param request
     * @return
     */
    @FormUrlEncoded
    @POST(url+"?r=user/login")
    Observable<HttpResponse<User>> passWordLogin(@FieldMap Map<String, Object> request);

    /**
     * 验证码登录
     * @param request
     * @return
     */
    @FormUrlEncoded
    @POST(url+"?r=user/register")
    Observable<HttpResponse<User>> codeLogin(@FieldMap Map<String, Object> request);

    /**
     * 获取验证码
     * @param request
     * @return
     */
    @FormUrlEncoded
    @POST(url+"?r=user/tel")
    Observable<HttpResponse>  getVerificationCode(@FieldMap Map<String, Object> request);

    /**
     * 完善个人信息
     * @param request
     * @return
     */
    @FormUrlEncoded
    @POST("?r=user/user_update")
    Observable<HttpResponse>  userUpdate(@FieldMap Map<String, Object> request);

    /**
     *  设置密码
     * @param request
     * @return
     */
    @FormUrlEncoded
    @POST("?r=user/pwd")
    Observable<HttpResponse> setPwd(@FieldMap Map<String, Object> request);

    /**
     *  修改手机号
     * @param request
     * @return
     */
    @FormUrlEncoded
    @POST("?r=user/update_tel")
    Observable<HttpResponse> updatePhone(@FieldMap Map<String, Object> request);

    /**
     *  用户退出
     * @param request
     * @return
     */
    @FormUrlEncoded
    @POST("?r=user/out")
    Observable<HttpResponse> loginOut(@FieldMap Map<String, Object> request);

    /**
     *  修改密码
     * @param request
     * @return
     */
    @FormUrlEncoded
    @POST("?r=user/update_pwd")
    Observable<HttpResponse> updatePwd(@FieldMap Map<String, Object> request);

    /**
     *  用户注册
     * @param request
     * @return
     */
    @FormUrlEncoded
    @POST("?r=user/register")
    Observable<HttpResponse> userRegister(@FieldMap Map<String, Object> request);

    /**
     *  个人信息
     * @param request
     * @return
     */
    @FormUrlEncoded
    @POST(url+"?r=user/user")
    Observable<HttpResponse<UserInfo>> getUserInfo(@FieldMap Map<String, Object> request);
}
