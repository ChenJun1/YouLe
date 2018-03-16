package com.laiding.yl.youle.login.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.home.activty.ActivityHome;
import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.RxBarTool;
import com.vondear.rxtools.view.RxToast;

/**
 * Created by JunChen on 2018/3/15.
 * Remarks 启动页
 */

public class ActivityLauncher extends AppCompatActivity{
    protected void init() {

        new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000);
                    toMain();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBarTool.hideStatusBar(this);
        setContentView(R.layout.activity_launcher);
        init();
    }

    public void toMain() {
        RxActivityTool.skipActivityAndFinish(this, ActivityHome.class);
    }
}
