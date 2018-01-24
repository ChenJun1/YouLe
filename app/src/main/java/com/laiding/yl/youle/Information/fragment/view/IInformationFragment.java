package com.laiding.yl.youle.Information.fragment.view;

import com.laiding.yl.mvprxretrofitlibrary.base.IBaseView;
import com.laiding.yl.youle.login.entity.UserBean;

/**
 * Created by JunChen on 2018/1/23.
 * Remarks
 */

public interface IInformationFragment extends IBaseView {
    void showResult(UserBean userBean);
}
