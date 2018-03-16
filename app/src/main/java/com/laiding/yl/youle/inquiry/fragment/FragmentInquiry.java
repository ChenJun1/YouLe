package com.laiding.yl.youle.inquiry.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.laiding.yl.mvprxretrofitlibrary.base.IBaseView;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseFragment;
import com.laiding.yl.youle.im.activity.ActivityChat;
import com.laiding.yl.youle.utils.MConstant;
import com.sunfusheng.glideimageview.GlideImageView;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/1/22.
 * Remarks 问诊
 */

public class FragmentInquiry extends MyBaseFragment{
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
        mLlImBarRight.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initBundleData() {

    }

    @OnClick({R.id.ll_im_bar_right, R.id.tv_gwy, R.id.tv_zgjx, R.id.tv_zgnmb, R.id.tv_zgnmxr, R.id.tv_zgnmzs, R.id.tv_zgjl, R.id.tv_zgll, R.id.tv_lcfybh, R.id.tv_dnlc, R.id.tv_lczs, R.id.tv_lcnz, R.id.tv_nyqn, R.id.tv_dcttlc, R.id.tv_zrlc, R.id.tv_xzlc, R.id.tv_xgxlc, R.id.tv_shrs, R.id.tv_slgwt, R.id.tv_slgtrbc, R.id.tv_jslxwt, R.id.tv_rj, R.id.tv_qlxy, R.id.tv_jzjx, R.id.tv_sj, R.id.tv_sijin, R.id.tv_ldlz, R.id.tv_jzbyh, R.id.tv_rtby, R.id.tv_snsn, R.id.tv_fjsz, R.id.tv_hfdy, R.id.tv_hfgr})
    public void onViewClicked(View view) {
        if (!EMClient.getInstance().isConnected()) {
            isTokenExpired("客服未连接,请重新登录");
//            RxToast.error("客服未连接,请重新登录");
            return;
        }
        switch (view.getId()) {
            case R.id.ll_im_bar_right:
                ActivityChat.start(mContext, "19", "","");
                break;
            case R.id.tv_gwy:
                ActivityChat.start(mContext, "19", "宫外孕",  MConstant.GWY);
                break;
            case R.id.tv_zgjx:
                ActivityChat.start(mContext, "19", "子宫畸形",  MConstant.ZGJX);
                break;
            case R.id.tv_zgnmb:
                ActivityChat.start(mContext, "19", "子宫内膜薄",  MConstant.ZGNMB);
                break;
            case R.id.tv_zgnmxr:
                ActivityChat.start(mContext, "19", "子宫内膜息肉",  MConstant.ZGNXR);
                break;
            case R.id.tv_zgnmzs:
                ActivityChat.start(mContext, "19", "子宫内膜增生",  MConstant.ZGNZS);
                break;
            case R.id.tv_zgjl:
                ActivityChat.start(mContext, "19", "子宫肌瘤",  MConstant.ZGJL);
                break;
            case R.id.tv_zgll:
                ActivityChat.start(mContext, "19", "宫腔黏连",  MConstant.GGLM);
                break;
            case R.id.tv_lcfybh:
                ActivityChat.start(mContext, "19", "卵巢发育不好",  MConstant.LCFYBUHAO);
                break;
            case R.id.tv_dnlc:
                ActivityChat.start(mContext, "19", "多囊卵巢",  MConstant.DUORANGLUANCHAO);
                break;
            case R.id.tv_lczs:
                ActivityChat.start(mContext, "19", "卵巢早衰",  MConstant.LUANCHAOZAOSUAI);
                break;
            case R.id.tv_lcnz:
                ActivityChat.start(mContext, "19", "卵巢囊肿",  MConstant.LUANCHAORANGZ);
                break;
            case R.id.tv_nyqn:
                ActivityChat.start(mContext, "19", "内异巧囊",  MConstant.NYQR);
                break;
            case R.id.tv_dcttlc:
                ActivityChat.start(mContext, "19", "多次胎停流产",  MConstant.DCTTLC);
                break;
            case R.id.tv_zrlc:
                ActivityChat.start(mContext, "19", "自然流产",  MConstant.ZIRANLIUCHAN);
                break;
            case R.id.tv_xzlc:
                ActivityChat.start(mContext, "19", "先兆流产",  MConstant.XIANZHAOLIUCHAN);
                break;
            case R.id.tv_xgxlc:
                ActivityChat.start(mContext, "19", "习惯性流产",  MConstant.XGXLC);
                break;
            case R.id.tv_shrs:
                ActivityChat.start(mContext, "19", "生化妊娠",  MConstant.SHENGHUARENCHENG);
                break;
            case R.id.tv_slgwt:
                ActivityChat.start(mContext, "19", "输卵管问题",  MConstant.SLGJY);
                break;
            case R.id.tv_slgtrbc:
                ActivityChat.start(mContext, "19", "输卵管通而不畅",  MConstant.SLGTRBC);
                break;
            case R.id.tv_jslxwt:
                ActivityChat.start(mContext, "19", "激素六项问题",  MConstant.NEIFENGMI);
                break;
            case R.id.tv_rj:
                ActivityChat.start(mContext, "19", "弱精",  MConstant.RUOJING);
                break;
            case R.id.tv_qlxy:
                ActivityChat.start(mContext, "19", "前列腺炎",  MConstant.QIANLIEXIANYAN);
                break;
            case R.id.tv_jzjx:
                ActivityChat.start(mContext, "19", "精子畸形",  MConstant.JINGZIJIXING);
                break;
            case R.id.tv_sj:
                ActivityChat.start(mContext, "19", "少精",  MConstant.SHAOJING);
                break;
            case R.id.tv_sijin:
                ActivityChat.start(mContext, "19", "死精",  MConstant.SIJING);
                break;
            case R.id.tv_ldlz:
                ActivityChat.start(mContext, "19", "冷冻卵子",  MConstant.LENGDONGLUANZI);
                break;
            case R.id.tv_jzbyh:
                ActivityChat.start(mContext, "19", "精子不液化",  MConstant.JZBYH);
                break;
            case R.id.tv_rtby:
                ActivityChat.start(mContext, "19", "二胎备孕",  MConstant.ERTAIBEIYU);
                break;
            case R.id.tv_snsn:
                ActivityChat.start(mContext, "19", "生男生女",  MConstant.SHENGNANSHENGNV);
                break;
            case R.id.tv_fjsz:
                ActivityChat.start(mContext, "19", "赴美生子",  MConstant.FUMEISHENGZI);
                break;
            case R.id.tv_hfdy:
                ActivityChat.start(mContext, "19", "合法代孕",  MConstant.HEFADAIYUNL);
                break;
            case R.id.tv_hfgr:
                ActivityChat.start(mContext, "19", "合法供卵",  MConstant.HEFAGONGLUAN);
                break;
        }
    }

}
