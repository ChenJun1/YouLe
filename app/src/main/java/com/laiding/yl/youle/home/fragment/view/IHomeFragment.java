package com.laiding.yl.youle.home.fragment.view;

import com.laiding.yl.mvprxretrofitlibrary.base.IBaseView;
import com.laiding.yl.youle.home.entity.CommunityBean;
import com.laiding.yl.youle.login.entity.UserBean;

import java.util.List;

/**
 * Created by JunChen on 2018/1/9.
 * Remarks
 */

public interface IHomeFragment extends IBaseView {
    void showResult(List<CommunityBean> communityBeanList);
}
