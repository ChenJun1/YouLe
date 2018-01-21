package com.laiding.yl.youle.api;

import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.RetrofitUtils;

/**
 * Created by JunChen on 2018/1/3.
 */

public class ApiUtlis {
    public static LoginApi loginApi;

    public static LoginApi getLoginApi(){
        if(loginApi==null){
            loginApi = RetrofitUtils.get().retrofit().create(LoginApi.class);
        }
        return loginApi;
    }
}
