package com.laiding.yl.mvprxretrofitlibrary.http.retrofit;

import java.util.HashMap;
import java.util.Map;

/**
 * 构建Api请求参数
 *
 * @author JunChen
 */

public class HttpRequest {

    public static final String appKey = "1889b37351288";


    /**
     * 获取基础request
     *
     * @author JunChen
     */
    public static final Map<String, Object> getRequest() {
        Map<String, Object> map = new HashMap<>();
//        map.put("token", appKey);
        return map;
    }


}
