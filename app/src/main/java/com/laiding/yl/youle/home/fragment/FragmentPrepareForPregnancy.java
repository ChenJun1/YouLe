package com.laiding.yl.youle.home.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.laiding.yl.youle.Information.activity.ActivityInformationDetail;
import com.laiding.yl.youle.MyApplication;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseFragment;
import com.laiding.yl.youle.home.activty.ActivityPregnancyDetail;
import com.laiding.yl.youle.home.adapter.AdapterPregnancyFragment;
import com.laiding.yl.youle.home.entity.ForumPostsBean;
import com.laiding.yl.youle.home.entity.PregnancyBean;
import com.laiding.yl.youle.home.fragment.view.IPregnancyFragment;
import com.laiding.yl.youle.home.presenter.PresenterPregnancy;
import com.laiding.yl.youle.login.entity.UserBean;
import com.laiding.yl.youle.widget.MyItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by JunChen on 2018/1/24.
 * Remarks 备孕
 */

public class FragmentPrepareForPregnancy extends MyBaseFragment implements IPregnancyFragment {

    public static final int PAGE_SIZE = 10;
    @BindView(R.id.rcy_view)
    RecyclerView mRcyView;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;

    public static FragmentPrepareForPregnancy newInstance(String pid) {

        Bundle args = new Bundle();
        args.putString("PID",pid);
        FragmentPrepareForPregnancy fragment = new FragmentPrepareForPregnancy();
        fragment.setArguments(args);
        return fragment;
    }

    private PresenterPregnancy presenter = new PresenterPregnancy(this, this);
    private AdapterPregnancyFragment adapter;
    private List<PregnancyBean> list = new ArrayList<>();
    private boolean isRefresh = true;
    private String pid="";
    private int page=1;
    private View notDataView;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_prepare_for_pregnancy;
    }

    @Override
    protected void init() {
        initAdapter();
        initRefresh();
        presenter.requestHttpPregnant();
    }

    @Override
    protected void initBundleData() {
        pid= getArguments().getString("PID");
    }


    /**
     * 初始化刷新控件
     */
    private void initRefresh() {
        mSwipeLayout.setColorSchemeResources(R.color.color_FF4081, R.color.color_303F9F);
        mSwipeLayout.setOnRefreshListener(() -> refreshData());
    }

    /**
     * 刷新数据
     */
    private void refreshData() {
        page=1;
        mSwipeLayout.setRefreshing(true);
        adapter.setEnableLoadMore(false);
        isRefresh = true;
        presenter.requestHttpPregnant();
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        notDataView = getLayoutInflater().inflate(R.layout.empty_text_view, (ViewGroup) mRcyView.getParent(), false);
        mRcyView.setLayoutManager(new LinearLayoutManager(MyApplication.app));
        mRcyView.addItemDecoration(new MyItemDecoration());

        adapter = new AdapterPregnancyFragment(list);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(() -> loadMore(), mRcyView);
        mRcyView.setAdapter(adapter);

        mRcyView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                PregnancyBean item = (PregnancyBean) adapter.getItem(position);
                int pid=item==null?-1:Integer.valueOf(item.getP_id());
                ActivityPregnancyDetail.start(mContext,pid);
            }
        });
    }

    /**
     * 加载更多
     */
    private void loadMore() {
        page++;
        isRefresh = false;
        mSwipeLayout.setRefreshing(false);
        presenter.requestHttpPregnant();

    }

    @Override
    public void showResult(List<PregnancyBean> list) {
        if(list==null){
            if(isRefresh){
                mSwipeLayout.setRefreshing(false);
            }else{
                adapter.loadMoreFail();
            }
            adapter.setEmptyView(notDataView);
        }else {

            if (isRefresh) {
                adapter.setNewData(list);
                mSwipeLayout.setRefreshing(false);

            } else {
                if (list.size() > 0) {
                    adapter.addData(list);
                }
            }
            if (adapter.getData().size() == 0) {
                adapter.setEmptyView(notDataView);
            }

            if (list.size() < PAGE_SIZE) {
                //第一页如果不够一页就不显示没有更多数据布局
                adapter.loadMoreEnd(isRefresh);
            } else {
                adapter.loadMoreComplete();
            }
        }

    }

    @Override
    public String getPid() {
        return pid;
    }

    @Override
    public int getPage() {
        return page;
    }
}
