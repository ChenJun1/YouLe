package com.laiding.yl.youle.Information.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.im.activity.ActivityChat;
import com.sunfusheng.glideimageview.GlideImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/1/23.
 * Remarks 资讯详情
 */

public class ActivityInformationDetail extends MyBaseActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityInformationDetail.class);
        context.startActivity(starter);
    }

    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_bar_right)
    GlideImageView mIvBarRight;
    @BindView(R.id.ll_im_bar_right)
    LinearLayout mLlImBarRight;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_information_detail;
    }

    @Override
    protected void init() {
        setTitle("资讯详情");
        mIvBarRight.loadLocalImage(R.mipmap.icon_zaixiankefu, R.mipmap.icon_zaixiankefu);
        mIvBarRight.setVisibility(View.VISIBLE);
        isBack(true);
    }

    @Override
    protected void initBundleData() {

    }

    @OnClick(R.id.ll_im_bar_right)
    public void onViewClicked() {
        ActivityChat.start(mContext,"8009");
    }
}
