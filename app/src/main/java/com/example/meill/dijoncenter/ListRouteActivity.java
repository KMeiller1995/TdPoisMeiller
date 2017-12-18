package com.example.meill.dijoncenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.example.meill.dijoncenter.Adapter.DataLieuAdapter;
import com.example.meill.dijoncenter.Adapter.DataParcoursAdapter;
import com.example.meill.dijoncenter.Dal.ConnectBDD;
import com.example.meill.dijoncenter.Models.Parcours;

import java.sql.Date;
import java.util.ArrayList;

public class ListRouteActivity extends AppCompatActivity {

    ListView lstRoute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_route);
        lstRoute =(ListView)findViewById(R.id.lstRoute);

        ConnectBDD maCollecDb = new ConnectBDD(this);
        SQLiteDatabase db = maCollecDb.getWritableDatabase();

        ArrayList<Parcours> arrParcours = new ArrayList<Parcours>();
        Cursor c = db.query ("Parcours",new String[]{"*"},null,null,null,null,null);
        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {

                Parcours p = new Parcours
                        (
                                c.getInt(c.getColumnIndex("idParcours"))
                                ,null
                                ,null
                                ,c.getString(c.getColumnIndex("Nom"))
                                ,new Date(c.getLong(c.getColumnIndex("Date")))
                        );
                arrParcours.add(p);
                c.moveToNext();
            }
        }

        //Adapter
        DataParcoursAdapter dl = new DataParcoursAdapter(arrParcours,(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        lstRoute.setAdapter(dl.getAdapter());

    }
}
