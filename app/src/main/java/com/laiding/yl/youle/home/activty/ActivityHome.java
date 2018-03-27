package com.laiding.yl.youle.home.activty;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.runtimepermissions.PermissionsManager;
import com.hyphenate.easeui.runtimepermissions.PermissionsResultAction;
import com.laiding.yl.mvprxretrofitlibrary.manager.ActivityStackManager;
import com.laiding.yl.mvprxretrofitlibrary.utlis.LogUtils;
import com.laiding.yl.youle.MyApplication;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseFragmentActivity;
import com.laiding.yl.youle.home.adapter.AdapterHomeViewPage;
import com.laiding.yl.youle.im.activity.ActivityChat;
import com.laiding.yl.youle.login.activity.ActivityLauncher;
import com.laiding.yl.youle.widget.cookie.CookieBar;
import com.vondear.rxtools.view.RxToast;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.util.List;

import butterknife.BindView;

/**
 * Created by JunChen on 2018/1/3.
 * 首页
 */

public class ActivityHome extends MyBaseFragmentActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityHome.class);
        context.startActivity(starter);
    }

    @BindView(R.id.home_vp)
    ViewPager homeVp;
    @BindView(R.id.alphaIndicator)
    AlphaTabsIndicator alphaIndicator;
    //双击返回键 退出
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    private boolean isAdvisory = false;
    @Override
    protected int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected void init() {
        initViewPage();
        requestPermissions();
    }

    @Override
    protected void onResume() {
        if (EMClient.getInstance() != null) {
            if(EMClient.getInstance().chatManager()!=null) {
                EMClient.getInstance().chatManager().addMessageListener(messageListener);
            }
        }
        super.onResume();
        if(null!=ActivityLauncher.sActivityLauncher){
            ActivityLauncher.sActivityLauncher.finish();
            ActivityLauncher.sActivityLauncher=null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (EMClient.getInstance() != null) {
            if(EMClient.getInstance().chatManager()!=null) {
                EMClient.getInstance().chatManager().removeMessageListener(messageListener);
            }
        }
        super.onDestroy();
    }

    EMMessageListener messageListener=new EMMessageListener() {
        @Override
        public void onMessageReceived(List<EMMessage> list) {

            refreshUIWithMessage(list);
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {}

        @Override
        public void onMessageRead(List<EMMessage> list) {}

        @Override
        public void onMessageDelivered(List<EMMessage> list) {}

        @Override
        public void onMessageRecalled(List<EMMessage> list) {refreshUIWithMessage(list); }

        @Override
        public void onMessageChanged(EMMessage emMessage, Object o) { LogUtils.i("");}
    };

    private void refreshUIWithMessage(List<EMMessage> list) {
        runOnUiThread(() -> {
            // refresh unread count
            updateUnreadLabel(list);
        });
    }

    private void updateUnreadLabel(List<EMMessage> list) {
        isAdvisory = true;
        showNotifiCation(list);
    }

    private void initViewPage() {
        homeVp.setAdapter(new AdapterHomeViewPage(getSupportFragmentManager()));
        homeVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                if (0 == position) {
//                    alphaIndicator.getTabView(0).showNumber(alphaIndicator.getTabView(0).getBadgeNumber() - 1);
                }else if(1==position){
                }else if (2 == position) {
//                    alphaIndicator.getTabView(2).removeShow();
                    if(isAdvisory) {
                        ActivityChat.start(mContext,"19","","");
                        isAdvisory = false;
                    }
                } else if (3 == position) {
//                    alphaIndicator.removeAllBadge();
                } else if (4 == position) {
//                    alphaIndicator.removeAllBadge();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        alphaIndicator.setViewPager(homeVp);
//        alphaIndicator.getTabView(0).showNumber(6);
//        alphaIndicator.getTabView(1).showNumber(888);
//        alphaIndicator.getTabView(2).showNumber(88);
//        alphaIndicator.getTabView(3).showPoint();
    }

    @Override
    protected void initBundleData() {

    }
    @TargetApi(23)
    private void requestPermissions() {
        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this, new PermissionsResultAction() {
            @Override
            public void onGranted() {
//                Toast.makeText(ActivityHome.this, "All permissions have been granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDenied(String permission) {
//                Toast.makeText(ActivityHome.this, "Permission " + permission + " has been denied", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            ActivityStackManager.getManager().finishAllActivity();
            ActivityStackManager.getManager().exitApp(MyApplication.app);
            super.onBackPressed();
            return;
        } else {
            RxToast.normal("再次点击返回键退出", Toast.LENGTH_SHORT);
        }
        mBackPressed = System.currentTimeMillis();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }
}
