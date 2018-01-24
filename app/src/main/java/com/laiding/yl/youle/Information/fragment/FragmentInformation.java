package com.laiding.yl.youle.Information.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jorge.circlelibrary.ImageCycleView;
import com.laiding.yl.mvprxretrofitlibrary.utlis.LogUtils;
import com.laiding.yl.youle.Information.activity.ActivityInformationDetail;
import com.laiding.yl.youle.Information.adapter.AdapterInformationFragment;
import com.laiding.yl.youle.Information.fragment.view.IInformationFragment;
import com.laiding.yl.youle.Information.presenter.PresenterInformation;
import com.laiding.yl.youle.MyApplication;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseFragment;
import com.laiding.yl.youle.home.entity.ForumPostsBean;
import com.laiding.yl.youle.login.entity.UserBean;
import com.laiding.yl.youle.widget.MyItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by JunChen on 2018/1/23.
 * Remarks 资讯
 */

public class FragmentInformation extends MyBaseFragment implements IInformationFragment {

    public static FragmentInformation newInstance() {

        Bundle args = new Bundle();

        FragmentInformation fragment = new FragmentInformation();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.information_rl)
    RecyclerView mInformationRl;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;

    protected ImageCycleView mCycleView;
    private AdapterInformationFragment adapter;
    private List<ForumPostsBean> list = new ArrayList<>();
    private boolean isRefresh = true;
    private PresenterInformation mPresenter = new PresenterInformation(this, this);

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_information;
    }

    @Override
    protected void init() {
        mTvTitle.setText("资讯");
        initAdapter();
        initRefresh();
        initCycleViewData();
        addHeadView();
    }

    /**
     * 装在数据的集合  文字描述
     */
    ArrayList<String> imageDescList = new ArrayList<>();
    /**
     * 装在数据的集合  图片地址
     */
    ArrayList<String> urlList = new ArrayList<>();

    private void addHeadView() {
        View head = getLayoutInflater().inflate(R.layout.fragment_information_head_view, (ViewGroup) mInformationRl.getParent(), false);
        adapter.addHeaderView(head);
        mCycleView = head.findViewById(R.id.cycleView);
        //轮播类型
        mCycleView.setCycle_T(ImageCycleView.CYCLE_T.CYCLE_VIEW_NORMAL);
        mCycleView.setImageResources(imageDescList, urlList, new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void displayImage(String imageURL, ImageView imageView) {
                /**在此方法中，显示图片，可以用自己的图片加载库，也可以用本demo中的（Imageloader）*/
                Glide.with(mContext).load(imageURL).into(imageView);
            }

            @Override
            public void onImageClick(int position, View imageView) {
                /**实现点击事件*/
                LogUtils.d("点击" + position);
            }
        });
        // 是否隐藏底部
        mCycleView.hideBottom(true);
        mCycleView.startImageCycle();
    }

    private void initCycleViewData() {
        urlList.clear();
        imageDescList.clear();
        imageDescList.add("");
        imageDescList.add("");
        imageDescList.add("");
        urlList.add("http://attach.bbs.miui.com/forum/201604/05/001754vp6j0vmcj49f0evc.jpg.thumb.jpg");
        urlList.add("http://attach.bbs.miui.com/forum/201604/05/001754vp6j0vmcj49f0evc.jpg.thumb.jpg");
        urlList.add("http://attach.bbs.miui.com/forum/201604/05/100838d2b99k6ihk32a36a.jpg.thumb.jpg");
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        mInformationRl.setLayoutManager(new LinearLayoutManager(MyApplication.app));
        mInformationRl.addItemDecoration(new MyItemDecoration());

        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        adapter = new AdapterInformationFragment(list);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, mInformationRl);
        mInformationRl.setAdapter(adapter);

        mInformationRl.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                ActivityInformationDetail.start(mContext);
            }
        });
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
        mPresenter.login("ruffian", "EA8A706C4C34A168");
    }

    /**
     * 加载更多
     */
    private void loadMore() {
        isRefresh = false;
        mSwipeLayout.setRefreshing(false);
        mPresenter.login("ruffian", "EA8A706C4C34A168");

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
