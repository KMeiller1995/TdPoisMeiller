package com.example.meill.dijoncenter.Broadcast;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.example.meill.dijoncenter.R;

/**
 * Created by meill on 25/10/2017.
 */

public class Permission {

    public final int NumRECEIVE_SMS = 1;
    public final int NumREAD_SMS = 2;
    Activity context;

    public void PermisionReceiveSMS(Activity c) {
        context = c;
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.RECEIVE_SMS)) { return; }
            else { ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.RECEIVE_SMS}, NumRECEIVE_SMS); }
        }
    }
    public void PermisionREAD_SMS(Activity c) {
        context = c;
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.READ_SMS)) { return; }
            else { ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_SMS}, NumREAD_SMS); }
        }
    }

    public void onRequestPermissionsResult(int requestCode, int[] grantResults) {
        switch (requestCode) {
            case NumRECEIVE_SMS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, R.string.permissionOK, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, R.string.permissionNO, Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
        switch (requestCode) {
            case NumREAD_SMS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, R.string.permissionOK, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, R.string.permissionNO, Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }

    }


}