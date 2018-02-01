package com.laiding.yl.youle.mine.activity.view;

import com.laiding.yl.mvprxretrofitlibrary.base.IBaseView;

/**
 * Created by JunChen on 2018/1/31.
 * Remarks
 */

public interface IPersnonalInformation extends IBaseView {
    void setAvatar(String url);

    void setNikeName(CharSequence nikeName);

    CharSequence getNikeName();

    void setGender(CharSequence gender);

    CharSequence getGender();

    void setBirthday(CharSequence birthday);

    CharSequence getBirthday();

    void setName(CharSequence name);

    CharSequence getName();

    void setPhone(CharSequence phone);

    CharSequence getPhone();

    void setLocation(CharSequence location);

    CharSequence getLocation();


    void setDetailLocation(CharSequence detailLocation);

    CharSequence getDetailLocation();

    void setPostal(CharSequence postal);

    CharSequence getPostal();

    void setEmail(CharSequence email);

    CharSequence getEmail();
}
