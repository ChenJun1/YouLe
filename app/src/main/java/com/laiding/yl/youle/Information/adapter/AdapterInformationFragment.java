package com.laiding.yl.youle.Information.adapter;

import android.support.annotation.Nullable;
import android.text.Html;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.home.entity.CommunityBean;
import com.laiding.yl.youle.home.entity.ForumPostsBean;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.List;

/**
 * Created by JunChen on 2018/1/23.
 * Remarks
 */

public class AdapterInformationFragment extends BaseQuickAdapter<CommunityBean, BaseViewHolder> {
    public AdapterInformationFragment(@Nullable List<CommunityBean> data) {
        super( R.layout.item_fragment_information,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommunityBean item) {
        helper.setText(R.id.tv_content, Html.fromHtml(item.getN_content()));
        GlideImageView imageView = helper.getView(R.id.gridView);
        imageView.loadImage("https://www.zhuangbi.info/uploads/i/2017-12-27-33edf85858c00f22a9ec69c1037eb88b.jpg",R.mipmap.ic_launcher);
        helper.setText(R.id.tv_time, item.getTime());
    }
}
