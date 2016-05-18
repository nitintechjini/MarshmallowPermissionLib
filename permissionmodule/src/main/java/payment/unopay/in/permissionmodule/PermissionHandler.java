package payment.unopay.in.permissionmodule;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class PermissionHandler extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private static final int MULTI_PERMISSION_REQUEST_CODE = 1;
    private static final int APP_SETTING_REQUEST_CODE = 2;
    private AlertDialog.Builder dialogBuilder;
    private ArrayList<AppPermission> appPermissions=new ArrayList<>();
    private ArrayList<AppPermission> mRationaleList = new ArrayList<>(), mPermissionList = new ArrayList<>();
    private String permissionDescription = "";
    private HashMap<String, AppPermission> mRequestedAppPermissions = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_handler);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.get("permissionList")!=null) {
            appPermissions=bundle.getParcelableArrayList("permissionList");
            if (appPermissions != null) {
                for (AppPermission appPermission : appPermissions) {
                    if (appPermission != null) {
                        mRequestedAppPermissions.put(appPermission.getName(), appPermission);
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, appPermission.getName())) {
                            permissionDescription += appPermission.getRequestDesc() + "\n";
                            mRationaleList.add(appPermission);
                        } else {
                            mPermissionList.add(appPermission);
                        }
                    }
                }


                if (mPermissionList.size() > 0) {
                    ActivityCompat.requestPermissions(this, getMultiPermissionNameAsString(mPermissionList), MULTI_PERMISSION_REQUEST_CODE);
                } else if (mRationaleList.size() > 0 && !permissionDescription.trim().equals("")) {
                    showRationaleDialog();
                }


            }
        }else {
            finish();
        }

    }


    private String[] getMultiPermissionNameAsString(ArrayList<AppPermission> appPermissions) {
        String[] permissionArray = new String[appPermissions != null ? appPermissions.size() : 0];
        int index = 0;
        for (AppPermission appPermission : appPermissions) {
            permissionArray[index++] = appPermission.getName();
        }
        return permissionArray;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==APP_SETTING_REQUEST_CODE)
        {
            sendActionBroadCast(appPermissions);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MULTI_PERMISSION_REQUEST_CODE) {
            if (grantResults != null && grantResults.length > 0) {
                ArrayList<AppPermission> appPermissions = new ArrayList<>();
                for (int permissionIndex = 0; permissionIndex < permissions.length; permissionIndex++) {
                    AppPermission appPermission = mRequestedAppPermissions.get(permissions[permissionIndex]);
                    appPermission.setEnabled(grantResults[0] == PackageManager.PERMISSION_GRANTED ? true : false);
                    appPermissions.add(appPermission);
                }

                sendActionBroadCast(appPermissions);

            } else if (mRationaleList.size() > 0) {
                showRationaleDialog();
            }else {
                sendActionBroadCast(appPermissions);
            }

        }

    }

    public void launchAppSettings(String packageName) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", packageName, null);
        intent.setData(uri);
        startActivityForResult(intent,APP_SETTING_REQUEST_CODE);

    }

    private void sendActionBroadCast(ArrayList<AppPermission> appPermissions) {
        Intent resultIntent=new Intent();
        resultIntent.setAction("PERMISSION_ACTION");
        resultIntent.putParcelableArrayListExtra("permissionList",appPermissions);
        LocalBroadcastManager.getInstance(this).sendBroadcast(resultIntent);
        finish();
    }

    private void showRationaleDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Permission Required");
        dialogBuilder.setMessage(permissionDescription);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                launchAppSettings(PermissionHandler.this.getPackageName());
            }
        });
        dialogBuilder.setNegativeButton("Not now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendActionBroadCast(appPermissions);
            }
        });

        dialogBuilder.show();
    }
}
