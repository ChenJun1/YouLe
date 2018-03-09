package com.laiding.yl.youle.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by JunChen on 2018/3/5.
 * Remarks
 */

public class MConstant {
    /**
     * 图片地址
     */
    public static final String IMGURL="http://back.51laiding.xyz/uploads/";
    /**
     * 备孕图片地址
     */
    public static final String PREGNANCYIMGURL="http://back.51laiding.xyz/photo/";
    /**
     * 诊疗记录图片地址
     */
    public static final String RECORDSIMGURL="http://m.51laiding.xyz/photo/";

    /**
     * 头像地址
     */
    public static final String AVATARIMGURL="http://m.51laiding.xyz/uploads/;";



    /**
     *  文件上传
     * @param files
     * @return
     */
    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<File> files) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (File file : files) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*;charset=utf-8"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("img[]", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }


}
