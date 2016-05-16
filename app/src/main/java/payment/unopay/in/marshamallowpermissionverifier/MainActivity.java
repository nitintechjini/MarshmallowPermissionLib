package payment.unopay.in.marshamallowpermissionverifier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.jar.Manifest;

import payment.unopay.in.permissionmodule.AppPermission;
import payment.unopay.in.permissionmodule.AppPermissionModel;
import payment.unopay.in.permissionmodule.DevicePermissionHandler;
import payment.unopay.in.permissionmodule.PermissionActionListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PermissionActionListener {

    ArrayList<AppPermission> appPermissions = new ArrayList<>();
    DevicePermissionHandler mDevicePermissionHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.check_sms_permission).setOnClickListener(this);
        findViewById(R.id.check_location_permission).setOnClickListener(this);
        findViewById(R.id.check_both_permission).setOnClickListener(this);
        AppPermissionModel appPermissionModel = AppPermissionModel.getInstance();
        appPermissionModel.setReadSMSPermission(true, "SMS is required");
        appPermissionModel.setFineLocationPermission(true, "Location required");
        appPermissions = appPermissionModel.getAppPermissionAsArrayList();
        mDevicePermissionHandler = new DevicePermissionHandler(this, this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_sms_permission:
                if (!mDevicePermissionHandler.isPermissionEnabled(appPermissions.get(1))) {
                    ArrayList<AppPermission> smsPermissionList = new ArrayList<AppPermission>();
                    smsPermissionList.add(appPermissions.get(1));
                    mDevicePermissionHandler.requestUserPermission(smsPermissionList);
                }
                break;
            case R.id.check_location_permission:
                if (!mDevicePermissionHandler.isPermissionEnabled(appPermissions.get(0))) {
                    ArrayList<AppPermission> smsPermissionList = new ArrayList<AppPermission>();
                    smsPermissionList.add(appPermissions.get(0));
                    mDevicePermissionHandler.requestUserPermission(smsPermissionList);
                }
                break;
            case R.id.check_both_permission:

                ArrayList<AppPermission> appPermissionsList = mDevicePermissionHandler.getMultiPermissionEnabledStatus(appPermissions);
                mDevicePermissionHandler.requestUserPermission(appPermissionsList);


                break;

        }
    }

    @Override
    public void onPermissionGrant() {
        Toast.makeText(MainActivity.this,"Grant",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionDeny() {
        Toast.makeText(MainActivity.this,"Deny",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMultiPermissionAction(ArrayList<AppPermission> appPermissions) {

    }
}
