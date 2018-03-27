package com.laiding.yl.youle.api;

import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.youle.login.entity.User;
import com.laiding.yl.youle.login.entity.UserBean;
import com.laiding.yl.youle.mine.entity.CommonToolBean;
import com.laiding.yl.youle.mine.entity.UserInfo;


import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

/**
 * Created by JunChen on 2018/1/3.
 */

public interface UserApi {

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
    @POST("?r=user/login")
    Observable<HttpResponse<User>> passWordLogin(@FieldMap Map<String, Object> request);

    /**
     * 验证码登录
     * @param request
     * @return
     */
    @FormUrlEncoded
    @POST("?r=user/register")
    Observable<HttpResponse<User>> codeLogin(@FieldMap Map<String, Object> request);

    /**
     * 获取验证码
     * @param request
     * @return
     */
    @FormUrlEncoded
    @POST("?r=user/tel")
    Observable<HttpResponse>  getVerificationCode(@FieldMap Map<String, Object> request);

    /**
     * 完善个人信息
     * @param request
     * @return
     */
    @Multipart
    @POST("?r=user/user_update")
    Observable<HttpResponse<UserInfo>>  userUpdate(@PartMap Map<String, RequestBody> request, @Part MultipartBody.Part file);

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
    @GET("?r=user/out")
    Observable<HttpResponse> loginOut(@QueryMap Map<String, Object> request);

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
    @POST("?r=user/user")
    Observable<HttpResponse<UserInfo>> getUserInfo(@FieldMap Map<String, Object> request);

    /**
     *  忘记密码
     * @param request
     * @return
     */
    @FormUrlEncoded
    @POST("?r=user/forget_pwd")
    Observable<HttpResponse> forgetPwd(@FieldMap Map<String, Object> request);

    /**
     *  常用工具
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("?r=assistant/assistant_info")
    Observable<HttpResponse<List<CommonToolBean>>> getAssistantInfo(@FieldMap Map<String, Object> request);
}
