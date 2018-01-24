package com.laiding.yl.youle.home.activty;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseFragmentActivity;
import com.laiding.yl.youle.home.adapter.AdapterPregnancyViewPage;
import com.sunfusheng.glideimageview.GlideImageView;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import butterknife.BindView;

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
}
