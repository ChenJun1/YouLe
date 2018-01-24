package com.laiding.yl.youle.inquiry.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseFragment;
import com.laiding.yl.youle.im.activity.ActivityChat;
import com.sunfusheng.glideimageview.GlideImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by JunChen on 2018/1/22.
 * Remarks
 */

public class FragmentInquiry extends MyBaseFragment {
    public static FragmentInquiry newInstance() {

        Bundle args = new Bundle();

        FragmentInquiry fragment = new FragmentInquiry();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_gwy)
    TextView mTvGwy;
    @BindView(R.id.tv_zgjx)
    TextView mTvZgjx;
    @BindView(R.id.tv_zgnmb)
    TextView mTvZgnmb;
    @BindView(R.id.tv_zgnmxr)
    TextView mTvZgnmxr;
    @BindView(R.id.tv_zgnmzs)
    TextView mTvZgnmzs;
    @BindView(R.id.tv_zgjl)
    TextView mTvZgjl;
    @BindView(R.id.tv_zgll)
    TextView mTvZgll;
    @BindView(R.id.tv_lcfybh)
    TextView mTvLcfybh;
    @BindView(R.id.tv_dnlc)
    TextView mTvDnlc;
    @BindView(R.id.tv_lczs)
    TextView mTvLczs;
    @BindView(R.id.tv_lcnz)
    TextView mTvLcnz;
    @BindView(R.id.tv_nyqn)
    TextView mTvNyqn;
    @BindView(R.id.tv_dcttlc)
    TextView mTvDcttlc;
    @BindView(R.id.tv_zrlc)
    TextView mTvZrlc;
    @BindView(R.id.tv_xzlc)
    TextView mTvXzlc;
    @BindView(R.id.tv_xgxlc)
    TextView mTvXgxlc;
    @BindView(R.id.tv_shrs)
    TextView mTvShrs;
    @BindView(R.id.tv_slgwt)
    TextView mTvSlgwt;
    @BindView(R.id.tv_slgtrbc)
    TextView mTvSlgtrbc;
    @BindView(R.id.tv_jslxwt)
    TextView mTvJslxwt;
    @BindView(R.id.tv_rj)
    TextView mTvRj;
    @BindView(R.id.tv_qlxy)
    TextView mTvQlxy;
    @BindView(R.id.tv_jzjx)
    TextView mTvJzjx;
    @BindView(R.id.tv_sj)
    TextView mTvSj;
    @BindView(R.id.tv_sijin)
    TextView mTvSijin;
    @BindView(R.id.tv_ldlz)
    TextView mTvLdlz;
    @BindView(R.id.tv_jzbyh)
    TextView mTvJzbyh;
    @BindView(R.id.tv_rtby)
    TextView mTvRtby;
    @BindView(R.id.tv_snsn)
    TextView mTvSnsn;
    @BindView(R.id.tv_fjsz)
    TextView mTvFjsz;
    @BindView(R.id.tv_hfdy)
    TextView mTvHfdy;
    @BindView(R.id.tv_hfgr)
    TextView mTvHfgr;
    @BindView(R.id.iv_bar_right)
    GlideImageView mIvBarRight;
    @BindView(R.id.ll_im_bar_right)
    LinearLayout mLlImBarRight;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_inquiry;
    }

    @Override
    protected void init() {
        initView();
    }

    private void initView() {
        mTvTitle.setText("问诊");
        mIvBarRight.loadLocalImage(R.mipmap.icon_zaixiankefu, R.mipmap.icon_zaixiankefu);
        mIvBarRight.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initBundleData() {

    }

    @OnClick({R.id.ll_im_bar_right, R.id.tv_gwy, R.id.tv_zgjx, R.id.tv_zgnmb, R.id.tv_zgnmxr, R.id.tv_zgnmzs, R.id.tv_zgjl, R.id.tv_zgll, R.id.tv_lcfybh, R.id.tv_dnlc, R.id.tv_lczs, R.id.tv_lcnz, R.id.tv_nyqn, R.id.tv_dcttlc, R.id.tv_zrlc, R.id.tv_xzlc, R.id.tv_xgxlc, R.id.tv_shrs, R.id.tv_slgwt, R.id.tv_slgtrbc, R.id.tv_jslxwt, R.id.tv_rj, R.id.tv_qlxy, R.id.tv_jzjx, R.id.tv_sj, R.id.tv_sijin, R.id.tv_ldlz, R.id.tv_jzbyh, R.id.tv_rtby, R.id.tv_snsn, R.id.tv_fjsz, R.id.tv_hfdy, R.id.tv_hfgr})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_im_bar_right:
                ActivityChat.start(mContext, "8009");
                break;
            case R.id.tv_gwy:
                break;
            case R.id.tv_zgjx:
                break;
            case R.id.tv_zgnmb:
                break;
            case R.id.tv_zgnmxr:
                break;
            case R.id.tv_zgnmzs:
                break;
            case R.id.tv_zgjl:
                break;
            case R.id.tv_zgll:
                break;
            case R.id.tv_lcfybh:
                break;
            case R.id.tv_dnlc:
                break;
            case R.id.tv_lczs:
                break;
            case R.id.tv_lcnz:
                break;
            case R.id.tv_nyqn:
                break;
            case R.id.tv_dcttlc:
                break;
            case R.id.tv_zrlc:
                break;
            case R.id.tv_xzlc:
                break;
            case R.id.tv_xgxlc:
                break;
            case R.id.tv_shrs:
                break;
            case R.id.tv_slgwt:
                break;
            case R.id.tv_slgtrbc:
                break;
            case R.id.tv_jslxwt:
                break;
            case R.id.tv_rj:
                break;
            case R.id.tv_qlxy:
                break;
            case R.id.tv_jzjx:
                break;
            case R.id.tv_sj:
                break;
            case R.id.tv_sijin:
                break;
            case R.id.tv_ldlz:
                break;
            case R.id.tv_jzbyh:
                break;
            case R.id.tv_rtby:
                break;
            case R.id.tv_snsn:
                break;
            case R.id.tv_fjsz:
                break;
            case R.id.tv_hfdy:
                break;
            case R.id.tv_hfgr:
                break;
        }
    }

}
