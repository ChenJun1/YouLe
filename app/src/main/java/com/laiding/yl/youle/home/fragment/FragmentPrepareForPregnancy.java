package com.laiding.yl.youle.home.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.laiding.yl.youle.Information.activity.ActivityInformationDetail;
import com.laiding.yl.youle.MyApplication;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseFragment;
import com.laiding.yl.youle.home.activty.ActivityPregnancyDetail;
import com.laiding.yl.youle.home.adapter.AdapterPregnancyFragment;
import com.laiding.yl.youle.home.entity.ForumPostsBean;
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

    @BindView(R.id.rcy_view)
    RecyclerView mRcyView;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;

    public static FragmentPrepareForPregnancy newInstance() {

        Bundle args = new Bundle();
        FragmentPrepareForPregnancy fragment = new FragmentPrepareForPregnancy();
        fragment.setArguments(args);
        return fragment;
    }

    private PresenterPregnancy presenter = new PresenterPregnancy(this, this);
    private AdapterPregnancyFragment adapter;
    private List<ForumPostsBean> list = new ArrayList<>();
    private boolean isRefresh = true;


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_prepare_for_pregnancy;
    }

    @Override
    protected void init() {
        initAdapter();
        initRefresh();
    }

    @Override
    protected void initBundleData() {

    }


    /**
     * 初始化刷新控件
     */
    private void initRefresh() {
        mSwipeLayout.setColorSchemeResources(R.color.color_FF4081, R.color.color_303F9F);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
        mSwipeLayout.setRefreshing(true);
        adapter.setEnableLoadMore(false);
        isRefresh = true;
        presenter.login("ruffian", "EA8A706C4C34A168");
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        mRcyView.setLayoutManager(new LinearLayoutManager(MyApplication.app));
        mRcyView.addItemDecoration(new MyItemDecoration());

        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        adapter = new AdapterPregnancyFragment(list);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(() -> loadMore(), mRcyView);
        mRcyView.setAdapter(adapter);

        mRcyView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ActivityPregnancyDetail.start(mContext);
            }
        });
    }

    /**
     * 加载更多
     */
    private void loadMore() {
        isRefresh = false;
        mSwipeLayout.setRefreshing(false);
        presenter.login("ruffian", "EA8A706C4C34A168");

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
            mSwipeLayout.setRefreshing(false);
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
    }

}
