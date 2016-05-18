package payment.unopay.in.permissionmodule;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nitin S.Mesta on 12/5/16.
 * // Copyright (c) 2016 Techjini Solutions. All rights reserved.
 */
public class Permission implements Parcelable {

    private String name;
    private boolean isMandatory;
    private String requestDesc;
    private boolean isEnabled;


    protected Permission(Parcel in) {
        name = in.readString();
        isMandatory = in.readByte() != 0;
        requestDesc = in.readString();
        isEnabled = in.readByte() != 0;
    }

    public static final Creator<Permission> CREATOR = new Creator<Permission>() {
        @Override
        public Permission createFromParcel(Parcel in) {
            return new Permission(in);
        }

        @Override
        public Permission[] newArray(int size) {
            return new Permission[size];
        }
    };

    public Permission() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.isMandatory = mandatory;
    }

    public String getRequestDesc() {
        return requestDesc;
    }

    public void setRequestDesc(String requestDesc) {
        this.requestDesc = requestDesc;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeByte((byte) (isMandatory ? 1 : 0));
        dest.writeString(requestDesc);
        dest.writeByte((byte) (isEnabled ? 1 : 0));
    }
}
