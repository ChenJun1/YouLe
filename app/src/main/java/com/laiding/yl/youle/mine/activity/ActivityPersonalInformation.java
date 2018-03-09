package com.laiding.yl.youle.mine.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.laiding.yl.mvprxretrofitlibrary.manager.ActivityStackManager;
import com.laiding.yl.mvprxretrofitlibrary.utlis.LogUtils;
import com.laiding.yl.youle.MyApplication;
import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.login.activity.ActivityPhoneLogin;
import com.laiding.yl.youle.mine.activity.view.IPersnonalInformation;
import com.laiding.yl.youle.mine.entity.UserInfo;
import com.laiding.yl.youle.mine.fragment.FragmentMine;
import com.laiding.yl.youle.mine.presenter.PresenterPersonalInformation;
import com.laiding.yl.youle.utils.MConstant;
import com.sunfusheng.glideimageview.GlideImageView;
import com.vondear.rxtools.RxPhotoTool;
import com.vondear.rxtools.RxSPTool;
import com.vondear.rxtools.view.dialog.RxDialog;
import com.vondear.rxtools.view.dialog.RxDialogChooseImage;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.vondear.rxtools.view.dialog.RxDialogChooseImage.LayoutType.TITLE;

/**
 * Created by JunChen on 2018/1/22.
 * Remarks 个人资料
 */

public class ActivityPersonalInformation extends MyBaseActivity implements IPersnonalInformation {
    public final static int UPDATEPHONE = 0x100;

