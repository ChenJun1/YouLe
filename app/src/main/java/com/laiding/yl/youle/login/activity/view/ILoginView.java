package com.laiding.yl.youle.login.activity.view;

import com.laiding.yl.mvprxretrofitlibrary.base.IBaseView;
import com.laiding.yl.youle.login.entity.UserBean;

/**
 * Created by JunChen on 2018/1/3.
 */

public interface ILoginView extends IBaseView{

    void showResult(UserBean userBean);
    void toChat();
}
