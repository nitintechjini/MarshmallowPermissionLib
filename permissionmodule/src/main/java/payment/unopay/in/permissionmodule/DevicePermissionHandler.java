package payment.unopay.in.permissionmodule;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nitin S.Mesta on 13/5/16.
 * // Copyright (c) 2016 Techjini Solutions. All rights reserved.
 */
public class DevicePermissionHandler implements ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int MULTI_PERMISSION_REQUEST_CODE = 1;
    private Context mContext;
    private PermissionActionListener mPermissionActionListener;
    private HashMap<String, AppPermission> mRequestedAppPermissions;
    private ArrayList<AppPermission> mRationaleList = new ArrayList<>(), mPermissionList = new ArrayList<>();
    private String permissionDescription = "";
    private AlertDialog.Builder dialogBuilder;


    public DevicePermissionHandler(Context context, PermissionActionListener permissionActionListener) {
        this.mContext = context;
        this.mPermissionActionListener = permissionActionListener;
        this.mRequestedAppPermissions = new HashMap<>();
    }

    public boolean isPermissionEnabled(AppPermission permission) {
        return ContextCompat.checkSelfPermission(mContext, permission.getmPermissionName()) != PackageManager.PERMISSION_GRANTED ? false : true;
    }

    public ArrayList<AppPermission> getMultiPermissionEnabledStatus(ArrayList<AppPermission> permissions) {
        if (permissions != null) {
            for (int permissionIndex = 0; permissionIndex < permissions.size(); permissionIndex++) {
                AppPermission appPermission = permissions.get(permissionIndex);
                if (appPermission != null) {
                    appPermission.setmIsPermissionEnabled(isPermissionEnabled(appPermission));
                }
            }
        }
        return permissions;
    }

    public void requestUserPermission(ArrayList<AppPermission> appPermissions) {

        mRationaleList.clear();
        mPermissionList.clear();
        permissionDescription = "";


        if (appPermissions != null) {
            for (AppPermission appPermission : appPermissions) {
                if (appPermission != null) {
                    mRequestedAppPermissions.put(appPermission.getmPermissionName(), appPermission);
                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) mContext, appPermission.getmPermissionName())) {
                        permissionDescription += appPermission.getmPermissionRequestDesc() + "\n";
                        mRationaleList.add(appPermission);
                    } else {
                        mPermissionList.add(appPermission);
                    }
                }
            }


            if (mPermissionList.size() > 0) {
                ActivityCompat.requestPermissions((Activity) mContext, getMultiPermissionNameAsString(mPermissionList), MULTI_PERMISSION_REQUEST_CODE);
            } else if (mRationaleList.size() > 0 && !permissionDescription.trim().equals("")) {
                showRationaleDialog();
            }


        }
    }

    private void showRationaleDialog() {
        dialogBuilder = new AlertDialog.Builder(mContext);
        dialogBuilder.setTitle("Permission Required");
        dialogBuilder.setMessage(permissionDescription);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPermissionActionListener.onPermissionGrant();
                //TODO including the multi-permission list
            }
        });
        dialogBuilder.setNegativeButton("Not now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPermissionActionListener.onPermissionDeny();
                //TODO including the multi-permission list
            }
        });

        dialogBuilder.show();
    }

    private String[] getMultiPermissionNameAsString(ArrayList<AppPermission> appPermissions) {
        String[] permissionArray = new String[appPermissions != null ? appPermissions.size() : 0];
        int index = 0;
        for (AppPermission appPermission : appPermissions) {
            permissionArray[index++] = appPermission.getmPermissionName();
        }
        return permissionArray;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MULTI_PERMISSION_REQUEST_CODE) {
            if (grantResults != null && grantResults.length > 0) {
                ArrayList<AppPermission> appPermissions = new ArrayList<>();
                for (int permissionIndex = 0; permissionIndex < permissions.length; permissionIndex++) {
                    AppPermission appPermission = mRequestedAppPermissions.get(permissions[permissionIndex]);
                    appPermission.setmIsPermissionEnabled(grantResults[0] == PackageManager.PERMISSION_GRANTED ? true : false);
                    appPermissions.add(appPermission);
                }

                mPermissionActionListener.onMultiPermissionAction(appPermissions);


            } else if (mRationaleList.size() > 0) {
                showRationaleDialog();
            }

        }

    }
}
