package com.laiding.yl.youle.service;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.laiding.yl.youle.BuildConfig;
import com.laiding.yl.youle.im.DemoHelper;
import com.laiding.yl.youle.login.entity.DaoMaster;
import com.laiding.yl.youle.login.entity.DaoSession;
import com.umeng.socialize.UMShareAPI;
import com.vondear.rxtools.RxTool;

import java.util.Iterator;
import java.util.List;

/**
 * Created by JunChen on 2018/3/16.
 * Remarks
 */

public class ServiceAppInit extends IntentService{

    private static final String ACTION_INIT_WHEN_APP_CREATE = "com.laiding.youle.service.action.INIT";

    public static void start(Context context) {
        Intent starter = new Intent(context, ServiceAppInit.class);
        starter.setAction(ACTION_INIT_WHEN_APP_CREATE);
        context.startService(starter);
    }
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param
     */
    public ServiceAppInit() {
        super("MYSERVICE");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT_WHEN_APP_CREATE.equals(action)) {
                performInit();
            }
        }
    }

    private void performInit(){
        RxTool.init(this);
        UMShareAPI.get(this);//初始化sdk
        initIM();
        setupDatabase();
    }

    private void initIM() {
        //init demo helper
        DemoHelper.getInstance().init(this);

        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments(true);
        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true);

        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回

        if (processAppName == null || !processAppName.equalsIgnoreCase(this.getPackageName())) {
            Log.e("APPLICATION", "enter the ServiceAppInit process!");

            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }

        //初始化
        EMClient.getInstance().init(this, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG);
    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }

    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //创建数据库
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "youle.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
    }


}
