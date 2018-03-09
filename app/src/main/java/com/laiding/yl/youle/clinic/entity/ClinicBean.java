package com.laiding.yl.youle.clinic.entity;

import java.util.List;

/**
 * Created by JunChen on 2018/2/27.
 * Remarks
 */

public class ClinicBean {


    private List<HospitalListBean> hospitalList;
    private List<AddressInfoBean> addressInfo;

    public List<HospitalListBean> getHospitalList() {
        return hospitalList;
    }

    public void setHospitalList(List<HospitalListBean> hospitalList) {
        this.hospitalList = hospitalList;
    }

    public List<AddressInfoBean> getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(List<AddressInfoBean> addressInfo) {
        this.addressInfo = addressInfo;
    }

    public static class HospitalListBean {
        /**
         * h_id : 1
         * h_name : 上海国际医院
         * img : sm1516329932624.jpg
         * state : 0
         * h_info : <p>最好的医院没有之一！</p>
         * h_grade : 三甲
         * h_service : 代孕,一代试管,二代试管
         * a_address : 上海
         */

        private String h_id;
        private String h_name;
        private String img;
        private String state;
        private String h_info;
        private String h_grade;
        private String h_service;
        private String a_address;

        public String getH_id() {
            return h_id;
        }

        public void setH_id(String h_id) {
            this.h_id = h_id;
        }

        public String getH_name() {
            return h_name;
        }

        public void setH_name(String h_name) {
            this.h_name = h_name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getH_info() {
            return h_info;
        }

        public void setH_info(String h_info) {
            this.h_info = h_info;
        }

        public String getH_grade() {
            return h_grade;
        }

        public void setH_grade(String h_grade) {
            this.h_grade = h_grade;
        }

        public String getH_service() {
            return h_service;
        }

        public void setH_service(String h_service) {
            this.h_service = h_service;
        }

        public String getA_address() {
            return a_address;
        }

        public void setA_address(String a_address) {
            this.a_address = a_address;
        }
    }

    public static class AddressInfoBean {
        /**
         * a_id : 1
         * a_address : 上海
         */

        private String a_id;
        private String a_address;

        public String getA_id() {
            return a_id;
        }

        public void setA_id(String a_id) {
            this.a_id = a_id;
        }

        public String getA_address() {
            return a_address;
        }

        public void setA_address(String a_address) {
            this.a_address = a_address;
        }
    }
}
