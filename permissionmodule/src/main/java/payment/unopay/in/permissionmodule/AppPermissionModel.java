package payment.unopay.in.permissionmodule;

import android.Manifest;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nitin S.Mesta on 12/5/16.
 * Copyright (c) 2016 Techjini Solutions. All rights reserved.
 */

/**
 *
 * This class encloses all the dangerous permission in the android system .
 * User can set the list of permission required for him in his app and set the mandatory permission accordingly
 */
public class AppPermissionModel {
    private static AppPermissionModel mAppPermissionModel;
    private static HashMap<String, Permission> mAppPermissionList;

    private AppPermissionModel() {

    }

    public ArrayList<Permission> getAppPermissionAsArrayList() {
        ArrayList<Permission> permissions = new ArrayList<>();
        permissions.addAll(mAppPermissionList.values());
        return permissions;
    }

    public static AppPermissionModel getInstance() {
        mAppPermissionModel = new AppPermissionModel();
        mAppPermissionList = new HashMap<>();
        return mAppPermissionModel;
    }

    public AppPermissionModel setReadSMSPermission(boolean isMandatory, String message) {
        Permission permission = new Permission();
        permission.setName(Manifest.permission.READ_SMS);
        permission.setMandatory(isMandatory);
        permission.setRequestDesc(message != null ? message : "Need SMS permission");
        mAppPermissionList.put(Manifest.permission.READ_SMS, permission);
        return mAppPermissionModel;

    }

    public Permission getReadSMSPermission() {
         return mAppPermissionList.get(Manifest.permission.READ_SMS);
    }



    public AppPermissionModel setSendSMSPermission(boolean isMandatory, String message) {
        Permission permission = new Permission();
        permission.setName(Manifest.permission.SEND_SMS);
        permission.setMandatory(isMandatory);
        permission.setRequestDesc(message != null ? message : "Need SMS permission");
        mAppPermissionList.put(Manifest.permission.SEND_SMS, permission);
        return mAppPermissionModel;
    }
    public Permission getSendSMSPermission() {
        return mAppPermissionList.get(Manifest.permission.SEND_SMS);
    }

    public AppPermissionModel setReadPhoneStatePermission(boolean isMandatory, String message) {
        Permission permission = new Permission();
        permission.setName(Manifest.permission.READ_PHONE_STATE);
        permission.setMandatory(isMandatory);
        permission.setRequestDesc(message != null ? message : "Need Phone permission");
        mAppPermissionList.put(Manifest.permission.READ_PHONE_STATE, permission);
        return mAppPermissionModel;
    }

    public Permission getReadPhoneStatePermission() {
        return mAppPermissionList.get(Manifest.permission.READ_PHONE_STATE);
    }

    public AppPermissionModel setFineLocationPermission(boolean isMandatory, String message) {
        Permission permission = new Permission();
        permission.setName(Manifest.permission.ACCESS_FINE_LOCATION);
        permission.setMandatory(isMandatory);
        permission.setRequestDesc(message != null ? message : "Need Location permission");
        mAppPermissionList.put(Manifest.permission.ACCESS_FINE_LOCATION, permission);
        return mAppPermissionModel;
    }

    public Permission getFineLocationPermission() {
        return mAppPermissionList.get(Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public AppPermissionModel setCoarseLocationPermission(boolean isMandatory, String message) {
        Permission permission = new Permission();
        permission.setName(Manifest.permission.ACCESS_COARSE_LOCATION);
        permission.setMandatory(isMandatory);
        permission.setRequestDesc(message != null ? message : "Need Location permission");
        mAppPermissionList.put(Manifest.permission.ACCESS_COARSE_LOCATION, permission);
        return mAppPermissionModel;
    }

    public Permission getCoarseLocationPermission() {
        return mAppPermissionList.get(Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    public AppPermissionModel setAddVoiceMailPermission(boolean isMandatory, String message) {
        Permission permission = new Permission();
        permission.setName(Manifest.permission.ADD_VOICEMAIL);
        permission.setMandatory(isMandatory);
        permission.setRequestDesc(message != null ? message : "Need Phone permission");
        mAppPermissionList.put(Manifest.permission.ADD_VOICEMAIL, permission);
        return mAppPermissionModel;
    }

    public Permission getAddVoiceMailPermission()
    {
        return mAppPermissionList.get(Manifest.permission.ADD_VOICEMAIL);
    }

    public AppPermissionModel setBodySensorPermission(boolean isMandatory, String message) {
        Permission permission = new Permission();
        permission.setName(Manifest.permission.BODY_SENSORS);
        permission.setMandatory(isMandatory);
        permission.setRequestDesc(message != null ? message : "Need Sensor permission");
        mAppPermissionList.put(Manifest.permission.BODY_SENSORS, permission);
        return mAppPermissionModel;
    }

    public Permission getBodySensorPermission() {
        return mAppPermissionList.get(Manifest.permission.BODY_SENSORS);
    }

    public AppPermissionModel setCallPhonePermission(boolean isMandatory, String message) {
        Permission permission = new Permission();
        permission.setName(Manifest.permission.CALL_PHONE);
        permission.setMandatory(isMandatory);
        permission.setRequestDesc(message != null ? message : "Need Phone permission");
        mAppPermissionList.put(Manifest.permission.CALL_PHONE, permission);
        return mAppPermissionModel;
    }

    public Permission getCallPhonePermission() {
        return mAppPermissionList.get(Manifest.permission.CALL_PHONE);
    }

    public AppPermissionModel setCameraPermission(boolean isMandatory, String message) {
        Permission permission = new Permission();
        permission.setName(Manifest.permission.CAMERA);
        permission.setMandatory(isMandatory);
        permission.setRequestDesc(message != null ? message : "Need Camera permission");
        mAppPermissionList.put(Manifest.permission.CAMERA, permission);
        return mAppPermissionModel;
    }
    public Permission getCameraPermission() {
        return mAppPermissionList.get(Manifest.permission.CAMERA);
    }

    public AppPermissionModel setGetAccountPermission(boolean isMandatory, String message) {
        Permission permission = new Permission();
        permission.setName(Manifest.permission.GET_ACCOUNTS);
        permission.setMandatory(isMandatory);
        permission.setRequestDesc(message != null ? message : "Need Accounts permission");
        mAppPermissionList.put(Manifest.permission.GET_ACCOUNTS, permission);
        return mAppPermissionModel;
    }
    public Permission getAccountPermission() {
        return mAppPermissionList.get(Manifest.permission.GET_ACCOUNTS);
    }


    public AppPermissionModel setProcessOutGoingCallPermission(boolean isMandatory, String message) {
        Permission permission = new Permission();
        permission.setName(Manifest.permission.PROCESS_OUTGOING_CALLS);
        permission.setMandatory(isMandatory);
        permission.setRequestDesc(message != null ? message : "Need Phone permission");
        mAppPermissionList.put(Manifest.permission.PROCESS_OUTGOING_CALLS, permission);
        return mAppPermissionModel;
    }

    public Permission getProcessOutGoingCallPermission() {
        return mAppPermissionList.get(Manifest.permission.PROCESS_OUTGOING_CALLS);
    }


}
