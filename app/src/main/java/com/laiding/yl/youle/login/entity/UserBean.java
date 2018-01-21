package com.laiding.yl.youle.login.entity;

/**
 * 用户实体类
 *
 * @author ZhongDaFeng
 */

public class UserBean {
    //token	string	用户登录生成的token
    //uid	string	用户Id
    private String token;
    private String uid;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
