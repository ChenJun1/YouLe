package com.laiding.yl.youle.home.activty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.home.activty.view.IMedicalRecordsActy;
import com.laiding.yl.youle.home.activty.view.IMedicalRecordsDetailActy;
import com.laiding.yl.youle.home.entity.MedicalRecordsBean;
import com.laiding.yl.youle.home.presenter.PresenterMedicalRecordsDetail;
import com.laiding.yl.youle.widget.photopicker.widget.BGASortableNinePhotoLayout;

import butterknife.BindView;

/**
 * Created by JunChen on 2018/1/26.
 * Remarks 诊疗详情
 */

public class ActivityMedicalRecordsDetail extends MyBaseActivity implements IMedicalRecordsDetailActy {

public static void start(Context context,int rid) {
    Intent starter = new Intent(context, ActivityMedicalRecordsDetail.class);
    starter.putExtra("ID",rid);
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

    private int Rid=0;
    private PresenterMedicalRecordsDetail presenter=new PresenterMedicalRecordsDetail(this,this);

    @Override
    protected int getContentViewId() {
        return R.layout.activity_medical_records_detai;
    }

    @Override
    protected void init() {
        setTitle("详情");
        isBack(true);
        presenter.requestHttp();
    }

    @Override
    protected void initBundleData() {
        Rid = getIntent().getIntExtra("ID", 0);
    }

    @Override
    public void showResult(MedicalRecordsBean bean) {
            if(bean==null)
                return;
        mEtMrTitle.setText(bean.getR_project());
        mTvMrTime.setText(bean.getTime());
        mTvHospital.setText(bean.getR_hospital());
        mEtContent.setText(bean.getR_content());
    }

    @Override
    public int getRID() {
        return Rid;
    }
}
