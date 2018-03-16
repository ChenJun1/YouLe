package com.laiding.yl.youle.clinic.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.clinic.activity.view.IDoctorDetail;
import com.laiding.yl.youle.clinic.entity.DoctorBean;
import com.laiding.yl.youle.clinic.presenter.PresenterDoctorDetail;
import com.laiding.yl.youle.utils.MConstant;
import com.sunfusheng.glideimageview.GlideImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JunChen on 2018/1/26.
 * Remarks医生详情
 */

public class ActivityDoctorDetail extends MyBaseActivity implements IDoctorDetail {
    @BindView(R.id.giv_avatar)
    GlideImageView mGivAvatar;
    @BindView(R.id.name_tv)
    TextView mNameTv;
    @BindView(R.id.rom_duties_tv)
    TextView mRomDutiesTv;
    @BindView(R.id.address_tv)
    TextView mAddressTv;
    @BindView(R.id.info_tv)
    TextView mInfoTv;
    @BindView(R.id.expert_tv)
    TextView mExpertTv;

    public static void start(Context context, String dId) {
        Intent starter = new Intent(context, ActivityDoctorDetail.class);
        starter.putExtra("ID", dId);
        context.startActivity(starter);
    }

    private String dId;
    private PresenterDoctorDetail presenter = new PresenterDoctorDetail(this, this);

    @Override
    protected int getContentViewId() {
        return R.layout.activity_doctor_detail;
    }

    @Override
    protected void init() {
        setTitle("医生详情");
        isBack(true);
        presenter.requestClinicDetail();
    }

    @Override
    protected void initBundleData() {
        dId = getIntent().getStringExtra("ID");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showResult(DoctorBean bean) {
        if (bean != null) {
            mGivAvatar.loadCircleImage(bean.getD_file(), R.mipmap.ic_launcher_round);
            mNameTv.setText(bean.getD_name());
            mRomDutiesTv.setText(bean.getD_room() + "  " + bean.getD_duties());
            mAddressTv.setText(bean.getH_name());
            mInfoTv.setText(Html.fromHtml(bean.getD_info()==null?"":bean.getD_info()));
            mExpertTv.setText(bean.getD_expert());
        }
    }

    @Override
    public String getDId() {
        return dId;
    }

}
