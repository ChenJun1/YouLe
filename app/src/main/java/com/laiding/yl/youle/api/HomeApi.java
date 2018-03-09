package com.laiding.yl.youle.api;

import android.icu.util.Output;

import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.youle.home.entity.MedicalRecordsBean;
import com.laiding.yl.youle.home.entity.PregnancyBean;
import com.laiding.yl.youle.login.entity.UserBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

/**
 * Created by JunChen on 2018/2/26.
 * Remarks
 */

public interface HomeApi {
    /**
     * 首页备孕详情
     * @param request
     * @return
     */
    @FormUrlEncoded
    @GET("?r=index/pregnant_info")
    Observable<HttpResponse<UserBean>> getPregnantDetail(@QueryMap Map<String, Object> request);

    /**
     * 备孕列表
     * @param request
     * @return
     */
    @GET("?r=index/pregnant")
    Observable<HttpResponse<List<PregnancyBean>>> getPregnantList(@QueryMap Map<String, Object> request);

    /**
     * 诊疗记录列表
     * @return
     */
    @FormUrlEncoded
    @POST("?r=index/record_list")
    Observable<HttpResponse<List<MedicalRecordsBean>>> getRecordList(@FieldMap Map<String, Object> request);

    /**
     * 诊疗记录详情
     * @return
     */
    @GET("?r=index/record_info")
    Observable<HttpResponse<MedicalRecordsBean>> getRecordDetai(@QueryMap Map<String, Object> request);

    /**
     * 诊疗记录添加
     * @return
     */
    @Multipart
    @POST("?r=index/record")
    Observable<HttpResponse> getAddRecord(@PartMap Map<String, RequestBody> request, @Part List<MultipartBody.Part> parts);

}
