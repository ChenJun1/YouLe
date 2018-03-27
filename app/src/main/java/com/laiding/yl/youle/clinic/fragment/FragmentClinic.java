package com.laiding.yl.youle.clinic.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.laiding.yl.mvprxretrofitlibrary.utlis.LogUtils;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseFragment;
import com.laiding.yl.youle.clinic.activity.ActivityClinicDetail;
import com.laiding.yl.youle.clinic.adapter.AdapterClinicFragment;
import com.laiding.yl.youle.clinic.entity.ClinicBean;
import com.laiding.yl.youle.clinic.fragment.view.IFragmentClinic;
import com.laiding.yl.youle.clinic.presenter.PresenterClinic;
import com.laiding.yl.youle.widget.MyItemDecoration;
import com.vondear.rxtools.RxDeviceTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/1/17.
 * Remarks 诊所
 */

public class FragmentClinic extends MyBaseFragment implements IFragmentClinic {

    public static final int PAGE_SIZE = 10;

    public static FragmentClinic newInstance() {

        Bundle args = new Bundle();

        FragmentClinic fragment = new FragmentClinic();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.iv_clean_text)
    ImageView mIvCleanText;
    @BindView(R.id.bt_search)
    Button mBtSearch;
    @BindView(R.id.spin_area)
    Spinner mSpinArea;
    @BindView(R.id.spin_grade)
    Spinner mSpinGrade;
    @BindView(R.id.spin_service)
    Spinner mSpinService;
    @BindView(R.id.clinic_rl)
    RecyclerView mClinicRl;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeLayout;

    private AdapterClinicFragment mAdapter;
    private List<ClinicBean.HospitalListBean> list = new ArrayList<>();
    private List<String> areaList = new ArrayList<>();
    private List<String> gradeList = new ArrayList<>();
    private List<String> serviceList = new ArrayList<>();

