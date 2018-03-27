package com.laiding.yl.youle.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.laiding.yl.youle.information.fragment.FragmentInformation;
import com.laiding.yl.youle.clinic.fragment.FragmentClinic;
import com.laiding.yl.youle.home.fragment.FragmentHome;
import com.laiding.yl.youle.inquiry.fragment.FragmentInquiry;
import com.laiding.yl.youle.mine.fragment.FragmentMine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunChen on 2018/1/4.
 * Remarks
 */

public class AdapterHomeViewPage extends FragmentPagerAdapter{

    private List<Fragment> list=new ArrayList<>();

    public AdapterHomeViewPage(FragmentManager fm) {
        super(fm);
        list.clear();
        list.add(FragmentHome.newInstance());
        list.add(FragmentClinic.newInstance());
        list.add(FragmentInquiry.newInstance());
        list.add(FragmentInformation.newInstance());
        list.add(FragmentMine.newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
