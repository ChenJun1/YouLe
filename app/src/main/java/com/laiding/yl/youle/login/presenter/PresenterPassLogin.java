package com.laiding.yl.youle.login.presenter;

import com.laiding.yl.youle.base.MyBasePresenter;
import com.laiding.yl.youle.login.activity.ActivityPassLogin;
import com.laiding.yl.youle.login.activity.view.IPassLogin;

/**
 * Created by JunChen on 2018/2/3.
 * Remarks
 */

public class PresenterPassLogin extends MyBasePresenter<IPassLogin,ActivityPassLogin> {
    public PresenterPassLogin(IPassLogin view, ActivityPassLogin activity) {
        super(view, activity);
    }
}
