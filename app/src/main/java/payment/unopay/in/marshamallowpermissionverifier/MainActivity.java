package payment.unopay.in.marshamallowpermissionverifier;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

import payment.unopay.in.permissionmodule.Permission;
import payment.unopay.in.permissionmodule.AppPermissionModel;
import payment.unopay.in.permissionmodule.DevicePermissionHandler;
import payment.unopay.in.permissionmodule.PermissionActionListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PermissionActionListener {

    private DevicePermissionHandler mDevicePermissionHandler;
    private AppPermissionModel appPermissionModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.check_sms_permission).setOnClickListener(this);
        findViewById(R.id.check_location_permission).setOnClickListener(this);
        findViewById(R.id.check_both_permission).setOnClickListener(this);
        appPermissionModel = AppPermissionModel.getInstance()
                .setFineLocationPermission(true, "Location required")
                .setReadSMSPermission(true, "SMS is required");
        mDevicePermissionHandler = new DevicePermissionHandler(this, this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_sms_permission:
                if (!mDevicePermissionHandler.isPermissionEnabled(appPermissionModel.getReadSMSPermission())) {
                    ArrayList<Permission> smsPermissionList = new ArrayList<Permission>();
                    smsPermissionList.add(appPermissionModel.getReadSMSPermission());
                    mDevicePermissionHandler.requestUserPermission(smsPermissionList);
                }
                break;
            case R.id.check_location_permission:
                if (!mDevicePermissionHandler.isPermissionEnabled(appPermissionModel.getFineLocationPermission())) {
                    ArrayList<Permission> smsPermissionList = new ArrayList<Permission>();
                    smsPermissionList.add(appPermissionModel.getFineLocationPermission());
                    mDevicePermissionHandler.requestUserPermission(smsPermissionList);
                }
                break;
            case R.id.check_both_permission:
                ArrayList<Permission> permissionsList = mDevicePermissionHandler.getMultiPermissionEnabledStatus(appPermissionModel.getPermissionsAsArrayList());
                mDevicePermissionHandler.requestUserPermission(permissionsList);
                break;
            default:
                break;

        }
    }



    @Override
    public void onMultiPermissionAction(ArrayList<Permission> permissions) {
        if(permissions !=null) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Permission Status");
            StringBuffer messageBuffer=new StringBuffer();

            for(Permission permission : permissions)
            {
                messageBuffer.append(permission.getName());
                messageBuffer.append("-");
                messageBuffer.append( permission.isEnabled());
                messageBuffer.append("\n");
            }
            alertDialog.setMessage(messageBuffer.toString());
            alertDialog.show();
        }
    }
}
