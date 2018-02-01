package com.laiding.yl.youle.mine.presenter;

import android.view.View;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBasePresenter;
import com.laiding.yl.youle.mine.activity.ActivityPersonalInformation;
import com.laiding.yl.youle.mine.activity.view.IPersnonalInformation;
import com.vondear.rxtools.view.dialog.RxDialogEditSureCancel;

/**
 * Created by JunChen on 2018/1/31.
 * Remarks
 */

public class PresenterPersonalInformation extends MyBasePresenter<IPersnonalInformation,ActivityPersonalInformation> {

    public PresenterPersonalInformation(IPersnonalInformation view, ActivityPersonalInformation activity) {
        super(view, activity);
    }

    /**
     * 昵称
     */
    public void showDialogNikeName(){
        final RxDialogEditSureCancel nikeNameDialog=new RxDialogEditSureCancel(getActivity());
        nikeNameDialog.getEditText().setHint("昵称");
        nikeNameDialog.getEditText().setText(getView().getNikeName());
        nikeNameDialog.getEditText().setSelection(getView().getNikeName().length());
        nikeNameDialog.getTitleView().setBackgroundResource(R.mipmap.ic_launcher);
        nikeNameDialog.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence nikeName= nikeNameDialog.getEditText().getText();
                getView().setNikeName(nikeName);
                nikeNameDialog.cancel();
            }
        });
        nikeNameDialog.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nikeNameDialog.cancel();
            }
        });
        nikeNameDialog.show();
    }


    /**
     * 性别
     */
    public void showDialogGender(){
        final RxDialogEditSureCancel nikeNameDialog=new RxDialogEditSureCancel(getActivity());
        nikeNameDialog.getEditText().setHint("昵称");
        nikeNameDialog.getEditText().setText(getView().getNikeName());
        nikeNameDialog.getEditText().setSelection(getView().getNikeName().length());
        nikeNameDialog.getTitleView().setBackgroundResource(R.mipmap.ic_launcher);
        nikeNameDialog.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence nikeName= nikeNameDialog.getEditText().getText();
                getView().setNikeName(nikeName);
                nikeNameDialog.cancel();
            }
        });
        nikeNameDialog.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nikeNameDialog.cancel();
            }
        });
        nikeNameDialog.show();
    }

    /**
     * 真实姓名
     */
    public void showDialogName(){
        final RxDialogEditSureCancel NameDialog=new RxDialogEditSureCancel(getActivity());
        NameDialog.getEditText().setHint("姓名");
        NameDialog.getEditText().setText(getView().getName());
        NameDialog.getEditText().setSelection(getView().getName().length());
        NameDialog.getTitleView().setBackgroundResource(R.mipmap.ic_launcher);
        NameDialog.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence Name= NameDialog.getEditText().getText();
                getView().setName(Name);
                NameDialog.cancel();
            }
        });
        NameDialog.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NameDialog.cancel();
            }
        });
        NameDialog.show();
    }

    /**
     * 详细地址
     */
    public void showDialogDetailLocation(){
        final RxDialogEditSureCancel detailLocationDialog=new RxDialogEditSureCancel(getActivity());
        detailLocationDialog.getEditText().setHint("详细地址");
        detailLocationDialog.getEditText().setText(getView().getDetailLocation());
        detailLocationDialog.getEditText().setSelection(getView().getDetailLocation().length());
        detailLocationDialog.getTitleView().setBackgroundResource(R.mipmap.ic_launcher);
        detailLocationDialog.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence Name= detailLocationDialog.getEditText().getText();
                getView().setDetailLocation(Name);
                detailLocationDialog.cancel();
            }
        });
        detailLocationDialog.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailLocationDialog.cancel();
            }
        });
        detailLocationDialog.show();
    }

    /**
     * 邮政编码
     */
    public void showDialogPostal(){
        final RxDialogEditSureCancel postalDialog=new RxDialogEditSureCancel(getActivity());
        postalDialog.getEditText().setHint("详细地址");
        postalDialog.getEditText().setText(getView().getPostal());
        postalDialog.getEditText().setSelection(getView().getPostal().length());
        postalDialog.getTitleView().setBackgroundResource(R.mipmap.ic_launcher);
        postalDialog.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence postal= postalDialog.getEditText().getText();
                getView().setPostal(postal);
                postalDialog.cancel();
            }
        });
        postalDialog.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postalDialog.cancel();
            }
        });
        postalDialog.show();
    }

    /**
     * 邮箱
     */
    public void showDialogEmail(){
        final RxDialogEditSureCancel emailDialog=new RxDialogEditSureCancel(getActivity());
        emailDialog.getEditText().setHint("邮箱");
        emailDialog.getEditText().setText(getView().getEmail());
        emailDialog.getEditText().setSelection(getView().getEmail().length());
        emailDialog.getTitleView().setBackgroundResource(R.mipmap.ic_launcher);
        emailDialog.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence email= emailDialog.getEditText().getText();
                getView().setEmail(email);
                emailDialog.cancel();
            }
        });
        emailDialog.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailDialog.cancel();
            }
        });
        emailDialog.show();
    }

}
