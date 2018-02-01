package com.laiding.yl.youle.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hyphenate.easeui.runtimepermissions.PermissionsAPI;
import com.hyphenate.easeui.runtimepermissions.PermissionsManager;
import com.hyphenate.easeui.runtimepermissions.PermissionsResultAction;
import com.laiding.yl.mvprxretrofitlibrary.utlis.LogUtils;
import com.laiding.yl.youle.Information.activity.ActivityInformationDetail;
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
import com.laiding.yl.youle.home.presenter.PresenterHome;
import com.laiding.yl.youle.login.entity.UserBean;
import com.laiding.yl.youle.widget.MyItemDecoration;
import com.vondear.rxtools.RxNetTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * Created by JunChen on 2018/1/4.
 * Remarks 首页Fragment
 */

public class FragmentHome extends MyBaseFragment implements IHomeFragment {

    private static final int PRC_CARLENDAR = 1;
    @BindView(R.id.home_rl)
    RecyclerView homeRl;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    public static FragmentHome newInstance() {

        Bundle args = new Bundle();

        FragmentHome fragment = new FragmentHome();
        fragment.setArguments(args);
        return fragment;
    }

    private PresenterHome presenter = new PresenterHome(this, this);
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
        initAdapter();
        addHeadView();
        initRefresh();
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
                startCarlendar();
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

    @AfterPermissionGranted(PRC_CARLENDAR)
    private void startCarlendar(){
        if (EasyPermissions.hasPermissions(mContext, PermissionsAPI.carlendarPermissions)) {
            presenter.startCalendar();
        } else {
            EasyPermissions.requestPermissions(this, "日程安排选择需要以下权限:\n\n1.访问设备上的日历", PRC_CARLENDAR,  PermissionsAPI.carlendarPermissions);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        switch (requestCode){
            case PRC_CARLENDAR:
                presenter.startCalendar();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == PRC_CARLENDAR) {
            Toast.makeText(mContext, "您拒绝了「访问日历」所需要的相关权限!", Toast.LENGTH_SHORT).show();
        }
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

        homeRl.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ActivityInformationDetail.start(mContext);
            }
        });
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

//    ping                        : 判断是否有外网连接
//    isWifiEnabled               : 判断WIFI是否打开
//    is3rd                       : 判断是否为3G网络
//    isWifi                      : 判断网络连接方式是否为WIFI
//    isNetworkAvailable          : 判断网络连接是否可用
//    isGpsEnabled                : GPS是否打开
//    getNetWork                  : 获取当前网络状态
//    openWirelessSettings        : 打开网络设置界面
//    getActiveNetworkInfo        : 获取活动网络信息
//    isAvailable                 : 判断网络是否可用
//    isConnected                 : 判断网络是否连接
//    is4G                        : 判断网络是否是4G
//    isWifiConnected             : 判断wifi是否连接状态
//    getNetworkOperatorName      : 获取移动网络运营商名称
//    getPhoneType                : 获取移动终端类型
//    getNetWorkType              : 获取当前的网络类型
//    getNetWorkTypeName          : 获取当前的网络类型名称

    private void test(){
        LogUtils.d(RxNetTool.ping()+"===");
        LogUtils.d(RxNetTool.isWifiEnabled(mContext)+"===");
        LogUtils.d(RxNetTool.isWifi(mContext)+"===");
        LogUtils.d(RxNetTool.isWifiConnected(mContext)+"===");
        LogUtils.d(RxNetTool.getNetWorkTypeName(mContext)+"===");
        LogUtils.d(RxNetTool.getNetWorkType(mContext)+"===");
    }
}
