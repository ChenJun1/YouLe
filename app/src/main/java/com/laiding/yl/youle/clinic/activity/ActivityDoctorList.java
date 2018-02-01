package com.laiding.yl.youle.clinic.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.clinic.adapter.AdapterDocotrList;
import com.laiding.yl.youle.home.entity.ForumPostsBean;
import com.laiding.yl.youle.widget.MyItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/1/26.
 * Remarks 专家团队
 */

public class ActivityDoctorList extends MyBaseActivity {
    @BindView(R.id.rlview)
    RecyclerView mRlview;
    @BindView(R.id.ll_call_doctor)
    LinearLayout mLlCallDoctor;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;

    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityDoctorList.class);
        context.startActivity(starter);
    }

    private AdapterDocotrList adapter;
    private List<ForumPostsBean> list = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_doctor_list;
    }

    @Override
    protected void init() {
        setTitle("专家团队");
        isBack(true);
        initView();
        initData();
        initAdapter();
        initRefresh();
    }

    /**
     * 初始化刷新控件
     */
    private void initRefresh() {
        mSwipeLayout.setColorSchemeResources(R.color.color_FF4081, R.color.color_303F9F);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeLayout.setRefreshing(false);
            }
        });
    }

    private void initAdapter() {
        adapter = new AdapterDocotrList(list);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ActivityDoctorDetail.start(mContext);
            }
        });
        mRlview.setAdapter(adapter);
    }

    private void initData() {
        list.clear();
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
    }

    private void initView() {
        mRlview.addItemDecoration(new MyItemDecoration());
        mRlview.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void initBundleData() {

    }


    @OnClick(R.id.ll_call_doctor)
    public void onViewClicked() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
