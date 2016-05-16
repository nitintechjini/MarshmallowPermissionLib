package payment.unopay.in.permissionmodule;

/**
 * Created by Nitin S.Mesta on 12/5/16.
 * // Copyright (c) 2016 Techjini Solutions. All rights reserved.
 */
public class AppPermission {

    private String mPermissionName;
    private boolean mPermissionIsMandatory;
    private String mPermissionRequestDesc;
    private boolean mIsPermissionEnabled;


    public String getmPermissionName() {
        return mPermissionName;
    }

    public void setmPermissionName(String mPermissionName) {
        this.mPermissionName = mPermissionName;
    }

    public boolean ismPermissionIsMandatory() {
        return mPermissionIsMandatory;
    }

    public void setmPermissionIsMandatory(boolean mPermissionIsMandatory) {
        this.mPermissionIsMandatory = mPermissionIsMandatory;
    }

    public String getmPermissionRequestDesc() {
        return mPermissionRequestDesc;
    }

    public void setmPermissionRequestDesc(String mPermissionRequestDesc) {
        this.mPermissionRequestDesc = mPermissionRequestDesc;
    }

    public boolean ismIsPermissionEnabled() {
        return mIsPermissionEnabled;
    }

    public void setmIsPermissionEnabled(boolean mIsPermissionEnabled) {
        this.mIsPermissionEnabled = mIsPermissionEnabled;
    }
}
