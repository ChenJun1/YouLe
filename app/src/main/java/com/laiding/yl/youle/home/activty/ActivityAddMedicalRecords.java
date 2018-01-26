package com.laiding.yl.youle.home.activty;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.laiding.yl.youle.R;
import com.laiding.yl.youle.base.MyBaseActivity;
import com.laiding.yl.youle.widget.photopicker.activity.BGAPhotoPickerActivity;
import com.laiding.yl.youle.widget.photopicker.activity.BGAPhotoPickerPreviewActivity;
import com.laiding.yl.youle.widget.photopicker.widget.BGASortableNinePhotoLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by JunChen on 2018/1/24.
 * Remarks  自定义添加诊疗记录
 */

public class ActivityAddMedicalRecords extends MyBaseActivity implements BGASortableNinePhotoLayout.Delegate {
    private static final int PRC_PHOTO_PICKER = 1;

    private static final int RC_CHOOSE_PHOTO = 1;
    private static final int RC_PHOTO_PREVIEW = 2;

    private static final String EXTRA_MOMENT = "EXTRA_MOMENT";

    @BindView(R.id.snpl_moment_add_photos)
    BGASortableNinePhotoLayout mSnplMomentAddPhotos;
    @BindView(R.id.et_moment_add_content)
    EditText mEtMomentAddContent;

    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityAddMedicalRecords.class);
        context.startActivity(starter);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_add_medical_records;
    }

    @Override
    protected void init() {
        initView();
        initBGASortabl();
    }

    private void initBGASortabl() {
        //设置最大图片数
        mSnplMomentAddPhotos.setMaxItemCount(9);
        // 设置拖拽排序控件的代理
        mSnplMomentAddPhotos.setDelegate(this);
    }

    private void initView() {
        setTitle("添加");
        isBack(true);
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    public void onClickAddNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, ArrayList<String> models) {
        choicePhotoWrapper();
    }

    @Override
    public void onClickDeleteNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        mSnplMomentAddPhotos.removeItem(position);
    }

    @Override
    public void onClickNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        Intent photoPickerPreviewIntent = new BGAPhotoPickerPreviewActivity.IntentBuilder(this)
                .previewPhotos(models) // 当前预览的图片路径集合
                .selectedPhotos(models) // 当前已选中的图片路径集合
                .maxChooseCount(mSnplMomentAddPhotos.getMaxItemCount()) // 图片选择张数的最大值
                .currentPosition(position) // 当前预览图片的索引
                .isFromTakePhoto(false) // 是否是拍完照后跳转过来
                .build();
        startActivityForResult(photoPickerPreviewIntent, RC_PHOTO_PREVIEW);
    }

    @Override
    public void onNinePhotoItemExchanged(BGASortableNinePhotoLayout sortableNinePhotoLayout, int fromPosition, int toPosition, ArrayList<String> models) {
        Toast.makeText(this, "排序发生变化", Toast.LENGTH_SHORT).show();
    }

    @AfterPermissionGranted(PRC_PHOTO_PICKER)
    private void choicePhotoWrapper() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
            File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "YoulePhoto");

            Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(this)
                    .cameraFileDir( takePhotoDir) // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话则不开启图库里的拍照功能
                    .maxChooseCount(mSnplMomentAddPhotos.getMaxItemCount() - mSnplMomentAddPhotos.getItemCount()) // 图片选择张数的最大值
                    .selectedPhotos(null) // 当前已选中的图片路径集合
                    .pauseOnScroll(false) // 滚动列表时是否暂停加载图片
                    .build();
            startActivityForResult(photoPickerIntent, RC_CHOOSE_PHOTO);
        } else {
            EasyPermissions.requestPermissions(this, "图片选择需要以下权限:\n\n1.访问设备上的照片\n\n2.拍照", PRC_PHOTO_PICKER, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RC_CHOOSE_PHOTO) {

            mSnplMomentAddPhotos.addMoreData(BGAPhotoPickerActivity.getSelectedPhotos(data));
        } else if (requestCode == RC_PHOTO_PREVIEW) {
            mSnplMomentAddPhotos.setData(BGAPhotoPickerPreviewActivity.getSelectedPhotos(data));
        }
    }

}
