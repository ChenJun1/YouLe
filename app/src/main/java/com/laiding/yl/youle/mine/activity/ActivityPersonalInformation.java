package com.laiding.yl.youle.mine.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.mine.activity.view.IPersnonalInformation;
import com.laiding.yl.youle.mine.presenter.PresenterPersonalInformation;
import com.sunfusheng.glideimageview.GlideImageView;
import com.vondear.rxtools.RxPhotoTool;
import com.vondear.rxtools.RxSPTool;
import com.vondear.rxtools.view.dialog.RxDialogChooseImage;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.vondear.rxtools.view.dialog.RxDialogChooseImage.LayoutType.TITLE;

/**
 * Created by JunChen on 2018/1/22.
 * Remarks 个人资料
 */

public class ActivityPersonalInformation extends MyBaseActivity implements IPersnonalInformation {
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

    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityPersonalInformation.class);
        context.startActivity(starter);
    }

    PresenterPersonalInformation presenter = new PresenterPersonalInformation(this, this);
    private static final int PRC_PHOTO_PICKER = 1;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_personal_information;
    }

    @Override
    protected void init() {
        setTitle("个人资料");
        isBack(true);
    }

    @Override
    protected void initBundleData() {

    }

    @OnClick({R.id.ll_update_avatar, R.id.ll_nike_name, R.id.ll_gender, R.id.ll_birthday, R.id.ll_name, R.id.tv_update_phone, R.id.ll_location, R.id.ll_detail_location, R.id.ll_postal, R.id.ll_email})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_update_avatar:
                initCAMERA();
                break;
            case R.id.ll_nike_name:
                presenter.showDialogNikeName();
                break;
            case R.id.ll_gender:
                presenter.showDialogGender();
                break;
            case R.id.ll_birthday:
                presenter.showDialigBirthday();
                break;
            case R.id.ll_name:
                presenter.showDialogName();
                break;
            case R.id.tv_update_phone:
                ActivityUpdatePhone.start(mContext);
                break;
            case R.id.ll_location:
                presenter.showDialogLocation();
                break;
            case R.id.ll_detail_location:
                presenter.showDialogDetailLocation();
                break;
            case R.id.ll_postal:
                presenter.showDialogPostal();
                break;
            case R.id.ll_email:
                presenter.showDialogEmail();
                break;
        }
    }


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
//                RequestUpdateAvatar(new File(RxPhotoTool.getRealFilePath(mContext, RxPhotoTool.cropImageUri)));
                break;

            case UCrop.REQUEST_CROP://UCrop裁剪之后的处理
                if (resultCode == RESULT_OK) {
                    resultUri = UCrop.getOutput(data);
                    roadImageView(resultUri, mGivAvatar);
                    RxSPTool.putContent(mContext, "AVATAR", resultUri.toString());
                } else if (resultCode == UCrop.RESULT_ERROR) {
                    final Throwable cropError = UCrop.getError(data);
                }
                break;
            case UCrop.RESULT_ERROR://UCrop裁剪错误之后的处理
                final Throwable cropError = UCrop.getError(data);
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //从Uri中加载图片 并将其转化成File文件返回
    private File roadImageView(Uri uri, GlideImageView imageView) {
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
    public void setNikeName(CharSequence nikeName) {
        mTvNickName.setText(nikeName);
    }

    @Override
    public CharSequence getNikeName() {
        return mTvNickName.getText();
    }

    @Override
    public void setGender(CharSequence gender) {
        mTvGender.setText(gender);
    }

    @Override
    public CharSequence getGender() {
        return mTvGender.getText();
    }

    @Override
    public void setBirthday(CharSequence birthday) {
        mTvBirthday.setText(birthday);
    }

    @Override
    public CharSequence getBirthday() {
        return mTvBirthday.getText();
    }

    @Override
    public void setName(CharSequence name) {
        mTvName.setText(name);
    }

    @Override
    public CharSequence getName() {
        return mTvName.getText();
    }

    @Override
    public void setPhone(CharSequence phone) {
        mTvPhone.setText(phone);
    }

    @Override
    public CharSequence getPhone() {
        return mTvPhone.getText();
    }

    @Override
    public void setProvinceLocation(CharSequence location) {
        mTvLocationProvince.setText(location);
    }

    @Override
    public CharSequence getProvinceLocation() {
        return mTvLocationProvince.getText();
    }

    @Override
    public void setAreaLocation(CharSequence location) {
            mTvLocationArea.setText(location);
    }

    @Override
    public CharSequence getAreaLocation() {
        return mTvLocationArea.getText();
    }

    @Override
    public void setDetailLocation(CharSequence detailLocation) {
        mTvDetailLocation.setText(detailLocation);
    }

    @Override
    public CharSequence getDetailLocation() {
        return mTvDetailLocation.getText();
    }

    @Override
    public void setPostal(CharSequence postal) {
        mTvPostal.setText(postal);
    }

    @Override
    public CharSequence getPostal() {
        return mTvPostal.getText();
    }

    @Override
    public void setEmail(CharSequence email) {
        mTvEmail.setText(email);
    }

    @Override
    public CharSequence getEmail() {
        return mTvEmail.getText();
    }


}
