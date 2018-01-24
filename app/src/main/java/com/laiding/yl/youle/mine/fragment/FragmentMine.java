package com.laiding.yl.youle.mine.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseFragment;
import com.laiding.yl.youle.mine.activity.ActivitySet;
import com.sunfusheng.glideimageview.GlideImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by JunChen on 2018/1/22.
 * Remarks 我的
 */

public class FragmentMine extends MyBaseFragment {
    public static FragmentMine newInstance() {

        Bundle args = new Bundle();

        FragmentMine fragment = new FragmentMine();
        fragment.setArguments(args);
        return fragment;
    }


    @BindView(R.id.iv_bar_right)
    GlideImageView mIvBarRight;
    @BindView(R.id.ll_im_bar_right)
    LinearLayout mLlImBarRight;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_user_avatar)
    ImageView mIvUserAvatar;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.tv_user_content)
    TextView mTvUserContent;
    @BindView(R.id.ll_top_head)
    LinearLayout mLlTopHead;
    @BindView(R.id.ll_theme)
    LinearLayout mLlTheme;
    @BindView(R.id.ll_reply)
    LinearLayout mLlReply;
    @BindView(R.id.ll_collection)
    LinearLayout mLlCollection;
    @BindView(R.id.ll_history)
    LinearLayout mLlHistory;
    @BindView(R.id.ll_utils)
    LinearLayout mLlUtils;
    @BindView(R.id.ll_set)
    LinearLayout mLlSet;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void init() {
        initView();
    }

    private void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("我的");
        mIvBarRight.setVisibility(View.VISIBLE);
        mIvBarRight.loadLocalImage(R.mipmap.icon_shezhi, R.mipmap.icon_shezhi);
    }

    @Override
    protected void initBundleData() {

    }

    @OnClick({R.id.ll_im_bar_right, R.id.ll_top_head, R.id.ll_theme, R.id.ll_reply, R.id.ll_collection, R.id.ll_history, R.id.ll_utils, R.id.ll_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_top_head:
                break;
            case R.id.ll_theme:
                break;
            case R.id.ll_reply:
                break;
            case R.id.ll_collection:
                break;
            case R.id.ll_history:
                break;
            case R.id.ll_utils:
                break;
            case R.id.ll_set:
                ActivitySet.start(mContext);
                break;
            case R.id.ll_im_bar_right:
                ActivitySet.start(mContext);
                break;
        }
    }

}
