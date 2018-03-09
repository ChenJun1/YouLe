package com.laiding.yl.youle.api;

import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.HttpResponse;
import com.laiding.yl.youle.clinic.entity.ClinicBean;
import com.laiding.yl.youle.clinic.entity.ClinicDetailBean;
import com.laiding.yl.youle.clinic.entity.DoctorBean;
import com.laiding.yl.youle.login.entity.UserBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by JunChen on 2018/2/26.
 * Remarks
 */

public interface ClinicApi {
    /**
     * 医院详情和团队
     * @param request
     * @return
     */
    @GET("?r=hospital/hospital_info")
    Observable<HttpResponse<ClinicDetailBean>> getHospitalDetial(@QueryMap Map<String, Object> request);

    /**
     * 医院列表
     * @param request
     * @return
     */
    @FormUrlEncoded
    @POST("?r=hospital/hospital")
    Observable<HttpResponse<ClinicBean>> getHospitalList(@FieldMap Map<String, Object> request);

    /**
     * 医生详情
     * @param request
     * @return
     */
    @GET("?r=hospital/doctor_info")
    Observable<HttpResponse<DoctorBean>> getDoctorsDetail(@QueryMap Map<String, Object> request);
}

