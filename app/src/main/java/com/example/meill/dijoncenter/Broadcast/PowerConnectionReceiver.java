package com.example.meill.dijoncenter.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.widget.Toast;

import com.example.meill.dijoncenter.R;

/**
 * Created by meill on 25/10/2017.
 */

public class PowerConnectionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
       Toast.makeText(context, R.string.LevelBattery, Toast.LENGTH_SHORT).show();

    }
}

