package com.laiding.yl.youle.information.entity;

import java.util.List;

/**
 * Created by JunChen on 2018/3/8.
 * Remarks
 */

public class CommentListBean {

    /**
     * totalNumber : 2
     * messageInfo : [{"m_id":"1","m_message":"胜多负少的","time":"1517822008","u_nname":"187****776","photo":null},{"m_id":"2","m_message":"实打实阿斯顿","time":"1519798145","u_nname":"187****7777","photo":null}]
     */

    private int totalNumber;
    private List<MessageInfoBean> messageInfo;

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<MessageInfoBean> getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(List<MessageInfoBean> messageInfo) {
        this.messageInfo = messageInfo;
    }

    public static class MessageInfoBean {
        /**
         * m_id : 1
         * m_message : 胜多负少的
         * time : 1517822008
         * u_nname : 187****776
         * photo : null
         */

        private String m_id;
        private String m_message;
        private String time;
        private String u_nname;
        private String photo;

        public String getM_id() {
            return m_id;
        }

        public void setM_id(String m_id) {
            this.m_id = m_id;
        }

        public String getM_message() {
            return m_message;
        }

        public void setM_message(String m_message) {
            this.m_message = m_message;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getU_nname() {
            return u_nname;
        }

        public void setU_nname(String u_nname) {
            this.u_nname = u_nname;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}
