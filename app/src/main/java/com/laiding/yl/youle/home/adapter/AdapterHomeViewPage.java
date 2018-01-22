package com.laiding.yl.youle.home.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.laiding.yl.youle.clinic.fragment.FragmentClinic;
import com.laiding.yl.youle.home.fragment.FragmentHome;
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
        EaseChatFragment fragment=new EaseChatFragment();
        Bundle bundle=new Bundle();
        bundle.putString(EaseConstant.EXTRA_USER_ID,"8001");
        fragment.setArguments(bundle);
        list.add(FragmentHome.newInstance());
        list.add(FragmentClinic.newInstance());
        list.add(new FragmentHome());
        list.add(new FragmentHome());
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
