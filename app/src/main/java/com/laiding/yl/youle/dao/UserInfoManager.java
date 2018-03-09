package com.laiding.yl.youle.dao;

import com.laiding.yl.youle.MyApplication;
import com.laiding.yl.youle.login.entity.User;

/**
 * Created by JunChen on 2018/2/28.
 * Remarks
 */

public class UserInfoManager {
    public static User getUserInfo() {
        User user=new User("","","","","");
        UserDaoUtil userDaoUtil = new UserDaoUtil(MyApplication.app);
        if(userDaoUtil.queryAllUser()!=null&&userDaoUtil.queryAllUser().size()>0){
           return userDaoUtil.queryAllUser().get(0);
        }
        return user;
    }
}
