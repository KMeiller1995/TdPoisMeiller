package com.example.meill.dijoncenter;

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

import com.example.meill.dijoncenter.Adapter.DataLieuAdapter;
import com.example.meill.dijoncenter.Dal.ConnectBDD;
import com.example.meill.dijoncenter.Dal.ReadJson;
import com.example.meill.dijoncenter.Models.Lieu;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView LstLieu;
    ArrayList<Lieu> dataLieux;
    Button btnCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bdd
        ConnectBDD maCollecDb = new ConnectBDD(this);
        SQLiteDatabase db = maCollecDb.getWritableDatabase();

        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CreateParcourActivity.class);
                startActivity(i);
            };
        });

        LstLieu =(ListView)findViewById(R.id.listLieu);
        LstLieu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Lieu lieu = (Lieu) LstLieu.getAdapter().getItem(position);
                Intent i = new Intent(MainActivity.this, GoogleMapsActivity.class);
                i.putExtra("idLieu",lieu.getId());
                startActivity(i);
            }
        });
        dataLieux = new ArrayList<Lieu>();

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
                dataLieux=lieus;
                //Adapter
                DataLieuAdapter dl = new DataLieuAdapter(dataLieux,(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE));
                LstLieu.setAdapter(dl.getAdapter());
            }
        }.execute();







    }
}
