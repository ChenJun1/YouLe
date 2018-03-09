package com.laiding.yl.youle.dao;

import android.content.Context;

import com.laiding.yl.mvprxretrofitlibrary.utlis.LogUtils;
import com.laiding.yl.youle.login.entity.User;

import java.util.List;

/**
 * Created by JunChen on 2018/2/26.
 * Remarks
 */

public class UserDaoUtil {
    private static final String TAG = UserDaoUtil.class.getSimpleName();
    private DaoManager mManager;

    public UserDaoUtil(Context context){
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成User记录的插入，如果表未创建，先创建User表
     * @param user
     * @return
     */
    public boolean insertUser(User user){
        boolean flag=false;
        flag= mManager.getDaoSession().getUserDao().insertOrReplace(user) != -1;
        LogUtils.i("insert User :" + flag + "-->" + user.toString());
        return flag;
    }


    /**
     * 修改一条数据
     * @param user
     * @return
     */
    public boolean updateUser(User user){
        boolean flag = false;
        try {
            mManager.getDaoSession().update(user);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     * @param user
     * @return
     */
    public boolean deleteUser(User user){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().delete(user);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有记录
     * @return
     */
    public boolean deleteAll(){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().deleteAll(User.class);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<User> queryAllUser(){
        return mManager.getDaoSession().loadAll(User.class);
    }

    /**
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public User queryUserById(long key){
        return mManager.getDaoSession().load(User.class, key);
    }

}
