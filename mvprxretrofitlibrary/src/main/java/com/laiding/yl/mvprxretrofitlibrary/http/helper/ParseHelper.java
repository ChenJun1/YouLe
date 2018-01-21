package com.laiding.yl.mvprxretrofitlibrary.http.helper;

import com.google.gson.JsonElement;

/**
 * 数据解析helper
 *
 * @author JunChen
 */
public interface ParseHelper {
    Object[] parse(JsonElement jsonElement);
}
