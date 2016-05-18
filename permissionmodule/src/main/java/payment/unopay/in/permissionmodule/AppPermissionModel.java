package payment.unopay.in.permissionmodule;

import android.Manifest;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nitin S.Mesta on 12/5/16.
 * Copyright (c) 2016 Techjini Solutions. All rights reserved.
 */
public class AppPermissionModel {
    private static AppPermissionModel mAppPermissionModel;
    private static HashMap<String, AppPermission> mAppPermissionList;

    private AppPermissionModel() {

    }

    public ArrayList<AppPermission> getAppPermissionAsArrayList() {
        ArrayList<AppPermission> appPermissions = new ArrayList<>();
        appPermissions.addAll(mAppPermissionList.values());
        return appPermissions;
    }

    public static AppPermissionModel getInstance() {
        if (mAppPermissionModel == null) {
            mAppPermissionModel = new AppPermissionModel();
            mAppPermissionList = new HashMap<>();
        }
        return mAppPermissionModel;
    }

    public AppPermissionModel setReadSMSPermission(boolean isMandatory, String message) {
        AppPermission appPermission = new AppPermission();
        appPermission.setName(Manifest.permission.READ_SMS);
        appPermission.setMandatory(isMandatory);
        appPermission.setRequestDesc(message != null ? message : "Need send SMS permission");
        mAppPermissionList.put(Manifest.permission.READ_SMS, appPermission);
        return mAppPermissionModel;
    }

    public AppPermissionModel setSendSMSPermission(boolean isMandatory, String message) {
        AppPermission appPermission = new AppPermission();
        appPermission.setName(Manifest.permission.SEND_SMS);
        appPermission.setMandatory(isMandatory);
        appPermission.setRequestDesc(message != null ? message : "Need send SMS permission");
        mAppPermissionList.put(Manifest.permission.SEND_SMS, appPermission);
        return mAppPermissionModel;
    }

    public AppPermissionModel setReadPhoneStatePermission(boolean isMandatory, String message) {
        AppPermission appPermission = new AppPermission();
        appPermission.setName(Manifest.permission.READ_PHONE_STATE);
        appPermission.setMandatory(isMandatory);
        appPermission.setRequestDesc(message != null ? message : "Need send SMS permission");
        mAppPermissionList.put(Manifest.permission.READ_PHONE_STATE, appPermission);
        return mAppPermissionModel;
    }

    public AppPermissionModel setFineLocationPermission(boolean isMandatory, String message) {
        AppPermission appPermission = new AppPermission();
        appPermission.setName(Manifest.permission.ACCESS_FINE_LOCATION);
        appPermission.setMandatory(isMandatory);
        appPermission.setRequestDesc(message != null ? message : "Need send SMS permission");
        mAppPermissionList.put(Manifest.permission.ACCESS_FINE_LOCATION, appPermission);
        return mAppPermissionModel;
    }

    public AppPermissionModel setCoarseLocationPermission(boolean isMandatory, String message) {
        AppPermission appPermission = new AppPermission();
        appPermission.setName(Manifest.permission.ACCESS_COARSE_LOCATION);
        appPermission.setMandatory(isMandatory);
        appPermission.setRequestDesc(message != null ? message : "Need send SMS permission");
        mAppPermissionList.put(Manifest.permission.ACCESS_COARSE_LOCATION, appPermission);
        return mAppPermissionModel;
    }

    public AppPermissionModel setAddVoiceMailPermission(boolean isMandatory, String message) {
        AppPermission appPermission = new AppPermission();
        appPermission.setName(Manifest.permission.ADD_VOICEMAIL);
        appPermission.setMandatory(isMandatory);
        appPermission.setRequestDesc(message != null ? message : "Need send SMS permission");
        mAppPermissionList.put(Manifest.permission.ADD_VOICEMAIL, appPermission);
        return mAppPermissionModel;
    }

    public AppPermissionModel setBodySensorPermission(boolean isMandatory, String message) {
        AppPermission appPermission = new AppPermission();
        appPermission.setName(Manifest.permission.BODY_SENSORS);
        appPermission.setMandatory(isMandatory);
        appPermission.setRequestDesc(message != null ? message : "Need send SMS permission");
        mAppPermissionList.put(Manifest.permission.BODY_SENSORS, appPermission);
        return mAppPermissionModel;
    }

    public AppPermissionModel setCallPhonePermission(boolean isMandatory, String message) {
        AppPermission appPermission = new AppPermission();
        appPermission.setName(Manifest.permission.CALL_PHONE);
        appPermission.setMandatory(isMandatory);
        appPermission.setRequestDesc(message != null ? message : "Need send SMS permission");
        mAppPermissionList.put(Manifest.permission.CALL_PHONE, appPermission);
        return mAppPermissionModel;
    }

    public AppPermissionModel setCameraPermission(boolean isMandatory, String message) {
        AppPermission appPermission = new AppPermission();
        appPermission.setName(Manifest.permission.CAMERA);
        appPermission.setMandatory(isMandatory);
        appPermission.setRequestDesc(message != null ? message : "Need send SMS permission");
        mAppPermissionList.put(Manifest.permission.CAMERA, appPermission);
        return mAppPermissionModel;
    }

    public AppPermissionModel setGetAccountPermission(boolean isMandatory, String message) {
        AppPermission appPermission = new AppPermission();
        appPermission.setName(Manifest.permission.GET_ACCOUNTS);
        appPermission.setMandatory(isMandatory);
        appPermission.setRequestDesc(message != null ? message : "Need send SMS permission");
        mAppPermissionList.put(Manifest.permission.GET_ACCOUNTS, appPermission);
        return mAppPermissionModel;
    }

    public AppPermissionModel setProcessOutGoingCallPermission(boolean isMandatory, String message) {
        AppPermission appPermission = new AppPermission();
        appPermission.setName(Manifest.permission.PROCESS_OUTGOING_CALLS);
        appPermission.setMandatory(isMandatory);
        appPermission.setRequestDesc(message != null ? message : "Need send SMS permission");
        mAppPermissionList.put(Manifest.permission.PROCESS_OUTGOING_CALLS, appPermission);
        return mAppPermissionModel;
    }


}
