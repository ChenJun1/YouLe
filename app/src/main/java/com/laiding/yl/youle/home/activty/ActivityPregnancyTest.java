package com.laiding.yl.youle.home.activty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.hyphenate.chat.EMClient;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseFragmentActivity;
import com.laiding.yl.youle.home.adapter.AdapterPregnancyViewPage;
import com.laiding.yl.youle.im.activity.ActivityChat;
import com.sunfusheng.glideimageview.GlideImageView;
import com.vondear.rxtools.view.RxToast;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/1/21.
 * Remarks 备孕检查
 */

public class ActivityPregnancyTest extends MyBaseFragmentActivity {
    @BindView(R.id.iv_bar_right)
    GlideImageView mIvBarRight;
    @BindView(R.id.ll_im_bar_right)
    LinearLayout mLlImBarRight;
    @BindView(R.id.alphaIndicator)
    AlphaTabsIndicator mAlphaIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityPregnancyTest.class);
        context.startActivity(starter);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_pregnancy_test;
    }

    @Override
    protected void init() {
        setTitle("备孕检查");
        isBack(true);
        mLlImBarRight.setVisibility(View.VISIBLE);
        setRightImag(mIvBarRight, R.mipmap.icon_zaixiankefu);
        initViewPage();
    }

    @Override
    protected void initBundleData() {

    }

    private void initViewPage() {
        mViewPager.setAdapter(new AdapterPregnancyViewPage(getSupportFragmentManager()));
        mAlphaIndicator.setViewPager(mViewPager);

    }

    @OnClick(R.id.ll_im_bar_right)
    public void onViewClicked() {
        if(EMClient.getInstance().isConnected()){
            ActivityChat.start(mContext, "8899","备孕","s撒大大的事发生的纠纷你上雕刻技法能看到");
        }else{
            RxToast.error("客服未连接");
        }
    }
}
