package com.laiding.yl.youle.home.activty;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseFragmentActivity;
import com.laiding.yl.youle.home.adapter.ContentFragmentPageAdapter;
import com.laiding.yl.youle.home.entity.ContentPageItem;
import com.laiding.yl.youle.home.presenter.PresenterMedicalRecordsDetail;
import com.laiding.yl.youle.widget.slidingview.CustomSlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by JunChen on 2018/2/23.
 * Remarks 备孕检查详情
 */

public class ActivityPregnancyDetail extends MyBaseFragmentActivity {


    @BindView(R.id.sliding_tabs)
    CustomSlidingTabLayout mSlidingTabs;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityPregnancyDetail.class);
        context.startActivity(starter);
    }
    private List<ContentPageItem> mTabs;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_pregnancy_detail;
    }

    @Override
    protected void init() {
        setTitle("备孕详情");
        isBack(true);
        initViewPage();
    }

    private void initViewPage() {
        mTabs = new ArrayList<>();
        String[] strs = {"服务介绍","预约说明"};
        for(int i = 0 ; i < strs.length ; i++){
            mTabs.add(new ContentPageItem(
                    strs[i], // Title
                    getResources().getColor(R.color.colorPrimaryDark), // Indicator color
                    Color.TRANSPARENT // Divider color
            ));
        }
        mViewpager.setAdapter(new ContentFragmentPageAdapter(getSupportFragmentManager(), mTabs));
        mSlidingTabs.setShouldExpand(true);
        mSlidingTabs.setViewPager(mViewpager);
        mSlidingTabs.setCustomTabColorizer(new CustomSlidingTabLayout.TabColorizer() {

            @Override
            public int getIndicatorColor(int position) {
                return mTabs.get(position).getIndicatorColor();
            }

            @Override
            public int getDividerColor(int position) {
                return mTabs.get(position).getDividerColor();
            }

        });
    }

    @Override
    protected void initBundleData() {

    }

}
