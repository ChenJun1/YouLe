package com.laiding.yl.youle.clinic.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JunChen on 2018/3/2.
 * Remarks
 */

public class ClinicDetailBean implements Parcelable {


    /**
     * hospital_info : {"h_id":"2","h_name":"北京妇科医院","h_phone":"23423423424","h_address":"北京市朝阳","file":"1516169643941.jpg","h_info":"<p>主流医生！<\/p>","state":"0"}
     * doctor_list : [{"d_id":"1","d_name":"李武","d_duties":"主任医师","d_file":"sm1516176127148.jpg","d_room":"男科","d_info":"<p>士大夫<\/p>"}]
     */

    private HospitalInfoBean hospital_info;
    private List<DoctorListBean> doctor_list;

    public HospitalInfoBean getHospital_info() {
        return hospital_info;
    }

    public void setHospital_info(HospitalInfoBean hospital_info) {
        this.hospital_info = hospital_info;
    }

    public List<DoctorListBean> getDoctor_list() {
        return doctor_list;
    }

    public void setDoctor_list(List<DoctorListBean> doctor_list) {
        this.doctor_list = doctor_list;
    }

    public static class HospitalInfoBean {
        /**
         * h_id : 2
         * h_name : 北京妇科医院
         * h_phone : 23423423424
         * h_address : 北京市朝阳
         * file : 1516169643941.jpg
         * h_info : <p>主流医生！</p>
         * state : 0
         */

        private String h_id;
        private String h_name;
        private String h_phone;
        private String h_address;
        private String file;
        private String h_info;
        private String state;

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

        public String getH_phone() {
            return h_phone;
        }

        public void setH_phone(String h_phone) {
            this.h_phone = h_phone;
        }

        public String getH_address() {
            return h_address;
        }

        public void setH_address(String h_address) {
            this.h_address = h_address;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        public String getH_info() {
            return h_info;
        }

        public void setH_info(String h_info) {
            this.h_info = h_info;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

    public static class DoctorListBean implements Parcelable {
        /**
         * d_id : 1
         * d_name : 李武
         * d_duties : 主任医师
         * d_file : sm1516176127148.jpg
         * d_room : 男科
         * d_info : <p>士大夫</p>
         */

        private String d_id;
        private String d_name;
        private String d_duties;
        private String d_file;
        private String d_room;
        private String d_info;

        public String getD_id() {
            return d_id;
        }

        public void setD_id(String d_id) {
            this.d_id = d_id;
        }

        public String getD_name() {
            return d_name;
        }

        public void setD_name(String d_name) {
            this.d_name = d_name;
        }

        public String getD_duties() {
            return d_duties;
        }

        public void setD_duties(String d_duties) {
            this.d_duties = d_duties;
        }

        public String getD_file() {
            return d_file;
        }

        public void setD_file(String d_file) {
            this.d_file = d_file;
        }

        public String getD_room() {
            return d_room;
        }

        public void setD_room(String d_room) {
            this.d_room = d_room;
        }

        public String getD_info() {
            return d_info;
        }

        public void setD_info(String d_info) {
            this.d_info = d_info;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.d_id);
            dest.writeString(this.d_name);
            dest.writeString(this.d_duties);
            dest.writeString(this.d_file);
            dest.writeString(this.d_room);
            dest.writeString(this.d_info);
        }

        public DoctorListBean() {
        }

        protected DoctorListBean(Parcel in) {
            this.d_id = in.readString();
            this.d_name = in.readString();
            this.d_duties = in.readString();
            this.d_file = in.readString();
            this.d_room = in.readString();
            this.d_info = in.readString();
        }

        public static final Creator<DoctorListBean> CREATOR = new Creator<DoctorListBean>() {
            @Override
            public DoctorListBean createFromParcel(Parcel source) {
                return new DoctorListBean(source);
            }

            @Override
            public DoctorListBean[] newArray(int size) {
                return new DoctorListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) this.hospital_info, flags);
        dest.writeList(this.doctor_list);
    }

    public ClinicDetailBean() {
    }

    protected ClinicDetailBean(Parcel in) {
        this.hospital_info = in.readParcelable(HospitalInfoBean.class.getClassLoader());
        this.doctor_list = new ArrayList<DoctorListBean>();
        in.readList(this.doctor_list, DoctorListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<ClinicDetailBean> CREATOR = new Parcelable.Creator<ClinicDetailBean>() {
        @Override
        public ClinicDetailBean createFromParcel(Parcel source) {
            return new ClinicDetailBean(source);
        }

        @Override
        public ClinicDetailBean[] newArray(int size) {
            return new ClinicDetailBean[size];
        }
    };
}
