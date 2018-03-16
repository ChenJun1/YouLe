package com.laiding.yl.youle.mine.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseFragment;
import com.laiding.yl.youle.mine.activity.ActivityPersonalInformation;
import com.laiding.yl.youle.mine.activity.ActivitySet;
import com.laiding.yl.youle.mine.entity.UserInfo;
import com.laiding.yl.youle.mine.fragment.presenter.PresenterMine;
import com.laiding.yl.youle.mine.fragment.view.IFragmentMine;
import com.laiding.yl.youle.utils.MConstant;
import com.sunfusheng.glideimageview.GlideImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by JunChen on 2018/1/22.
 * Remarks 我的
 */

public class FragmentMine extends MyBaseFragment implements IFragmentMine{
    public static final int REQUESTCODE=0x1000;
    public static final String AVATAR="AVATAR";
    public static final String NNAME="NNAME";
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
    GlideImageView mIvUserAvatar;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.ll_top_avatar)
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

    private PresenterMine mPresenterMine=new PresenterMine(this,this);

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void init() {
        initView();
    }

    private void initView() {
        mTvTitle.setText("我的");
        mLlImBarRight.setVisibility(View.VISIBLE);
        mIvBarRight.loadLocalImage(R.mipmap.icon_shezhi12, R.mipmap.icon_shezhi12);
        mPresenterMine.requestUserInfo();
    }

    @Override
    protected void initBundleData() {

    }

    @OnClick({R.id.ll_im_bar_right, R.id.ll_top_avatar, R.id.ll_theme, R.id.ll_reply, R.id.ll_collection, R.id.ll_history, R.id.ll_utils, R.id.ll_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_top_avatar:
                Intent intent=new Intent(mContext,ActivityPersonalInformation.class);
                startActivityForResult(intent,REQUESTCODE);
                break;
            case R.id.ll_theme:
                break;
            case R.id.ll_reply:
                break;
            case R.id.ll_collection    :
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK&& requestCode==REQUESTCODE){
            Bundle extras = data.getExtras();
            String avatarUrl=extras.getString(AVATAR);
            String nname=extras.getString(NNAME);
            mIvUserAvatar.loadCircleImage(avatarUrl, R.mipmap.im_avatar);
            mTvUserName.setText(nname);
        }
    }

    @Override
    public void showResult(UserInfo userInfo) {
        if(userInfo==null)
            return;
        mIvUserAvatar.loadCircleImage(userInfo.getPhoto(), R.mipmap.im_avatar);
        mTvUserName.setText(userInfo.getU_nname());
    }
}
