package com.hyphenate.easeui.runtimepermissions;

import android.Manifest;

/**
 * Created by JunChen on 2018/1/25.
 * Remarks
 */

public class PermissionsAPI {
    public static final String[] photoPermissions={Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
    public static final String[] videoPerms = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO};
    public static final String[] recordingerms = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO};
    public static final String[] carlendarPermissions = {Manifest.permission.READ_CALENDAR,Manifest.permission.WRITE_CALENDAR};
    public static final String[] callPhonePermissions = {Manifest.permission.CALL_PHONE};
}
