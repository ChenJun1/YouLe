package com.laiding.yl.youle.home.activty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.laiding.yl.youle.MyApplication;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.home.adapter.AdapterMedicalRecordsActivity;
import com.laiding.yl.youle.home.entity.ForumPostsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/1/21.
 * Remarks 诊疗记录
 */

public class ActivityMedicalRecords extends MyBaseActivity {
    @BindView(R.id.iv_add)
    ImageView mIvAdd;
    @BindView(R.id.rcy_view)
    RecyclerView mRcyView;

    private AdapterMedicalRecordsActivity adapter;
    private List<ForumPostsBean> mList = new ArrayList<>();

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
        initData();
    }

    private void initData() {
        mList.clear();
        mList.add(new ForumPostsBean());
        mList.add(new ForumPostsBean());
        mList.add(new ForumPostsBean());
        mList.add(new ForumPostsBean());
        mList.add(new ForumPostsBean());
        adapter.setNewData(mList);
    }

    private void initAdapter() {
        adapter = new AdapterMedicalRecordsActivity(mList);
        mRcyView.setAdapter(adapter);
    }

    private void initView() {
        setTitle("诊疗记录");
        isBack(true);
        mRcyView.setLayoutManager(new LinearLayoutManager(MyApplication.app));
    }

    @Override
    protected void initBundleData() {

    }

    @OnClick(R.id.iv_add)
    public void onViewClicked() {
        ActivityAddMedicalRecords.start(mContext);
    }
}
