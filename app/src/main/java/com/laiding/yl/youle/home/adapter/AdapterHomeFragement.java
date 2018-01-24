package com.laiding.yl.youle.home.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.home.entity.ForumPostsBean;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.List;

/**
 * Created by JunChen on 2018/1/9.
 * Remarks 首页
 */

public class AdapterHomeFragement extends BaseQuickAdapter<ForumPostsBean, BaseViewHolder> {

    public AdapterHomeFragement(int layoutResId, @Nullable List<ForumPostsBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, ForumPostsBean item) {
        helper.setText(R.id.title_tv, "假如风不停");
        GlideImageView imageView = helper.getView(R.id.pic_iv);
        imageView.loadImage("https://www.zhuangbi.info/uploads/i/2017-12-27-33edf85858c00f22a9ec69c1037eb88b.jpg",R.mipmap.ic_launcher);

    }
}
