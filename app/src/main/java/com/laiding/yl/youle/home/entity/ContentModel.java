package com.laiding.yl.youle.home.entity;

import java.io.Serializable;


public class ContentModel implements Serializable {

    private static final long serialVersionUID = -26005291063869005L;

    private int postion;

    private String content;

    public int getPostion() {
        return postion;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
