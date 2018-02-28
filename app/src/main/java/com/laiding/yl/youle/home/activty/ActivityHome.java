package com.laiding.yl.youle.home.activty;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.laiding.yl.mvprxretrofitlibrary.manager.ActivityStackManager;
import com.laiding.yl.youle.MyApplication;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseFragmentActivity;
import com.laiding.yl.youle.home.adapter.AdapterHomeViewPage;
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
    //双击返回键 退出
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    @Override
    protected int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected void init() {

        initViewPage();
        //登录成功后储存
        /*User user=new User("1","2222","天气","1382838238");
        User user1=new User("1","33333","天气1","13828138238");
        UserDaoUtil daoUtil=new UserDaoUtil(mContext);
        daoUtil.insertUser(user);
        daoUtil.insertUser(user1);
        daoUtil.deleteAll();
        List<User> users = daoUtil.queryAllUser();
        users.size();*/


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
                    alphaIndicator.getTabView(0).showNumber(alphaIndicator.getTabView(0).getBadgeNumber() - 1);
                }else if(1==position){
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


    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            ActivityStackManager.getManager().finishAllActivity();
            ActivityStackManager.getManager().exitApp(MyApplication.app);
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "再次点击返回键退出", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }

}
