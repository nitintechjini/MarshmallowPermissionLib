package payment.unopay.in.permissionmodule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;

import java.util.ArrayList;

/**
 * Created by Nitin S.Mesta on 13/5/16.
 * // Copyright (c) 2016 Techjini Solutions. All rights reserved.
 */
public class DevicePermissionHandler{

    private Context mContext;
    private PermissionActionListener mPermissionActionListener;

    public DevicePermissionHandler(Context context, PermissionActionListener permissionActionListener) {
        this.mContext = context;
        this.mPermissionActionListener = permissionActionListener;

    }

    public boolean isPermissionEnabled(Permission permission) {
        return permission!=null?ContextCompat.checkSelfPermission(mContext, permission.getName()) != PackageManager.PERMISSION_GRANTED ? false : true:false;
    }

    public ArrayList<Permission> getMultiPermissionEnabledStatus(ArrayList<Permission> permissions) {
        if (permissions != null) {
            for (int permissionIndex = 0; permissionIndex < permissions.size(); permissionIndex++) {
                Permission permission = permissions.get(permissionIndex);
                if (permission != null) {
                    permission.setEnabled(isPermissionEnabled(permission));
                }
            }
        }
        return permissions;
    }

    public boolean hasDisabledPermission(ArrayList<Permission> permissions)
    {
        if (permissions != null) {
            for (Permission permission:permissions) {
                if (permission != null) {
                   if(!permission.isEnabled())
                   {
                       return true;
                   }
                }
            }
        }
        return false;
    }

    public  boolean hasMandatoryPermissionDisabled(ArrayList<Permission> permissions)
    {
        if (permissions != null) {
            for (Permission permission:permissions) {
                if (permission != null) {
                    if(!permission.isEnabled() && permission.isMandatory())
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }




    private BroadcastReceiver permissionInteractionBroadCast=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //TODO handle the requested permission
            if(intent!=null)
            {
                ArrayList<Permission> permissions =intent.getParcelableArrayListExtra("permissionList");
                permissions =getMultiPermissionEnabledStatus(permissions);
                if(mPermissionActionListener!=null)
                {
                    LocalBroadcastManager.getInstance(mContext).unregisterReceiver(this);
                    mPermissionActionListener.onMultiPermissionAction(permissions);
                }
            }

        }
    };

    public void requestUserPermission(ArrayList<Permission> permissions) {

        Intent permissionHandlerIntent=new Intent(mContext,PermissionHandler.class);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(permissionInteractionBroadCast,new IntentFilter("PERMISSION_ACTION"));
        permissionHandlerIntent.putParcelableArrayListExtra("permissionList", permissions);
        mContext.startActivity(permissionHandlerIntent);

    }





}
