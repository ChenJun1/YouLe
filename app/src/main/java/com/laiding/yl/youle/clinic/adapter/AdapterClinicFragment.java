package com.laiding.yl.youle.clinic.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.home.entity.ForumPostsBean;

import java.util.List;

/**
 * Created by JunChen on 2018/1/17.
 * Remarks
 */

public class AdapterClinicFragment extends BaseQuickAdapter<ForumPostsBean, BaseViewHolder> {

    public AdapterClinicFragment(@Nullable List<ForumPostsBean> data) {
        super(R.layout.item_fragment_clinic,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ForumPostsBean item) {

    }
}
