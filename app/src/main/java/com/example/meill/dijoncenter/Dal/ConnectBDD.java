package com.example.meill.dijoncenter.Dal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by meill on 20/09/2017.
 */

public class ConnectBDD extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 1;
    public static final String  DATABASE_NAME ="MaDb.db";


    public Context context;
    public ConnectBDD(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Table
        db.execSQL("CREATE TABLE Parcours " +
                "(idParcours INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idLieuCine VARCHAR NOT NULL , " +
                "idLieuRest VARCHAR NOT NULL , " +
                "Nom VARCHAR NULL , " +
                "Date DATETIME NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
      /*  while(oldVersion<newVersion)
        {
            if(oldVersion==2){Versionn2(db); break;}
            if(oldVersion==3){ break;}
            if(oldVersion==4){ break;}
            if(oldVersion==5){ break;}
            if(oldVersion==6){ break;}
            if(oldVersion==7){ break;}
            oldVersion+=1;
        }*/
    }

    public void Versionn2(SQLiteDatabase db)
    {
        //db.execSQL("New script");
    }
}
