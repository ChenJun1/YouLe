package com.laiding.yl.youle.clinic.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.clinic.activity.view.IDoctorList;
import com.laiding.yl.youle.clinic.adapter.AdapterDocotrList;
import com.laiding.yl.youle.clinic.entity.ClinicDetailBean;
import com.laiding.yl.youle.home.entity.ForumPostsBean;
import com.laiding.yl.youle.widget.MyItemDecoration;
import com.vondear.rxtools.RxDeviceTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/1/26.
 * Remarks 专家团队
 */

public class ActivityDoctorList extends MyBaseActivity implements IDoctorList {
    @BindView(R.id.rlview)
    RecyclerView mRlview;
    @BindView(R.id.ll_call_doctor)
    LinearLayout mLlCallDoctor;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;

    public static void start(Context context, ArrayList<ClinicDetailBean.DoctorListBean> listBeans) {
        Intent starter = new Intent(context, ActivityDoctorList.class);
        starter.putParcelableArrayListExtra("DLIST", listBeans);
        context.startActivity(starter);
    }

    private View notDataView;
    private AdapterDocotrList adapter;
    private List<ClinicDetailBean.DoctorListBean> list = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_doctor_list;
    }

    @Override
    protected void init() {
        setTitle("专家团队");
        isBack(true);
        initView();
        initAdapter();
        initRefresh();
    }

    /**
     * 初始化刷新控件
     */
    private void initRefresh() {
        mSwipeLayout.setColorSchemeResources(R.color.color_FF4081, R.color.color_303F9F);
        mSwipeLayout.setOnRefreshListener(() -> {
            adapter.setNewData(list);
            mSwipeLayout.setRefreshing(false);
        });
    }

    private void initAdapter() {
        notDataView = getLayoutInflater().inflate(R.layout.empty_text_view, (ViewGroup) mRlview.getParent(), false);
        adapter = new AdapterDocotrList(list);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            ClinicDetailBean.DoctorListBean item = (ClinicDetailBean.DoctorListBean) adapter.getItem(position);
            ActivityDoctorDetail.start(mContext, item.getD_id());
        });
        mRlview.setAdapter(adapter);
        if (list.size() < 1) {
            adapter.setEmptyView(notDataView);
        }
    }

    private void initView() {
        mRlview.addItemDecoration(new MyItemDecoration());
        mRlview.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void initBundleData() {
        ArrayList<ClinicDetailBean.DoctorListBean> dlist = getIntent().getParcelableArrayListExtra("DLIST");
        list.clear();
        list.addAll(dlist);
    }


    @OnClick(R.id.ll_call_doctor)
    public void onViewClicked() {
        RxDeviceTool.dial(mContext,"13120995099");
    }

}
