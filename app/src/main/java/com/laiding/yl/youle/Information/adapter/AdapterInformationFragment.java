package com.laiding.yl.youle.Information.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.home.entity.ForumPostsBean;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.List;

/**
 * Created by JunChen on 2018/1/23.
 * Remarks
 */

public class AdapterInformationFragment extends BaseQuickAdapter<ForumPostsBean, BaseViewHolder> {
    public AdapterInformationFragment(@Nullable List<ForumPostsBean> data) {
        super( R.layout.item_fragment_information,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ForumPostsBean item) {
        helper.setText(R.id.tv_content,"关关雎鸠，在河之洲。窈窕淑女，君子好逑。参差荇菜，左右流之。窈窕淑女，寤寐求之。");
        GlideImageView imageView = helper.getView(R.id.gridView);
        imageView.loadImage("https://www.zhuangbi.info/uploads/i/2017-12-27-33edf85858c00f22a9ec69c1037eb88b.jpg",R.mipmap.ic_launcher);
        helper.setText(R.id.tv_time, "10-23");
    }
}
