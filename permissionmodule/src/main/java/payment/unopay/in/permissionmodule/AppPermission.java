package payment.unopay.in.permissionmodule;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Nitin S.Mesta on 12/5/16.
 * // Copyright (c) 2016 Techjini Solutions. All rights reserved.
 */
public class AppPermission implements Parcelable {

    private String name;
    private boolean isMandatory;
    private String requestDesc;
    private boolean isEnabled;


    protected AppPermission(Parcel in) {
        name = in.readString();
        isMandatory = in.readByte() != 0;
        requestDesc = in.readString();
        isEnabled = in.readByte() != 0;
    }

    public static final Creator<AppPermission> CREATOR = new Creator<AppPermission>() {
        @Override
        public AppPermission createFromParcel(Parcel in) {
            return new AppPermission(in);
        }

        @Override
        public AppPermission[] newArray(int size) {
            return new AppPermission[size];
        }
    };

    public AppPermission() {

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
