package com.example.meill.dijoncenter;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meill.dijoncenter.Adapter.DataLieuAdapter;
import com.example.meill.dijoncenter.Dal.ConnectBDD;
import com.example.meill.dijoncenter.Dal.ReadJson;
import com.example.meill.dijoncenter.Models.Lieu;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CreateParcourActivity extends AppCompatActivity {

    ListView LstLieu;
    ArrayList<Lieu> dataLieux;
    Button btnNext;
    Lieu REST ;
    Lieu CINE ;
    TextView textViewinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_parcour);

        textViewinfo =(TextView)findViewById(R.id.textView6);
        LstLieu =(ListView)findViewById(R.id.LstvLieu);
        LstLieu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Lieu lieu = (Lieu) LstLieu.getAdapter().getItem(position);

                DataLieuAdapter dl = new DataLieuAdapter(dataLieux,(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE));
                LstLieu.setAdapter(dl.getAdapter());
                if(REST==null && lieu!=null) { REST = lieu; GetJson();textViewinfo.setText("Choisir un cinema"); }
                else if(CINE==null && lieu!=null) { CINE = lieu; PushDatabase(); }

            }
        });
        dataLieux = new ArrayList<Lieu>();

        GetJson();
    }


    public void PushDatabase()
    {
        ConnectBDD maCollecDb = new ConnectBDD(this);
        SQLiteDatabase db = maCollecDb.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put("idParcours", null);
        values.put("idLieuCine", CINE.getId().toString());
        values.put("idLieuRest", REST.getId().toString());
        values.put("Nom", "Cinèma: " + CINE.getName() + " & Restaurant : " + REST.getName() );
        long id = db.insert("Parcours",null,values);

        Toast.makeText(this , "Parcours enregistré.", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(CreateParcourActivity.this, MainActivity.class);
        startActivity(i);


    }




    public void GetJson()
    {
        new AsyncTask<String, Void, ArrayList<Lieu>>()
        {
            protected ArrayList<Lieu> doInBackground(String... urls) {
                ArrayList<Lieu> resData = null;
                try {
                    resData = new ReadJson().read();
                }
                catch (JSONException e) {e.printStackTrace();}
                catch (MalformedURLException e) {e.printStackTrace(); }
                catch (IOException e) { e.printStackTrace(); }
                return resData;
            }
            @Override
            protected void onPostExecute(ArrayList<Lieu> lieus) {
                dataLieux = new ArrayList<Lieu>();
                for (Lieu l : lieus)
                {
                    if("REST".equals(l.getType()) && REST==null )
                    {dataLieux.add(l);}
                    else if("CINE".equals(l.getType()) && REST!=null )
                    {dataLieux.add(l);}
                }

                //Adapter
                DataLieuAdapter dl = new DataLieuAdapter(dataLieux,(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE));
                LstLieu.setAdapter(dl.getAdapter());
            }
        }.execute();
    }

}
