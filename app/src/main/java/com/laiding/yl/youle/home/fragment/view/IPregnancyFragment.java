package com.laiding.yl.youle.home.fragment.view;

import com.laiding.yl.mvprxretrofitlibrary.base.IBaseView;
import com.laiding.yl.youle.home.entity.PregnancyBean;
import com.laiding.yl.youle.login.entity.UserBean;

import java.util.List;

/**
 * Created by JunChen on 2018/1/24.
 * Remarks
 */

public interface IPregnancyFragment extends IBaseView{
    void showResult(List<PregnancyBean> list);

    String getPid();

    int getPage();
}
