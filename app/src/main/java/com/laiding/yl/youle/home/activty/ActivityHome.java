package com.laiding.yl.youle.home.activty;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

import com.laiding.yl.mvprxretrofitlibrary.base.BaseFragmentActivity;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseFragmentActivity;
import com.laiding.yl.youle.home.adapter.AdapterHomeViewPage;
import com.laiding.yl.youle.runtimepermissions.PermissionsManager;
import com.laiding.yl.youle.runtimepermissions.PermissionsResultAction;
import com.yinglan.alphatabs.AlphaTabsIndicator;

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

    @Override
    protected int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected void init() {
        requestPermissions();
        initViewPage();
    }

    private void initViewPage() {
        homeVp.setAdapter(new AdapterHomeViewPage(getSupportFragmentManager()));
        homeVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (0 == position) {
                    setTitle("");
                    alphaIndicator.getTabView(0).showNumber(alphaIndicator.getTabView(0).getBadgeNumber() - 1);
                }else if(1==position){
                    setTitle("问诊");
                }else if (2 == position) {
                    alphaIndicator.getCurrentItemView().removeShow();
                } else if (3 == position) {
                    alphaIndicator.removeAllBadge();
                } else if (4 == position) {
                    alphaIndicator.removeAllBadge();
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
//				Toast.makeText(MainActivity.this, "All permissions have been granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDenied(String permission) {
                //Toast.makeText(MainActivity.this, "Permission " + permission + " has been denied", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }

}
