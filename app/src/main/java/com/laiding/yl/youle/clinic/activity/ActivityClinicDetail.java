package com.laiding.yl.youle.clinic.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.hyphenate.easeui.runtimepermissions.PermissionsAPI;
import com.hyphenate.easeui.runtimepermissions.PermissionsManager;
import com.hyphenate.easeui.runtimepermissions.PermissionsResultAction;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.clinic.activity.view.IClinicDetail;
import com.laiding.yl.youle.clinic.adapter.AdapterClinicDetail;
import com.laiding.yl.youle.clinic.presenter.PresenterClinicDetail;
import com.laiding.yl.youle.home.entity.ForumPostsBean;
import com.sunfusheng.glideimageview.GlideImageView;
import com.vondear.rxtools.RxDeviceTool;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/1/26.
 * Remarks 诊所详情
 */

public class ActivityClinicDetail extends MyBaseActivity implements IClinicDetail{

    @BindView(R.id.giv_pic)
    GlideImageView mGivPic;
    @BindView(R.id.tv_phone)
    TextView mTvPhoto;
    @BindView(R.id.btn_call)
    Button mBtnCall;
    @BindView(R.id.tv_local)
    TextView mTvLocal;
    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.giv_jiaotou)
    GlideImageView mGivJiaotou;
    @BindView(R.id.tv_more)
    TextView mTvMore;
    @BindView(R.id.rcy_view)
    RecyclerView mRcyView;

    private boolean flag = true; //展开收缩箭头标记
    private List<ForumPostsBean> mList=new ArrayList<>();
    private AdapterClinicDetail adapter;
    private PresenterClinicDetail presenter=new PresenterClinicDetail(this,this);
    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityClinicDetail.class);
        context.startActivity(starter);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_clinic_detail;
    }

    @Override
    protected void init() {
        setTitle("医院详情");
        isBack(true);
        initData();
        initRcView();
    }

    private void initData() {
        mList.add(new ForumPostsBean());
        mList.add(new ForumPostsBean());
        mList.add(new ForumPostsBean());
        mList.add(new ForumPostsBean());
        mList.add(new ForumPostsBean());
    }

    private void initRcView() {
        adapter=new AdapterClinicDetail(mList);
        LinearLayoutManager ms = new LinearLayoutManager(mContext);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRcyView.setLayoutManager(ms);
        mRcyView.setAdapter(adapter);
        mRcyView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                    ActivityDoctorDetail.start(mContext);
            }
        });
    }

    @Override
    protected void initBundleData() {

    }


    @OnClick({R.id.btn_call, R.id.giv_jiaotou, R.id.tv_more, R.id.rcy_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_call:
                RxDeviceTool.dial(mContext,mTvPhoto.getText().toString());
//                if(PermissionsManager.getInstance().hasAllPermissions(mContext,PermissionsAPI.callPhonePermissions)){
//                    RxDeviceTool.dial(mContext,mTvPhoto.getText().toString());
//                }else{
//                    PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(ActivityClinicDetail.this, PermissionsAPI.callPhonePermissions,
//                            new PermissionsResultAction() {
//                                @Override
//                                public void onGranted() {
//                                    RxDeviceTool.dial(mContext,mTvPhoto.getText().toString());
//                                }
//
//                                @Override
//                                public void onDenied(String permission) {
//                                    Toast.makeText(mContext,"拨号权限被拒绝，无法拨号",Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                }

                break;
            case R.id.giv_jiaotou:
                if (flag) {
                    flag = false;
                    mTvContent.setMaxLines(100);
                    mGivJiaotou.loadLocalImage(R.mipmap.icon_shangjiantou11, R.mipmap.icon_shangjiantou11);
                } else {
                    flag = true;
                    mTvContent.setMaxLines(4);
                    mGivJiaotou.loadLocalImage(R.mipmap.icon_xiajiantou11, R.mipmap.icon_xiajiantou11);
                }

                break;
            case R.id.tv_more:
                ActivityDoctorList.start(mContext);
                break;
            case R.id.rcy_view:
                break;
        }
    }
}
