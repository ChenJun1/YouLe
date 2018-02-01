package com.laiding.yl.youle.clinic.presenter;

import com.laiding.yl.youle.base.MyBaseFrgPresenter;
import com.laiding.yl.youle.clinic.fragment.FragmentClinic;
import com.laiding.yl.youle.clinic.fragment.view.IFragmentClinic;

/**
 * Created by JunChen on 2018/1/29.
 * Remarks
 */

public class PresenterClinic extends MyBaseFrgPresenter<IFragmentClinic,FragmentClinic> {
    public PresenterClinic(IFragmentClinic view, FragmentClinic fragmet) {
        super(view, fragmet);
    }
}
