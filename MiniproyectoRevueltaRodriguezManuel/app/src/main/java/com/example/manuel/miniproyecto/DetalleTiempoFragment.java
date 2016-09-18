package com.example.manuel.miniproyecto;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Region;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView.OnQueryTextListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manuel.miniproyecto.Model.OpenWeatherDaily;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class DetalleTiempoFragment extends Fragment implements OnQueryTextListener, MenuItem.OnActionExpandListener{


    Boolean fav;
    Context i;
    private String nombre_ciudad;

    ProgressDialog progressDialog;

    TextView txtCiudad, txtFechaHora, txtTiempo, txtTemperatura, txtAmanecer, txtAnochecer, txtHumedad, txtWind;
    ImageView imgTiempo;
    ImageButton favo;


    public DetalleTiempoFragment() {

    }

    public DetalleTiempoFragment(String nombre_ciudad) {
        this.nombre_ciudad = nombre_ciudad;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detalle_tiempo, container, false);
        i=container.getContext();



        txtCiudad = (TextView) v.findViewById(R.id.txtCiudad);
        txtFechaHora = (TextView) v.findViewById(R.id.txtFechaHora);
        txtTiempo = (TextView) v.findViewById(R.id.txtTiempo);
        txtTemperatura = (TextView) v.findViewById(R.id.txtTemperatura);
        txtAmanecer = (TextView) v.findViewById(R.id.txtAmanecer);
        txtAnochecer = (TextView) v.findViewById(R.id.txtAnochecer);
        imgTiempo = (ImageView) v.findViewById(R.id.imgTiempo);
        txtHumedad = (TextView) v.findViewById(R.id.txtHumedad);
        txtWind = (TextView) v.findViewById(R.id.txtWind);
        favo = (ImageButton) v.findViewById(R.id.imageButton);


        Utils.cargarArray(getActivity());
        if(Utils.listadoCiudadesFav.contains(nombre_ciudad)){
            Picasso.with(getContext()).load(android.R.drawable.btn_star_big_on).into(favo);
            fav = true;
        }else{
            fav = false;
        }


        favo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //SharedPreferences prefs = getSharedPreferences("MISFAVORITOS", Context.MODE_PRIVATE);
                    //SharedPreferences.Editor editor = prefs.edit();
                    //editor.putString("Ciudad", txtCiudad.getText().toString());
                    //editor.commit();

                    //Si ya está en la lista, al pulsar sobre la estrella, lo borra de favoritos y
                    //coloca la estrella a apagada.

                    if(fav){
                        Log.i("Tamanyo", String.valueOf(Utils.listadoCiudadesFav.size()));

                        if(Utils.listadoCiudadesFav.contains(nombre_ciudad)){
                            Utils.listadoCiudadesFav.remove(nombre_ciudad);
                        }
                        Utils.guardarArray(getActivity());

                        Snackbar.make(container, "Borrado de favoritos", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        Picasso.with(getContext()).load(android.R.drawable.btn_star_big_off).into(favo);
                        fav = false;
                    }else{
                        if(!Utils.listadoCiudadesFav.contains(nombre_ciudad)) {

                            Utils.listadoCiudadesFav.add(nombre_ciudad);
                            Utils.guardarArray(getActivity());
                            Log.i("Añadido a favoritos", nombre_ciudad);
                            Snackbar.make(container, "Añadido a favoritos", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            Picasso.with(getContext()).load(android.R.drawable.btn_star_big_on).into(favo);
                            fav = true;
                        }
                    }
                }
        });


        new GetDataTask().execute();

        setHasOptionsMenu(true);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_tab_uno, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
       // new PuenteBuscar(crearURL(query)).execute();

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {


        return false;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        return false;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return false;
    }




    private class GetDataTask extends AsyncTask<Void, Void, OpenWeatherDaily> {

        @Override
        protected OpenWeatherDaily doInBackground(Void... params) {

            OpenWeatherDaily result = null;
            String URL_REQUEST = UrlApis.URL_BASE_REQUEST()+nombre_ciudad.replace(" ","%20").replace("España","Spain")+UrlApis.URL_BASE_APPEND();


            //Gson gson = new Gson();
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                @Override
                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    //return null;
                    Long dateAsLong = json.getAsLong();

                    dateAsLong = dateAsLong * 1000L;

                    return new Date(dateAsLong);


                }
            });

            Gson gson = gsonBuilder.create();

            try {
               // String URL_REQUEST = UrlApis.URL_BASE_REQUEST()+nombre_ciudad.replaceUrlApis(" ","%20").replace("España","Spain")+UrlApis.URL_BASE_APPEND();

                Reader r = Utils.Url2BufferedReader(URL_REQUEST);
                result = gson.fromJson(r, OpenWeatherDaily.class);
            } catch (IOException e) {
                e.printStackTrace();
            }


            return result;
        }

        @Override
        protected void onPostExecute(OpenWeatherDaily OpenWeatherDaily) {
            progressDialog.dismiss();

            DateFormat dfFechayHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dfFechayHora.setTimeZone(TimeZone.getTimeZone("GMT+1"));

            DateFormat dfHora = new SimpleDateFormat("HH:mm");
            dfHora.setTimeZone(TimeZone.getTimeZone("GMT+1"));


            if (OpenWeatherDaily!= null) {

                txtCiudad.setText(OpenWeatherDaily.getName());
                txtFechaHora.setText("Actualizado a \n" + dfFechayHora.format(OpenWeatherDaily.getDt()));
                txtTiempo.setText(OpenWeatherDaily.getWeather().get(0).getDescription());
                txtTemperatura.setText(OpenWeatherDaily.getMain().getTemp() + "º");
                txtAmanecer.setText(dfHora.format(OpenWeatherDaily.getSys().getSunrise()));
                txtAnochecer.setText(dfHora.format(OpenWeatherDaily.getSys().getSunset()));
                txtHumedad.setText(OpenWeatherDaily.getMain().getHumidity() + "%");
                txtWind.setText(OpenWeatherDaily.getWind().getSpeed()+" m/s");

                String url_image = UrlApis.URL_BASE_IMG_WEATHER() + OpenWeatherDaily.getWeather().get(0).getIcon() + UrlApis.EXTENSION_IMG_WEATHER();

                Picasso.with(i)
                        .load(url_image)
                        .fit()
                        .into(imgTiempo);

            } else {
                Toast.makeText(i, "Error en la descarga y procesamiento de la información", Toast.LENGTH_LONG).show();
            }


        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(i);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Descargando datos...");
            progressDialog.setMax(100);
            progressDialog.setCancelable(false);
            progressDialog.show();



        }
    }




    private class PuenteBuscar extends AsyncTask<String , Void, Region>{

        public PuenteBuscar(String s) {
        }

        @Override
        protected Region doInBackground(String... strings) {
            Gson gson = new Gson();
            Region lug = null;
            try {
                URL url = new URL(strings[0]);
                BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()));

                lug = gson.fromJson(bf, Region.class);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return lug;
        }
    }



}
