package com.laiding.yl.youle.clinic.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.sunfusheng.glideimageview.GlideImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/1/26.
 * Remarks 诊所详情
 */

public class ActivityClinicDetail extends MyBaseActivity {

    @BindView(R.id.giv_pic)
    GlideImageView mGivPic;
    @BindView(R.id.tv_photo)
    TextView mTvPhoto;
    @BindView(R.id.btn_call)
    Button mBtnCall;
    @BindView(R.id.tv_local)
    TextView mTvLocal;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.giv_jiaotou)
    GlideImageView mGivJiaotou;
    @BindView(R.id.tv_more)
    TextView mTvMore;
    @BindView(R.id.rcy_view)
    RecyclerView mRcyView;

    private boolean flag=true;

    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityClinicDetail.class);
        context.startActivity(starter);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_clinic_detail;
    }

    @Override
    protected void init() {
            setTitle("医院详情");
            isBack(true);
    }

    @Override
    protected void initBundleData() {

    }


    @OnClick({R.id.btn_call, R.id.giv_jiaotou, R.id.tv_more, R.id.rcy_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_call:
                break;
            case R.id.giv_jiaotou:
                if(flag){
                    flag=false;
                    mTvContent.setMaxLines(100);
                    mGivJiaotou.loadLocalImage(R.mipmap.icon_shangjiantou11, R.mipmap.icon_shangjiantou11);
                }else{
                    flag=true;
                    mTvContent.setMaxLines(4);
                    mGivJiaotou.loadLocalImage(R.mipmap.icon_xiajiantou11, R.mipmap.icon_xiajiantou11);
                }

                break;
            case R.id.tv_more:
                break;
            case R.id.rcy_view:
                break;
        }
    }
}
