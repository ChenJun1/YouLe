package com.hyphenate.easeui.runtimepermissions;

import android.Manifest;

/**
 * Created by JunChen on 2018/1/25.
 * Remarks
 */

public class PermissionsAPI {
    public static String[] photoPermissions={Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
    public static String[] videoPerms = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO};
    public static String[] recordingerms = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO};
    public static String[] duxiePermissions = {Manifest.permission.READ_CALENDAR,Manifest.permission.WRITE_CALENDAR};
}
