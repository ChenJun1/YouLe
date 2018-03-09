package com.laiding.yl.youle.Information.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jorge.circlelibrary.ImageCycleView;
import com.laiding.yl.mvprxretrofitlibrary.utlis.LogUtils;
import com.laiding.yl.youle.Information.activity.ActivityInformationDetail;
import com.laiding.yl.youle.Information.adapter.AdapterInformationFragment;
import com.laiding.yl.youle.Information.entity.AdsPictures;
import com.laiding.yl.youle.Information.fragment.view.IInformationFragment;
import com.laiding.yl.youle.Information.presenter.PresenterInformation;
import com.laiding.yl.youle.MyApplication;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseFragment;
import com.laiding.yl.youle.home.entity.CommunityBean;
import com.laiding.yl.youle.utils.MConstant;
import com.laiding.yl.youle.widget.MyItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by JunChen on 2018/1/23.
 * Remarks 资讯
 */

public class FragmentInformation extends MyBaseFragment implements IInformationFragment {

    public static final int PAGE_SIZE = 10;

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
    private List<CommunityBean> list = new ArrayList<>();
    private boolean isRefresh = true;
    private PresenterInformation mPresenter = new PresenterInformation(this, this);
    private int page = 1; //页
    private View headView;
    private View notDataView;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_information;
    }

    @Override
    protected void init() {
        mTvTitle.setText("资讯");
        initAdapter();
        initRefresh();

        addHeadView();
        mPresenter.requestHttp();
        mPresenter.requestHttpAdsPictures();
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
        headView = getLayoutInflater().inflate(R.layout.fragment_information_head_view, (ViewGroup) mInformationRl.getParent(), false);
        adapter.addHeaderView(headView);
        mCycleView = headView.findViewById(R.id.cycleView);
        // 是否隐藏底部
        mCycleView.hideBottom(true);
        //轮播类型
        mCycleView.setCycle_T(ImageCycleView.CYCLE_T.CYCLE_VIEW_NORMAL);

    }

    @SuppressLint("NewApi")
    private void initCycleViewData(List<AdsPictures> picturesList) {
        urlList.clear();
        imageDescList.clear();
        if (picturesList == null) {
            imageDescList.add("");
            urlList.add("");
        } else {
            for (AdsPictures adsPictures : picturesList) {
                imageDescList.add("");
                urlList.add(MConstant.IMGURL + adsPictures.getFile());
            }
        }
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

        mCycleView.startImageCycle();
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        notDataView = getLayoutInflater().inflate(R.layout.empty_text_view, (ViewGroup) mInformationRl.getParent(), false);

        mInformationRl.setLayoutManager(new LinearLayoutManager(MyApplication.app));
        mInformationRl.addItemDecoration(new MyItemDecoration());

        adapter = new AdapterInformationFragment(list);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(() -> loadMore(), mInformationRl);
        mInformationRl.setAdapter(adapter);

        mInformationRl.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                CommunityBean bean = (CommunityBean) adapter.getItem(position);
                ActivityInformationDetail.start(mContext, bean == null ? -1 : Integer.valueOf(bean.getN_id().isEmpty() ? "-1" : bean.getN_id()));
            }
        });
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
        page = 1;
        mSwipeLayout.setRefreshing(true);
        adapter.setEnableLoadMore(false);
        isRefresh = true;
        mPresenter.requestHttp();
    }

    /**
     * 加载更多
     */
    private void loadMore() {
        page++;
        isRefresh = false;
        mSwipeLayout.setRefreshing(false);
        mPresenter.requestHttp();

    }

    @Override
    protected void initBundleData() {

    }

    @Override
    public void showResult(List<CommunityBean> list) {
        adapter.removeAllFooterView();
        if (list == null) {
            if (isRefresh) {
                mSwipeLayout.setRefreshing(false);
            } else {
                adapter.loadMoreFail();
            }
            adapter.addFooterView(notDataView);
            return;
        }
        if (isRefresh) {
            adapter.setNewData(list);
            mSwipeLayout.setRefreshing(false);
            if (list.size() < 1) {
                adapter.addFooterView(notDataView);
            }
        } else {
            if (list.size() > 0) {
                adapter.addData(list);
            }
        }

        if (list.size() < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            adapter.loadMoreEnd(isRefresh);
        } else {
            adapter.loadMoreComplete();
        }
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public void showResultAdsPictures(List<AdsPictures> picturesList) {
        initCycleViewData(picturesList);
    }
}
