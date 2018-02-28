package com.laiding.yl.youle.Information.fragment.view;

import com.laiding.yl.mvprxretrofitlibrary.base.IBaseView;
import com.laiding.yl.youle.Information.entity.AdsPictures;
import com.laiding.yl.youle.home.entity.CommunityBean;
import com.laiding.yl.youle.login.entity.UserBean;

import java.util.List;

/**
 * Created by JunChen on 2018/1/23.
 * Remarks
 */

public interface IInformationFragment extends IBaseView {
    void showResult(List<CommunityBean> beanList);

    int getPage();

    void showResultAdsPictures(List<AdsPictures> picturesList);
}
