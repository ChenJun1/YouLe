package com.laiding.yl.youle.login.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by JunChen on 2018/2/26.
 * Remarks
 */
@Entity
public class User {

    /**
     * u_id : 2
     * token : 908f28f960581fc573417cd4bdb4a907
     * u_nname : 一直在
     * u_phone : 17621876063
     */
    private String u_id;
    private String token;
    private String u_nname;
    private String u_phone;
    private String u_pwd;
    @Generated(hash = 822292789)
    public User(String u_id, String token, String u_nname, String u_phone,
            String u_pwd) {
        this.u_id = u_id;
        this.token = token;
        this.u_nname = u_nname;
        this.u_phone = u_phone;
        this.u_pwd = u_pwd;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public String getU_id() {
        return this.u_id;
    }
    public void setU_id(String u_id) {
        this.u_id = u_id;
    }
    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getU_nname() {
        return this.u_nname;
    }
    public void setU_nname(String u_nname) {
        this.u_nname = u_nname;
    }
    public String getU_phone() {
        return this.u_phone;
    }
    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }
    public String getU_pwd() {
        return this.u_pwd;
    }
    public void setU_pwd(String u_pwd) {
        this.u_pwd = u_pwd;
    }

}
