package com.laiding.yl.youle.home.activty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.laiding.yl.youle.MyApplication;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.home.activty.view.IMedicalRecordsActy;
import com.laiding.yl.youle.home.adapter.AdapterMedicalRecordsActivity;
import com.laiding.yl.youle.home.entity.ForumPostsBean;
import com.laiding.yl.youle.home.entity.MedicalRecordsBean;
import com.laiding.yl.youle.home.presenter.PresenterMedicalRecords;
import com.vondear.rxtools.RxIntentTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/1/21.
 * Remarks 诊疗记录
 */

public class ActivityMedicalRecords extends MyBaseActivity implements IMedicalRecordsActy {
    @BindView(R.id.iv_add)
    ImageView mIvAdd;
    @BindView(R.id.rcy_view)
    RecyclerView mRcyView;
    @BindView(R.id.tv_add)
    TextView mTvAdd;

    private AdapterMedicalRecordsActivity adapter;
    private List<MedicalRecordsBean> mList = new ArrayList<>();
    private PresenterMedicalRecords presenter=new PresenterMedicalRecords(this,this);

    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityMedicalRecords.class);
        context.startActivity(starter);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_medical_records;
    }

    @Override
    protected void init() {
        initView();
        initAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.requestHttp();
    }

    private void initAdapter() {
        adapter = new AdapterMedicalRecordsActivity(mList);
        mRcyView.setAdapter(adapter);
    }

    private void initView() {
        setTitle("诊疗记录");
        isBack(true);
        mRcyView.setLayoutManager(new LinearLayoutManager(MyApplication.app));
        mRcyView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                MedicalRecordsBean item = (MedicalRecordsBean) adapter.getItem(position);
                ActivityMedicalRecordsDetail.start(mContext, item != null ? Integer.valueOf(item.getR_id()) : 0);
            }
        });
    }

    @Override
    protected void initBundleData() {
    }

    @OnClick({R.id.iv_add, R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add:
                ActivityAddMedicalRecords.start(mContext);
                break;
            case R.id.tv_add:
                ActivityAddMedicalRecords.start(mContext);
                break;
        }
    }

    @Override
    public void showResult(List<MedicalRecordsBean> list) {
        if(list==null)
            return;
        adapter.setNewData(list);
    }
}
