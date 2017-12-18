package com.example.meill.dijoncenter.Adapter;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

/**
 * Created by meill on 18/12/2017.
 */

public class MySyncAdapter extends AbstractThreadedSyncAdapter{

    ContentResolver contentResolver;

    public MySyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        contentResolver= context.getContentResolver();
    }

    public MySyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        contentResolver= context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        //action ici

    }
}
