package com.example.meill.dijoncenter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.example.meill.dijoncenter.Broadcast.Permission;
import com.example.meill.dijoncenter.Dal.ConnectBDD;
import com.example.meill.dijoncenter.Dal.ReadJson;
import com.example.meill.dijoncenter.Models.Lieu;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.xml.datatype.Duration;


public class MainActivity extends AppCompatActivity {

    ListView LstLieu;
    ArrayList<Lieu> dataLieux;
    Button btnCreate;
    TextView txtnbPoid;
    Permission p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p = new Permission(); p.PermisionReceiveSMS(this);p.PermisionREAD_SMS(this);

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
                txtnbPoid = (TextView)findViewById(R.id.txtnbPoid);
                txtnbPoid.setText(""+lieus.size());
                dataLieux=lieus;
                //Adapter
                DataLieuAdapter dl = new DataLieuAdapter(dataLieux,(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE));
                LstLieu.setAdapter(dl.getAdapter());
            }
        }.execute();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        p.onRequestPermissionsResult(requestCode,grantResults);
    }
}

