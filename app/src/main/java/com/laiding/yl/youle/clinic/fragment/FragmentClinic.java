package com.laiding.yl.youle.clinic.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
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
import com.laiding.yl.youle.clinic.fragment.view.IFragmentClinic;
import com.laiding.yl.youle.home.entity.ForumPostsBean;
import com.laiding.yl.youle.widget.MyItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/1/17.
 * Remarks 诊所
 */

public class FragmentClinic extends MyBaseFragment implements IFragmentClinic {

    public static FragmentClinic newInstance() {

        Bundle args = new Bundle();

        FragmentClinic fragment = new FragmentClinic();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.iv_clean_phone)
    ImageView mIvCleanPhone;
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
    private List<ForumPostsBean> list = new ArrayList<>();
    private List<String> areaList = new ArrayList<>();
    private List<String> gradeList = new ArrayList<>();
    private List<String> serviceList = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_clinic;
    }

    @Override
    protected void init() {
        initEvent();
        initAdapter();
        initRefresh();
        initStringArray();
        initSpinner();
    }

    private void initStringArray() {
        areaList.clear();
        gradeList.clear();
        serviceList.clear();

        areaList.add("地区");
        areaList.add("上海");
        areaList.add("北京");
        areaList.add("广东");
        areaList.add("河北");

        gradeList.add("等级");
        gradeList.add("一级");
        gradeList.add("二级");
        gradeList.add("三级");
        gradeList.add("四级");

        serviceList.add("服务");
        serviceList.add("代孕");
        serviceList.add("一级试管");
        serviceList.add("二级试管");
        serviceList.add("三级试管");
    }

    /**
     * 配置spinner
     */
    private void initSpinner() {

        final ArrayAdapter<String> areaAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, areaList);
        final ArrayAdapter<String> gradeAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, gradeList);
        final ArrayAdapter<String> serviceAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, serviceList);
        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        serviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinArea.setAdapter(areaAdapter);
        mSpinGrade.setAdapter(gradeAdapter);
        mSpinService.setAdapter(serviceAdapter);
        mSpinArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.d(areaAdapter.getItem(position) + "==========");
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
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }

    /**
     * 初始化Adapter
     */
    private void initAdapter() {
        mClinicRl.setLayoutManager(new LinearLayoutManager(mContext));
        mClinicRl.addItemDecoration(new MyItemDecoration());
        mClinicRl.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ActivityClinicDetail.start(mContext);
            }
        });
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        list.add(new ForumPostsBean());
        mAdapter = new AdapterClinicFragment(list);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        }, mClinicRl);
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
                if (!TextUtils.isEmpty(s) && mIvCleanPhone.getVisibility() == View.GONE) {
                    mIvCleanPhone.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    mIvCleanPhone.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void initBundleData() {

    }


    @OnClick({R.id.iv_clean_phone, R.id.bt_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_clean_phone:
                mEtSearch.setText("");
                break;
            case R.id.bt_search:

                break;
        }
    }
}
