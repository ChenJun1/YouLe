package com.laiding.yl.youle.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.laiding.yl.youle.home.fragment.FragmentPrepareForPregnancy;
import com.laiding.yl.youle.mine.fragment.FragmentMine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunChen on 2018/1/24.
 * Remarks
 */

public class AdapterPregnancyViewPage extends FragmentPagerAdapter {

    private List<Fragment> mFragments=new ArrayList<>();
    public AdapterPregnancyViewPage(FragmentManager fm) {
        super(fm);
        mFragments.add(FragmentPrepareForPregnancy.newInstance());
        mFragments.add(FragmentPrepareForPregnancy.newInstance());
        mFragments.add(FragmentPrepareForPregnancy.newInstance());
        mFragments.add(FragmentPrepareForPregnancy.newInstance());
        mFragments.add(FragmentPrepareForPregnancy.newInstance());
        mFragments.add(FragmentMine.newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments==null?0:mFragments.size();
    }
}
