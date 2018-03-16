package com.laiding.yl.youle.clinic.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.clinic.activity.view.IClinicDetail;
import com.laiding.yl.youle.clinic.adapter.AdapterClinicDetail;
import com.laiding.yl.youle.clinic.entity.ClinicDetailBean;
import com.laiding.yl.youle.clinic.presenter.PresenterClinicDetail;
import com.laiding.yl.youle.utils.MConstant;
import com.sunfusheng.glideimageview.GlideImageView;
import com.vondear.rxtools.RxDeviceTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/1/26.
 * Remarks 诊所详情
 */

public class ActivityClinicDetail extends MyBaseActivity implements IClinicDetail {

    @BindView(R.id.giv_pic)
    GlideImageView mGivPic;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
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
    @BindView(R.id.tv_clinic_title)
    TextView mTvClinicTitle;


    public static void start(Context context, String ClinicId) {
        Intent starter = new Intent(context, ActivityClinicDetail.class);
        starter.putExtra("ID", ClinicId);
        context.startActivity(starter);
    }

    private boolean flag = true; //展开收缩箭头标记
    private ArrayList<ClinicDetailBean.DoctorListBean> mList = new ArrayList<>();
    private AdapterClinicDetail adapter;
    private PresenterClinicDetail presenter = new PresenterClinicDetail(this, this);

    private String cilincId;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_clinic_detail;
    }

    @Override
    protected void init() {
        setTitle("医院详情");
        isBack(true);
        initRcView();
        presenter.requestClinicDetail();
    }

    private void initRcView() {
        adapter = new AdapterClinicDetail(mList);
        LinearLayoutManager ms = new LinearLayoutManager(mContext);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRcyView.setLayoutManager(ms);
        mRcyView.setAdapter(adapter);
        mRcyView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ClinicDetailBean.DoctorListBean item = (ClinicDetailBean.DoctorListBean) adapter.getItem(position);
                ActivityDoctorDetail.start(mContext, item.getD_id());
            }
        });
    }

    @Override
    protected void initBundleData() {
        cilincId = getIntent().getStringExtra("ID");
    }


    @OnClick({R.id.btn_call, R.id.giv_jiaotou, R.id.tv_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_call:
                RxDeviceTool.dial(mContext, mTvPhone.getText().toString());
                break;
            case R.id.giv_jiaotou:
                if (flag) {
                    flag = false;
                    mTvContent.setMaxLines(100);
                    mGivJiaotou.loadLocalImage(R.mipmap.icon_shangjiantou11, R.mipmap.icon_shangjiantou11);
                } else {
                    flag = true;
                    mTvContent.setMaxLines(4);
                    mGivJiaotou.loadLocalImage(R.mipmap.icon_xiajiantou11, R.mipmap.icon_xiajiantou11);
                }

                break;
            case R.id.tv_more:
                ActivityDoctorList.start(mContext, mList);
                break;
        }
    }

    @Override
    public void showResult(ClinicDetailBean detailBean) {
        if (detailBean != null) {
            if (detailBean.getDoctor_list() != null) {
                mList.clear();
                //只取4个展示
                if (detailBean.getDoctor_list().size() >= 4) {
                    for (int i = 0; i < 4; i++) {
                        mList.add(detailBean.getDoctor_list().get(i));
                    }
                } else {
                    mList.addAll(detailBean.getDoctor_list());
                }
                adapter.setNewData(mList);
            }

            ClinicDetailBean.HospitalInfoBean hospital_info = detailBean.getHospital_info();
            if (hospital_info != null) {
                mGivPic.loadImage(hospital_info.getFile(), R.mipmap.ic_launcher);
                mTvClinicTitle.setText(hospital_info.getH_name());
                mTvPhone.setText(hospital_info.getH_phone());
                mTvContent.setText(Html.fromHtml(hospital_info.getH_info() == null ? "" : hospital_info.getH_info()));
                mTvLocal.setText(hospital_info.getH_address());
            }
        }
    }

    @Override
    public String getClinicId() {
        return cilincId;
    }
}
