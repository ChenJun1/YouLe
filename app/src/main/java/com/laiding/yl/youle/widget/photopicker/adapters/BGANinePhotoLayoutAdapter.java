package com.laiding.yl.youle.widget.photopicker.adapters;


import android.databinding.BindingAdapter;

import com.laiding.yl.youle.widget.photopicker.widget.BGANinePhotoLayout;

import java.util.ArrayList;


/**
 * Created by JunChen on 2018/1/24.
 * 描述:
 */
public class BGANinePhotoLayoutAdapter {

    @BindingAdapter({"bga_npl_delegate"})
    public static void setDelegate(BGANinePhotoLayout ninePhotoLayout, BGANinePhotoLayout.Delegate delegate) {
        ninePhotoLayout.setDelegate(delegate);
    }

    @BindingAdapter({"bga_npl_data"})
    public static void setData(BGANinePhotoLayout ninePhotoLayout, ArrayList<String> data) {
        ninePhotoLayout.setData(data);
    }
}
