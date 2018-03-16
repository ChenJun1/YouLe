package com.laiding.yl.youle;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.laiding.yl.mvprxretrofitlibrary.utlis.LogUtils;
import com.laiding.yl.youle.im.DemoHelper;
import com.laiding.yl.youle.login.entity.DaoMaster;
import com.laiding.yl.youle.login.entity.DaoSession;
import com.laiding.yl.youle.service.ServiceAppInit;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.vondear.rxtools.RxAppTool;
import com.vondear.rxtools.RxTool;

import java.util.Iterator;
import java.util.List;

/**
 * Created by JunChen on 2018/1/3.
 */
public class MyApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    public  static Context app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        Config.DEBUG = BuildConfig.DEBUG;
        ServiceAppInit.start(this);
        if (BuildConfig.DEBUG) {
            initLog();
            LogUtils.openLog(BuildConfig.DEBUG);
        }
    }

    /**
     * 配置日志输出插件
     */
    private void initLog() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .methodOffset(7)        // （可选）隐藏内部方法调用到偏移量。 默认5
                .tag("YOU")   //（可选）每个日志的全局标记。 默认PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }

    //各个平台的配置
    {
        //微信
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //新浪微博(第三个参数为回调地址)
        PlatformConfig.setSinaWeibo("2969732329", "6ce849c5ac6f0fd6f9d225eff590ec97","http://sns.whalecloud.com/sina2/callback");
        //QQ
        PlatformConfig.setQQZone("1106685755", "9TW7I4vJ8IlRfZ7P");
    }
}
