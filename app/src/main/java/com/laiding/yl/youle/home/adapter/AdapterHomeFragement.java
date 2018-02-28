package com.laiding.yl.youle.home.adapter;


import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spanned;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.home.entity.CommunityBean;
import com.laiding.yl.youle.home.entity.ForumPostsBean;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.List;

/**
 * Created by JunChen on 2018/1/9.
 * Remarks 首页
 */

public class AdapterHomeFragement extends BaseQuickAdapter<CommunityBean, BaseViewHolder> {

    public AdapterHomeFragement(int layoutResId, @Nullable List<CommunityBean> data) {
        super(layoutResId, data);

    }

    @SuppressLint("NewApi")
    @Override
    protected void convert(BaseViewHolder helper, CommunityBean item) {
        helper.setText(R.id.title_tv, item.getN_title());
        helper.setText(R.id.content_tv, Html.fromHtml(item.getN_content()));
        GlideImageView imageView = helper.getView(R.id.pic_iv);
        imageView.loadImage("https://www.zhuangbi.info/uploads/i/2017-12-27-33edf85858c00f22a9ec69c1037eb88b.jpg",R.mipmap.ic_launcher);

    }
}