    @BindView(R.id.ll_top_head)
    LinearLayout mLlTopHead;
    @BindView(R.id.ll_update_avatar)
    LinearLayout mLlUpdateAvatar;
    @BindView(R.id.tv_nick_name)
    TextView mTvNickName;
    @BindView(R.id.ll_nike_name)
    LinearLayout mLlNikeName;
    @BindView(R.id.tv_gender)
    TextView mTvGender;
    @BindView(R.id.ll_gender)
    LinearLayout mLlGender;
    @BindView(R.id.tv_birthday)
    TextView mTvBirthday;
    @BindView(R.id.ll_birthday)
    LinearLayout mLlBirthday;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.ll_name)
    LinearLayout mLlName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_update_phone)
    TextView mTvUpdatePhone;
    @BindView(R.id.ll_phone)
    LinearLayout mLlPhone;
    @BindView(R.id.ll_location)
    LinearLayout mLlLocation;
    @BindView(R.id.tv_detail_location)
    TextView mTvDetailLocation;
    @BindView(R.id.ll_detail_location)
    LinearLayout mLlDetailLocation;
    @BindView(R.id.tv_postal)
    TextView mTvPostal;
    @BindView(R.id.ll_postal)
    LinearLayout mLlPostal;
    @BindView(R.id.tv_email)
    TextView mTvEmail;
    @BindView(R.id.ll_email)
    LinearLayout mLlEmail;
    @BindView(R.id.giv_avatar)
    GlideImageView mGivAvatar;
    @BindView(R.id.tv_location_province)
    TextView mTvLocationProvince;
    @BindView(R.id.tv_location_area)
    TextView mTvLocationArea;
    @BindView(R.id.tv_bar_right)
    TextView mTvBarRight;

    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityPersonalInformation.class);
        context.startActivity(starter);
    }

    PresenterPersonalInformation presenter = new PresenterPersonalInformation(this, this);
    private static final int PRC_PHOTO_PICKER = 1;
    private boolean isUpdate = false;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_personal_information;
    }

    @Override
    protected void init() {
        initHead();
        presenter.requestUserInfo();
    }

    private void initHead() {
        setTitle("个人资料");
        isBack(true);

        mTvBarRight.setText("保存");
        mTvBarRight.setBackgroundResource(R.drawable.btn_bg_medical_records);
        mTvBarRight.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initBundleData() {

    }

    @OnClick({R.id.ll_update_avatar, R.id.ll_nike_name, R.id.ll_gender, R.id.ll_birthday, R.id.ll_name,
            R.id.ll_phone, R.id.ll_location, R.id.ll_detail_location, R.id.ll_postal, R.id.ll_email
            , R.id.tv_bar_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_update_avatar:
                initCAMERA();
                break;
            case R.id.ll_nike_name:
                presenter.showDialogNikeName();
                isUpdate = true;
                break;
            case R.id.ll_gender:
                presenter.showDialogGender();
                isUpdate = true;
                break;
            case R.id.ll_birthday:
                presenter.showDialigBirthday();
                isUpdate = true;
                break;
            case R.id.ll_name:
                presenter.showDialogName();
                isUpdate = true;
                break;
            case R.id.ll_phone:
                Intent starter = new Intent(mContext, ActivityUpdatePhone2.class);
                startActivityForResult(starter, UPDATEPHONE);
                break;
            case R.id.ll_location:
                presenter.showDialogLocation();
                isUpdate = true;
                break;
            case R.id.ll_detail_location:
                presenter.showDialogDetailLocation();
                isUpdate = true;
                break;
            case R.id.ll_postal:
                presenter.showDialogPostal();
                isUpdate = true;
                break;
            case R.id.ll_email:
                presenter.showDialogEmail();
                isUpdate = true;
                break;
            case R.id.tv_bar_right:
                final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(mContext);//提示弹窗
                rxDialogSureCancel.getTitleView().setBackgroundResource(R.mipmap.ic_launcher);
                rxDialogSureCancel.setContent("是否保存个人信息？");
                rxDialogSureCancel.getSureView().setOnClickListener(v -> {
                    rxDialogSureCancel.cancel();
                    isUpdate = false;
                    presenter.requestUpdateUserInfo();
                });
                rxDialogSureCancel.getCancelView().setOnClickListener(v -> rxDialogSureCancel.cancel());
                rxDialogSureCancel.show();
                break;
        }
    }


    /**
     * 图片选择弹出
     */
    private void initDialogChooseImage() {
        RxDialogChooseImage dialogChooseImage = new RxDialogChooseImage(this, TITLE);
        dialogChooseImage.show();
    }

    /**
     * 拍照权限
     */
    @AfterPermissionGranted(PRC_PHOTO_PICKER)
    private void initCAMERA() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            initDialogChooseImage();
        } else {
            EasyPermissions.requestPermissions(this, "图片选择需要以下权限:\n\n1.访问设备上的照片\n\n2.拍照", PRC_PHOTO_PICKER, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == PRC_PHOTO_PICKER) {
            Toast.makeText(this, "您拒绝了「图片选择」所需要的相关权限!", Toast.LENGTH_SHORT).show();
        }
    }

    private Uri resultUri;
    private File mAvatarFile = null;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RxPhotoTool.GET_IMAGE_FROM_PHONE://选择相册之后的处理
                if (resultCode == RESULT_OK) {
//                    RxPhotoTool.cropImage(ActivityUser.this, );// 裁剪图片
                    initUCrop(data.getData());
                }

                break;
            case RxPhotoTool.GET_IMAGE_BY_CAMERA://选择照相机之后的处理
                if (resultCode == RESULT_OK) {
                   /* data.getExtras().get("data");*/
//                    RxPhotoTool.cropImage(ActivityUser.this, RxPhotoTool.imageUriFromCamera);// 裁剪图片
                    initUCrop(RxPhotoTool.imageUriFromCamera);
                }

                break;
            case RxPhotoTool.CROP_IMAGE://普通裁剪后的处理
                mGivAvatar.loadLocalCircleImage(RxPhotoTool.cropImageUri.getPath(), R.mipmap.ic_launcher_round);
                break;

            case UCrop.REQUEST_CROP://UCrop裁剪之后的处理
                if (resultCode == RESULT_OK) {
                    resultUri = UCrop.getOutput(data);
                    mAvatarFile = roadImageView(resultUri, mGivAvatar);
                    RxSPTool.putContent(mContext, "AVATAR", resultUri.toString());
                } else if (resultCode == UCrop.RESULT_ERROR) {
                    final Throwable cropError = UCrop.getError(data);
                }
                break;
            case UCrop.RESULT_ERROR://UCrop裁剪错误之后的处理
                final Throwable cropError = UCrop.getError(data);
                break;
            case UPDATEPHONE:// 修改成功手机号
                if (resultCode == RESULT_OK) {
                    String phone = data.getExtras().getString("PHONE");
                    mTvPhone.setText(phone);
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //从Uri中加载图片 并将其转化成File文件返回
    private File roadImageView(Uri uri, GlideImageView imageView) {
        isUpdate = true;
        imageView.loadLocalCircleImage(uri.getPath(), R.mipmap.ic_launcher_round);
        return (new File(RxPhotoTool.getImageAbsolutePath(this, uri)));
    }

    private void initUCrop(Uri uri) {
        //Uri destinationUri = RxPhotoTool.createImagePathUri(this);

        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
        long time = System.currentTimeMillis();
        String imageName = timeFormatter.format(new Date(time));

        Uri destinationUri = Uri.fromFile(new File(getCacheDir(), imageName + ".jpeg"));

        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //设置隐藏底部容器，默认显示
        //options.setHideBottomControls(true);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(this, R.color.colorPrimary));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryDark));

        //开始设置
        //设置最大缩放比例
        options.setMaxScaleMultiplier(5);
        //设置图片在切换比例时的动画
        options.setImageToCropBoundsAnimDuration(666);
        //设置裁剪窗口是否为椭圆
        //options.setOvalDimmedLayer(true);
        //设置是否展示矩形裁剪框
        // options.setShowCropFrame(false);
        //设置裁剪框横竖线的宽度
        //options.setCropGridStrokeWidth(20);
        //设置裁剪框横竖线的颜色
        //options.setCropGridColor(Color.GREEN);
        //设置竖线的数量
        //options.setCropGridColumnCount(2);
        //设置横线的数量
        //options.setCropGridRowCount(1);

        UCrop.of(uri, destinationUri)
                .withAspectRatio(1, 1)
                .withMaxResultSize(1000, 1000)
                .withOptions(options)
                .start(this);
    }

    @Override
    public void setAvatar(String url) {
        mGivAvatar.loadCircleImage(url, R.mipmap.ic_launcher_round);
    }

    @Override
    public File getAvatar() {
        return mAvatarFile;
    }

    @Override
    public void setNikeName(CharSequence nikeName) {
        mTvNickName.setText(nikeName);
    }

    @Override
    public CharSequence getNikeName() {
        return mTvNickName.getText().toString().trim();
    }

    @Override
    public void setGender(CharSequence gender) {
        mTvGender.setText(gender);
    }

    @Override
    public CharSequence getGender() {
        return mTvGender.getText().toString().trim();
    }

    @Override
    public void setBirthday(CharSequence birthday) {
        mTvBirthday.setText(birthday);
    }

    @Override
    public CharSequence getBirthday() {
        return mTvBirthday.getText().toString().trim();
    }

    @Override
    public void setName(CharSequence name) {
        mTvName.setText(name);
    }

    @Override
    public CharSequence getName() {
        return mTvName.getText().toString().trim();
    }

    @Override
    public void setPhone(CharSequence phone) {
        mTvPhone.setText(phone);
    }

    @Override
    public CharSequence getPhone() {
        return mTvPhone.getText().toString().trim();
    }

    @Override
    public void setProvinceLocation(CharSequence location) {
        mTvLocationProvince.setText(location);
    }

    @Override
    public CharSequence getProvinceLocation() {
        return mTvLocationProvince.getText().toString().trim();
    }

    @Override
    public void setAreaLocation(CharSequence location) {
        mTvLocationArea.setText(location);
    }

    @Override
    public CharSequence getAreaLocation() {
        return mTvLocationArea.getText().toString().trim();
    }

    @Override
    public void setDetailLocation(CharSequence detailLocation) {
        mTvDetailLocation.setText(detailLocation);
    }

    @Override
    public CharSequence getDetailLocation() {
        return mTvDetailLocation.getText().toString().trim();
    }

    @Override
    public void setPostal(CharSequence postal) {
        mTvPostal.setText(postal);
    }

    @Override
    public CharSequence getPostal() {
        return mTvPostal.getText().toString().trim();
    }

    @Override
    public void setEmail(CharSequence email) {
        mTvEmail.setText(email);
    }

    @Override
    public CharSequence getEmail() {
        return mTvEmail.getText().toString().trim();
    }

    @Override
    public void getUserInfoResult(UserInfo userInfo) {
        if (userInfo == null)
            return;
        saveUseInfo(userInfo);
        mGivAvatar.loadCircleImage(MConstant.AVATARIMGURL+ userInfo.getPhoto(), R.mipmap.ic_launcher_round);
        setNikeName(userInfo.getU_nname());
        setGender(userInfo.getU_sex() == null ? "男" : userInfo.getU_sex());
        setBirthday(userInfo.getU_birthday() == null ? "0" : userInfo.getU_birthday());
        setName(userInfo.getU_name());
        setPhone(userInfo.getU_phone());
        setProvinceLocation(userInfo.getU_region() == null ? "上海市" : userInfo.getU_region());
        setAreaLocation(userInfo.getU_city() == null ? "上海市" : userInfo.getU_city());
        setDetailLocation(userInfo.getU_address());
        setPostal(userInfo.getU_code());
        setEmail(userInfo.getU_email());
    }

    private void saveUseInfo(UserInfo userInfo){
        Intent intent=new Intent();
        intent.putExtra(FragmentMine.AVATAR, userInfo.getPhoto());
        intent.putExtra(FragmentMine.NNAME, userInfo.getU_nname());
        this.setResult(RESULT_OK,intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAvatarFile = null;
    }

    @Override
    public void isBackOnclik() {
        goBack();
    }

    @Override
    public void onBackPressed() {
        goBack();
    }

    /**
     * 页面退出
     */
    private void goBack() {
        if (isUpdate) {
            final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(mContext);//提示弹窗
            rxDialogSureCancel.getTitleView().setBackgroundResource(R.mipmap.ic_launcher);
            rxDialogSureCancel.setContent("修改未保存，是否离开？");
            rxDialogSureCancel.getSureView().setOnClickListener(v -> {
                super.isBackOnclik();
                rxDialogSureCancel.cancel();
            });
            rxDialogSureCancel.getCancelView().setOnClickListener(v -> rxDialogSureCancel.cancel());
            rxDialogSureCancel.show();
        } else {
            super.isBackOnclik();
        }
    }

}