    private int deviceWidth;//屏幕得宽
    private View notDataView;
    private int page = 1;
    private boolean isRefresh = true;
    private PresenterClinic mPresenterClinic = new PresenterClinic(this, this);
    private String grade = "";
    private String service = "";
    private int address = 0;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_clinic;
    }

    @Override
    protected void init() {
        page=1;
        isRefresh = true;
        initEvent();
        initAdapter();
        initRefresh();
        initStringArray();
        initSpinner();
        mPresenterClinic.requestClinicList();
    }

    private void initStringArray() {
        gradeList.clear();
        serviceList.clear();
        areaList.clear();

        areaList.add("地区");

        gradeList.add("等级");
        gradeList.add("一甲");
        gradeList.add("二甲");
        gradeList.add("三甲");
        gradeList.add("私立");

        serviceList.add("服务");
        serviceList.add("代孕");
        serviceList.add("一级试管");
        serviceList.add("二级试管");
        serviceList.add("三级试管");
    }

    private void intrAreaStringArray(List<ClinicBean.AddressInfoBean> list) {
        areaList.clear();
        areaList.add("地区");
        for (ClinicBean.AddressInfoBean bean : list) {
            areaList.add(bean.getA_address());
        }
    }

    /**
     * 配置spinner
     */
    @SuppressLint("NewApi")
    private void initSpinner() {
        deviceWidth = RxDeviceTool.getScreenWidth(mContext);
        final ArrayAdapter<String> areaAdapter = new ArrayAdapter<>(mContext, R.layout.item_spinner_clinic, areaList);
        final ArrayAdapter<String> gradeAdapter = new ArrayAdapter<>(mContext, R.layout.item_spinner_clinic, gradeList);
        final ArrayAdapter<String> serviceAdapter = new ArrayAdapter<>(mContext, R.layout.item_spinner_clinic, serviceList);
        areaAdapter.setDropDownViewResource(R.layout.item_spinner_clinic);
        gradeAdapter.setDropDownViewResource(R.layout.item_spinner_clinic);
        serviceAdapter.setDropDownViewResource(R.layout.item_spinner_clinic);
        mSpinArea.setAdapter(areaAdapter);
        mSpinGrade.setAdapter(gradeAdapter);
        mSpinService.setAdapter(serviceAdapter);
        //设置下拉框宽度
        mSpinArea.setDropDownWidth(deviceWidth);
        mSpinGrade.setDropDownWidth(deviceWidth);
        mSpinService.setDropDownWidth(deviceWidth);
        mSpinArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                address = position;
                page = 1;
                isRefresh = true;
                mPresenterClinic.requestClinicList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                grade = position == 0 ? "" : gradeAdapter.getItem(position);
                page = 1;
                isRefresh = true;
                mPresenterClinic.requestClinicList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                service = position == 0 ? "" : serviceAdapter.getItem(position);
                page = 1;
                isRefresh = true;
                mPresenterClinic.requestClinicList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    /**
     * 初始化刷新控件
     */
    private void initRefresh() {
        mSwipeLayout.setColorSchemeResources(R.color.color_FF4081, R.color.color_303F9F);
        mSwipeLayout.setOnRefreshListener(this::refreshData);
    }

    /**
     * 刷新数据
     */
    private void refreshData() {
        page = 1;
        mSwipeLayout.setRefreshing(true);
        mAdapter.setEnableLoadMore(false);
        isRefresh = true;
        mPresenterClinic.requestClinicList();
    }

    /**
     * 加载更多
     */
    private void loadMore() {
        page++;
        isRefresh = false;
        mSwipeLayout.setRefreshing(false);
        mPresenterClinic.requestClinicList();

    }

    /**
     * 初始化Adapter
     */
    private void initAdapter() {
        notDataView = getLayoutInflater().inflate(R.layout.empty_text_view, (ViewGroup) mClinicRl.getParent(), false);
        mClinicRl.setLayoutManager(new LinearLayoutManager(mContext));
        mClinicRl.addItemDecoration(new MyItemDecoration());
        mClinicRl.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ClinicBean.HospitalListBean item = mAdapter.getItem(position);
                assert item != null;
                ActivityClinicDetail.start(mContext, item.getH_id());
            }
        });

        mAdapter = new AdapterClinicFragment(list);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(this::loadMore, mClinicRl);
        mClinicRl.setAdapter(mAdapter);
    }

    /**
     * 初始化VIew
     */
    private void initEvent() {
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && mIvCleanText.getVisibility() == View.GONE) {
                    mIvCleanText.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    mIvCleanText.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void initBundleData() {

    }


    @OnClick({R.id.iv_clean_text, R.id.bt_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_clean_text:
                mEtSearch.setText("");
                break;
            case R.id.bt_search:
                page = 1;
                mPresenterClinic.requestClinicList();
                break;
        }
    }

    @Override
    public void showResult(ClinicBean clinicBean) {
        if (clinicBean == null) {
            if (isRefresh) {
                mSwipeLayout.setRefreshing(false);
                mAdapter.setNewData(null);
                mAdapter.setEmptyView(notDataView);
            } else {
                mAdapter.loadMoreEnd(false);
            }
            return;
        }

        if (areaList.size() < 2) {
            intrAreaStringArray(clinicBean.getAddressInfo());
        }
        if (isRefresh) {
            mAdapter.setNewData(clinicBean.getHospitalList());
            mSwipeLayout.setRefreshing(false);

        } else {

            if (clinicBean.getHospitalList() != null && clinicBean.getHospitalList().size() > 0) {
                mAdapter.addData(clinicBean.getHospitalList());
            }
        }
        if (mAdapter.getData().size() < 1) {
            mAdapter.setEmptyView(notDataView);
        }

        if (clinicBean.getHospitalList() == null || clinicBean.getHospitalList().size() < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
        } else {
            mAdapter.loadMoreComplete();
        }

    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public String getName() {
        return mEtSearch.getText().toString();
    }

    @Override
    public String getInfo() {
        return mEtSearch.getText().toString();
    }

    @Override
    public String getGrade() {
        return grade;
    }

    @Override
    public String getService() {
        return service;
    }

    @Override
    public int getAddress() {
        return address;
    }
}
