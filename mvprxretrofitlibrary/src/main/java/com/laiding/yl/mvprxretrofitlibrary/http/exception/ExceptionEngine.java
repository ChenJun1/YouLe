package com.laiding.yl.mvprxretrofitlibrary.http.exception;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.HttpException;


/**
 * 错误/异常处理工具
 *
 * @author JunChen
 */
public class ExceptionEngine {

    public static final int UN_KNOWN_ERROR = 1000;//未知错误
    public static final int ANALYTIC_SERVER_DATA_ERROR = 1001;//解析(服务器)数据错误
    public static final int ANALYTIC_CLIENT_DATA_ERROR = 1002;//解析(客户端)数据错误
    public static final int CONNECT_ERROR = 1003;//网络连接错误
    public static final int TIME_OUT_ERROR = 1004;//网络连接超时

    public static final String X0 = "0";        //成功
    public static final String X1 = "1";  //失败
    public static final String X1001 = "-1001";    //手机号不能为空
    public static final String X1002 = "-1002";    //手机号格式不正确
    public static final String X1003 = "-1003";    //密码不能为空
    public static final String X1004 = "-1004";    //用户不存在
    public static final String X1005 = "-1005";    //密码不正确
    public static final String X1006 = "-1006";    //code只能为数字
    public static final String X1007 = "-1007";    //Token不正确
    public static final String X1008 = "-1008";    //登录失效，重新登陆
    public static final String X1009 = "-1009";    //手机号已注册
    public static final String X1010 = "-1010";    //发送验证码频繁，请稍后重试
    public static final String X1011 = "-1011";    //验证码失效
    public static final String X1012 = "-1012";    //验证码不正确
    public static final String X1013 = "-1013";    //两次密码不一致
    public static final String X1014 = "-1014";    //昵称已占用
    public static final String X1015 = "-1015";    //尚未设置密码

    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException) {             //HTTP错误
            HttpException httpExc = (HttpException) e;
            ex = new ApiException(e, httpExc.code());
            ex.setMsg("网络错误");  //均视为网络错误
            return ex;
        } else if (e instanceof ServerException) {    //服务器返回的错误
            ServerException serverExc = (ServerException) e;
            ex = new ApiException(serverExc, serverExc.getCode());
            ex.setMsg(serverExc.getMsg());
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException || e instanceof MalformedJsonException) {  //解析数据错误
            ex = new ApiException(e, ANALYTIC_SERVER_DATA_ERROR);
            ex.setMsg("解析错误");
            return ex;
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {//连接网络错误
            ex = new ApiException(e, CONNECT_ERROR);
            ex.setMsg("连接失败");
            return ex;
        } else if (e instanceof SocketTimeoutException) {//网络超时
            ex = new ApiException(e, TIME_OUT_ERROR);
            ex.setMsg("网络超时");
            return ex;
        } else {  //未知错误
            ex = new ApiException(e, UN_KNOWN_ERROR);
            ex.setMsg("未知错误");
            return ex;
        }
    }

}
