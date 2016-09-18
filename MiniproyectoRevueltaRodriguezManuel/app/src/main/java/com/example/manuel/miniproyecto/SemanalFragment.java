package com.example.manuel.miniproyecto;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.manuel.miniproyecto.ModelSemanal.OpenWeatherFiveDays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Manuel on 13/11/2015.
 */
public class SemanalFragment extends Fragment {



    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Semanal> ciudades;


    Context contextoProximosDias;

    private String nombre_ciudad;


    public SemanalFragment() {}

    public SemanalFragment(String nombre_ciudad) {this.nombre_ciudad = nombre_ciudad;}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment_semanal, container, false);


        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


        new GetFiveDaysTask().execute(nombre_ciudad.replace(" ","%20").replace("España","Spain"));

        return v;
    }


    //ASYNTASK QUE REALIZA LA CONSULTA DEL PRONOSTICO DE 5 DÍAS

    private class GetFiveDaysTask extends AsyncTask<String, Void, OpenWeatherFiveDays> {

        @Override
        protected OpenWeatherFiveDays doInBackground(String... params) {
            OpenWeatherFiveDays result = null;
            URL url = null;
            if(params!=null)

                try {
                    url = new URL(UrlApis.URL_BASE_FIVEDAYS()+params[0].replace(" ","").replace("España","Spain")+UrlApis.URL_BASE_APPEND());

                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                        @Override
                        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                            Long dateAsLong = json.getAsLong();
                            dateAsLong = dateAsLong * 1000L;
                            return new Date(dateAsLong);
                        }
                    });

                    Gson gson = gsonBuilder.create();
                    BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()));
                    result = gson.fromJson(r, OpenWeatherFiveDays.class);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            return result;
        }

        @Override
        protected void onPostExecute(OpenWeatherFiveDays listWeather) {
            if (listWeather != null) {

                ciudades = new ArrayList<Semanal>();


                for (int i = 0; i < listWeather.getList().size(); i = i + 8) {

                    String url_image = UrlApis.URL_BASE_IMG_WEATHER() + listWeather.getList().get(i).getWeather().get(0).getIcon() + UrlApis.EXTENSION_IMG_WEATHER();
                    String fecha = "Día "+listWeather.getList().get(i).getDtTxt().substring(8, 11);
                    String temp_max = String.valueOf(listWeather.getList().get(i).getMain().getTempMax());
                    String temp_min = String.valueOf(listWeather.getList().get(i).getMain().getTempMin());
                    String descrp = String.valueOf(listWeather.getList().get(i).getWeather().get(0).getDescription().toUpperCase());

                    ciudades.add(new Semanal(url_image,fecha, temp_max, temp_min, descrp));
                }

                mAdapter = new CiudadAdapter(ciudades);
                mRecyclerView.setAdapter(mAdapter);

            } else {
                Toast.makeText(contextoProximosDias, "Error en la descarga y procesamiento de la información", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}