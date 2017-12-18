package com.example.meill.dijoncenter.Adapter;

import android.app.Service;
import android.content.Intent;
import android.content.SyncAdapterType;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by meill on 18/12/2017.
 */

public class SyncService extends Service {

    // Storage for an instance of the sync adapter
    private static MySyncAdapter sSyncAdapter = null;
    // Object to use as a thread-safe lock
    private static final Object sSyncAdapterLock = new Object();
    /*
     * Instantiate the sync adapter object.
     */
    @Override
    public void onCreate() {
        /*
         * Create the sync adapter as a singleton.
         * Set the sync adapter as syncable
         * Disallow parallel syncs
         */
        synchronized (sSyncAdapterLock) {
            if (sSyncAdapter == null) {
                sSyncAdapter = new MySyncAdapter(getApplicationContext(), true);
            }
        }
    }
    /**
     * Return an object that allows the system to invoke
     * the sync adapter.
     *
     */
    @Override
    public IBinder onBind(Intent intent) {

        return sSyncAdapter.getSyncAdapterBinder();
    }
}



