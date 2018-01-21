package com.laiding.yl.mvprxretrofitlibrary.utlis;

import android.util.Log;

import com.orhanobut.logger.Logger;


/**
 * LOG工具类
 *
 * @author JunChen
 */
public class LogUtils {

    private static boolean allowD = true;
    private static boolean allowE = true;
    private static boolean allowI = true;
    private static boolean allowV = true;
    private static boolean allowW = true;

    private LogUtils() {
    }

    /**
     * 开启Log
     *
     * @author ZhongDaFeng
     */
    public static void openLog(boolean flag) {
        allowD = flag;
        allowE = flag;
        allowI = flag;
        allowV = flag;
        allowW = flag;
    }

    public static void d(Object content) {
        if (!allowD)
            return;
        Logger.d(content);
    }

    public static void e(String content) {
        if (!allowE)
            return;
        Logger.e(content);
    }

    public static void i(String content) {
        if (!allowI)
            return;
        Logger.i(content);
    }

    public static void v(String content) {
        if (!allowV)
            return;
        Logger.v(content);
    }

    public static void w(String content) {
        if (!allowW)
            return;
        Logger.w(content);
    }
    public static void json(String content) {
        if (!allowD)
            return;
        Logger.json(content);
    }

    public static void xml(String content) {
        if (!allowD)
            return;
        Logger.xml(content);
    }

}
