package com.laiding.yl.mvprxretrofitlibrary.http.Api;


import com.laiding.yl.mvprxretrofitlibrary.http.retrofit.RetrofitUtils;

/**
 * 接口工具类
 *
 * @author JunChen
 */

public class ApiUtils {


    private static PhoneApi phoneApi;
    private static UserApi userApi;

    public static PhoneApi getPhoneApi() {
        if (phoneApi == null) {
            phoneApi = RetrofitUtils.get().retrofit().create(PhoneApi.class);
        }
        return phoneApi;
    }

    public static UserApi getUserApi() {
        if (userApi == null) {
            userApi = RetrofitUtils.get().retrofit().create(UserApi.class);
        }
        return userApi;
    }

}
