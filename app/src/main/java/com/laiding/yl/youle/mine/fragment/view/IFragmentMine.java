package com.laiding.yl.youle.mine.fragment.view;

import com.laiding.yl.mvprxretrofitlibrary.base.IBaseView;
import com.laiding.yl.youle.mine.entity.UserInfo;

/**
 * Created by JunChen on 2018/3/2.
 * Remarks
 */

public interface IFragmentMine extends IBaseView{
    void showResult(UserInfo userInfo);
}
