package com.laiding.yl.youle.home.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.laiding.yl.mvprxretrofitlibrary.utlis.LogUtils;
import com.laiding.yl.youle.MyApplication;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseFragment;
import com.laiding.yl.youle.home.activty.ActivityDietAssistant;
import com.laiding.yl.youle.home.activty.ActivityGoodPregnancyGuidance;
import com.laiding.yl.youle.home.activty.ActivityIVFSuccessRate;
import com.laiding.yl.youle.home.activty.ActivityLegitimateSurrogacy;
import com.laiding.yl.youle.home.activty.ActivityMedicalRecords;
import com.laiding.yl.youle.home.activty.ActivityPregnancyTest;
import com.laiding.yl.youle.home.activty.ActivityTestTubeBabyProcess;
import com.laiding.yl.youle.home.activty.ActivityTestTubeGuidance;
import com.laiding.yl.youle.home.adapter.AdapterHomeFragement;
import com.laiding.yl.youle.home.entity.ForumPostsBean;
import com.laiding.yl.youle.home.fragment.view.IHomeFragment;
import com.laiding.yl.youle.home.presenter.PresenterHomeFragment;
import com.laiding.yl.youle.im.activity.ActivityChat;
import com.laiding.yl.youle.login.entity.UserBean;
import com.laiding.yl.youle.widget.MyItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by JunChen on 2018/1/4.
 * Remarks 首页Fragment
 */

public class FragmentHome extends MyBaseFragment implements IHomeFragment {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_bar_right)
    TextView mTvBarRight;
    @BindView(R.id.home_rl)
    RecyclerView homeRl;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.ll_top_head)
    LinearLayout mLlTopHead;

    public static FragmentHome newInstance() {

        Bundle args = new Bundle();

        FragmentHome fragment = new FragmentHome();
        fragment.setArguments(args);
        return fragment;
    }

    private PresenterHomeFragment presenter = new PresenterHomeFragment(this, this);
    private AdapterHomeFragement adapter;
    private List<ForumPostsBean> list = new ArrayList<>();
    private boolean isRefresh = true;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        presenter.login("ruffian", "EA8A706C4C34A168");

        initbar();
        initAdapter();
        addHeadView();
        initRefresh();
    }

    private void initbar() {
        mLlTopHead.setVisibility(View.GONE);
        mTvTitle.setVisibility(View.VISIBLE);
        mTvBarRight.setVisibility(View.VISIBLE);
        mTvTitle.setText("首页");
    }

    /**
     * 初始化刷新控件
     */
    private void initRefresh() {
        swipeLayout.setColorSchemeResources(R.color.color_FF4081, R.color.color_303F9F);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refreshData();
            }
        });
    }

    /**
     * 刷新数据
     */
    private void refreshData() {
        swipeLayout.setRefreshing(true);
        adapter.setEnableLoadMore(false);
        isRefresh = true;
        presenter.login("ruffian", "EA8A706C4C34A168");
    }


    /**
     * 添加头部
     */
    private void addHeadView() {
        View head = getLayoutInflater().inflate(R.layout.fragment_home_head_view, (ViewGroup) homeRl.getParent(), false);
        adapter.addHeaderView(head);
        //试管婴儿流程
        head.findViewById(R.id.btn_Process).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("btn_Process" + "=========");
                ActivityTestTubeBabyProcess.start(mContext);
            }
        });
        //试管婴儿成功率
        head.findViewById(R.id.btn_success_rate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("btn_Process" + "=========");
                ActivityIVFSuccessRate.start(mContext);
            }
        });
        //今日待办
        head.findViewById(R.id.ll_today_to_be_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("btn_Process" + "=========");
                presenter.startCalendar();
            }
        });
        //诊疗记录
        head.findViewById(R.id.ll_medical_records).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("btn_Process" + "=========");
                ActivityMedicalRecords.start(mContext);
            }
        });
        //饮食助手
        head.findViewById(R.id.ll_diet_assistant).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("btn_Process" + "=========");
                ActivityDietAssistant.start(mContext);
            }
        });
        //好孕指导
        head.findViewById(R.id.ll_good_prgegnancy_guidance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("btn_Process" + "=========");
                ActivityGoodPregnancyGuidance.start(mContext);
            }
        });
        //备孕检查
        head.findViewById(R.id.ll_pregnancy_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("btn_Process" + "=========");
                ActivityPregnancyTest.start(mContext);
            }
        });
        //试管指导
        head.findViewById(R.id.ll_test_tube_guidance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("btn_Process" + "=========");
                ActivityTestTubeGuidance.start(mContext);
            }
        });
        //合法代孕
        head.findViewById(R.id.ll_legitimate_surrogacy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("btn_Process" + "=========");
                ActivityLegitimateSurrogacy.start(mContext);
            }
        });
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        homeRl.setLayoutManager(new LinearLayoutManager(MyApplication.app));
        homeRl.addItemDecoration(new MyItemDecoration());

        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        adapter = new AdapterHomeFragement(R.layout.item_fragment_home, list);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, homeRl);
        homeRl.setAdapter(adapter);
    }

    /**
     * 加载更多
     */
    private void loadMore() {
        isRefresh = false;
        swipeLayout.setRefreshing(false);
        presenter.login("ruffian", "EA8A706C4C34A168");

    }

    @Override
    protected void initBundleData() {

    }


    @Override
    public void showResult(UserBean userBean) {
        if (isRefresh) {
            list.clear();
            list.add(new ForumPostsBean());
            list.add(new ForumPostsBean());
            list.add(new ForumPostsBean());
            list.add(new ForumPostsBean());
            list.add(new ForumPostsBean());
            list.add(new ForumPostsBean());
            list.add(new ForumPostsBean());
            list.add(new ForumPostsBean());
            adapter.setNewData(list);
            swipeLayout.setRefreshing(false);
        } else {
            adapter.loadMoreFail();
            adapter.loadMoreComplete();
            list.add(new ForumPostsBean());
            list.add(new ForumPostsBean());
            list.add(new ForumPostsBean());
            list.add(new ForumPostsBean());
            list.add(new ForumPostsBean());
            list.add(new ForumPostsBean());
            list.add(new ForumPostsBean());
            if (list != null && list.size() > 0) {
                adapter.addData(list);
            }
        }

//        if (size < PAGE_SIZE) {
//            //第一页如果不够一页就不显示没有更多数据布局
//            mAdapter.loadMoreEnd(isRefresh);
//            Toast.makeText(this, "no more data", Toast.LENGTH_SHORT).show();
//        } else {
//            mAdapter.loadMoreComplete();
//        }
    }


    @OnClick(R.id.tv_bar_right)
    public void onViewClicked() {
        ActivityChat.start(mContext, "8899");
    }

}
