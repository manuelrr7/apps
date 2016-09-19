package com.example.mrrodriguez.trianadvisor;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.mrrodriguez.trianadvisor.interfaces.TMapAPI;
import com.example.mrrodriguez.trianadvisor.models.Bar;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng bares;
    Marker marcadorBares;
    Double longitud = 37.389092;
    Double latitud = -5.984459;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


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
        new MapaTask().execute();




    }



    private class MapaTask extends AsyncTask<Void, Void, Bar> {

        @Override
        protected Bar doInBackground(Void... params) {

            String url ="https://api.parse.com";

            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .baseUrl(url)
                    .build();

            TMapAPI service =retrofit.create(TMapAPI.class);

            Call<Bar> contacts = service.listBarMap(longitud,latitud);

            Response<Bar> result = null;


            try {
                result = contacts.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (result != null) {
                return result.body();
            } else {
                return null;
            }
        }


        protected void onPostExecute(Bar bar) {
            //super.onPostExecute(bar);
            Double longBar;
            Double latBar;

            for (int i = 0; i < bar.getResults().length; i++){
                if (bar.getResults()[i].getCoordenadas() != null) {

                    latBar = Double.parseDouble(bar.getResults()[i].getCoordenadas().getLatitude());
                    longBar = Double.parseDouble(bar.getResults()[i].getCoordenadas().getLongitude());

                    bares = new LatLng(latBar,longBar);

                    marcadorBares = mMap.addMarker(new MarkerOptions().position(bares)
                            .draggable(true)
                            .title(bar.getResults()[i].getNombre())
                            .snippet(bar.getResults()[i].getCategoria()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bares, 15));
                    marcadorBares.showInfoWindow();
                    Log.i("MAP", "Latitud: " + latBar + "Longitud: " + latBar);
                }

            }

        }

    }
}