package com.laiding.yl.youle.api;

import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.RetrofitUtils;

/**
 * Created by JunChen on 2018/1/3.
 */

public class ApiUtlis {
    public static UserApi sUserApi;
    public static HomeApi sHomeApi;
    public static ClinicApi sClinicApi;
    public static CommunityApi sCommunityApi;

    public static UserApi getUserApi(){
        if(sUserApi==null){
            sUserApi = RetrofitUtils.get().retrofit().create(UserApi.class);
        }
        return sUserApi;
    }

    public static HomeApi getHomeApi(){
        if(sHomeApi==null){
            sHomeApi = RetrofitUtils.get().retrofit().create(HomeApi.class);
        }
        return sHomeApi;
    }

    public static ClinicApi getClinicApi(){
        if(sClinicApi==null){
            sClinicApi = RetrofitUtils.get().retrofit().create(ClinicApi.class);
        }
        return sClinicApi;
    }

    public static CommunityApi getCommunityApi(){
        if(sCommunityApi==null){
            sCommunityApi = RetrofitUtils.get().retrofit().create(CommunityApi.class);
        }
        return sCommunityApi;
    }

}
