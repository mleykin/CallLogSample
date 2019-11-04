package com.example.maxim_leykin.calllogsample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CALL_LOG)) {
                // Show an explanation to the user
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, 0);
            }
        }
        else {
            Uri allCalls = Uri.parse("content://call_log/calls");
            Cursor c = managedQuery(allCalls, null, null, null, null);
            while (c.moveToNext()) {
                String num = c.getString(c.getColumnIndex(CallLog.Calls.NUMBER));// for  number
                String name = c.getString(c.getColumnIndex(CallLog.Calls.CACHED_NAME));// for name
                String duration = c.getString(c.getColumnIndex(CallLog.Calls.DURATION));// for duration
                int type = Integer.parseInt(c.getString(c.getColumnIndex(CallLog.Calls.TYPE)));// for call type, Incoming or out going.
                Toast.makeText(getApplicationContext(), num + name + duration + type, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Uri allCalls = Uri.parse("content://call_log/calls");
                    Cursor c = managedQuery(allCalls, null, null, null, null);
                    while (c.moveToNext()) {
                        String num = c.getString(c.getColumnIndex(CallLog.Calls.NUMBER));// for  number
                        String name = c.getString(c.getColumnIndex(CallLog.Calls.CACHED_NAME));// for name
                        String duration = c.getString(c.getColumnIndex(CallLog.Calls.DURATION));// for duration
                        int type = Integer.parseInt(c.getString(c.getColumnIndex(CallLog.Calls.TYPE)));// for call type, Incoming or out going.
                        Toast.makeText(getApplicationContext(), num + name + duration + type, Toast.LENGTH_LONG).show();
                    }
                } else {
                    // permission denied, boo! Disable the functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other permissions this app might request
        }
    }





}

