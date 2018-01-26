package com.laiding.yl.youle.home.activty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.widget.photopicker.widget.BGASortableNinePhotoLayout;

import butterknife.BindView;

/**
 * Created by JunChen on 2018/1/26.
 * Remarks
 */

public class ActivityMedicalRecordsDetail extends MyBaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityMedicalRecordsDetail.class);
        context.startActivity(starter);
    }

    @BindView(R.id.ll_im_bar_right)
    LinearLayout mLlImBarRight;
    @BindView(R.id.et_mr_title)
    EditText mEtMrTitle;
    @BindView(R.id.tv_mr_time)
    TextView mTvMrTime;
    @BindView(R.id.tv_hospital)
    TextView mTvHospital;
    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.snpl_photos)
    BGASortableNinePhotoLayout mSnplPhotos;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_medical_records_detai;
    }

    @Override
    protected void init() {
        setTitle("详情");
        isBack(true);
    }

    @Override
    protected void initBundleData() {

    }

}
