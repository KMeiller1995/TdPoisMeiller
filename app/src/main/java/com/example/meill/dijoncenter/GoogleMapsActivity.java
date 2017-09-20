package com.example.meill.dijoncenter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.TextView;

import com.example.meill.dijoncenter.Adapter.DataLieuAdapter;
import com.example.meill.dijoncenter.Dal.ReadJson;
import com.example.meill.dijoncenter.Models.Lieu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.UUID;

public class GoogleMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    UUID idLieu;
    Lieu lieu;
    TextView txt;
    TextView txt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
       /* SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);*/
        txt =(TextView)findViewById(R.id.txt);
        txt1=(TextView)findViewById(R.id.txt1);
        Intent i = getIntent();
        idLieu =(UUID)i.getSerializableExtra("idLieu");

        new AsyncTask<String, Void, Lieu>()
        {
            protected Lieu doInBackground(String... urls) {
                Lieu resData = null;
                try {
                    resData = new ReadJson().readById(idLieu);
                }
                catch (JSONException e) {e.printStackTrace();}
                catch (MalformedURLException e) {e.printStackTrace(); }
                catch (IOException e) { e.printStackTrace(); }
                return resData;
            }
            @Override
            protected void onPostExecute(Lieu _lieu) {
                lieu=_lieu;
                // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
                mapFragment.getMapAsync(GoogleMapsActivity.this);
                txt.setText(lieu.getName());
                txt1.setText(lieu.getType());
            }
        }.execute();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng Loisir = new LatLng(lieu.getLocation().getPosition().getLat(), lieu.getLocation().getPosition().getLon());
        mMap.addMarker(new MarkerOptions().position(Loisir).title(lieu.getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Loisir));
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(9), 10, null);
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(lieu.getLocation().getPosition().getLat(),lieu.getLocation().getPosition().getLon()) , 16.0f) );
    }
}
