package com.laiding.yl.youle.clinic.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.home.entity.ForumPostsBean;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.List;

/**
 * Created by JunChen on 2018/1/31.
 * Remarks
 */

public class AdapterDocotrList extends BaseQuickAdapter<ForumPostsBean,BaseViewHolder> {
    public AdapterDocotrList(@Nullable List<ForumPostsBean> data) {
        super(R.layout.item_doctor, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ForumPostsBean item) {
        GlideImageView imageView= helper.getView(R.id.tv_avatar);
        imageView.loadLocalCircleImage(R.drawable.header_background, R.mipmap.ic_launcher_round);
    }
}
