package com.example.manuel.miniproyecto;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manuel.miniproyecto.Model.Lugares;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleTiempoFragment extends Fragment implements View.OnClickListener, OnQueryTextListener, MenuItem.OnActionExpandListener{


    public final static String URL_REQUEST = "http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=741270e5e731e6a7be7513db5a7c4011&lang=es&units=metric";
    final String URL_BASE_IMG_WEATHER = "http://openweathermap.org/img/w/";
    final String EXTENSION_IMG_WEATHER = ".png";
    Context i;
    String city="";

    ProgressDialog progressDialog;

    TextView txtCiudad, txtFechaHora, txtTiempo, txtTemperatura, txtAmanecer, txtAnochecer;
    ImageView imgTiempo;

    SearchView busqueda;


    public DetalleTiempoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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




        new GetDataTask().execute();


        // CON ESTA LÍNEA DE CÓDIGO INDICO QUE ESTE FRAGMENT
        // TIENE UN MENÚ DE OPCIONES QUE DEBE SOBREESCRIBIR AL DEL ACTIVITY
        setHasOptionsMenu(true);



        return v;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_tab_uno, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.setTitle("Bla");
        busqueda = (SearchView) MenuItemCompat.getActionView(searchItem);
        busqueda.setQueryHint("Ciudad,Pais");
        busqueda.setOnQueryTextListener(this);


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

    @Override
    public void onClick(View view) {
/**
        if(view.getId()==R.id.imageButton){
            SharedPreferences prefs = getSharedPreferences("MISFAVORITOS", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("Ciudad", txtCiudad.getText().toString());
            editor.commit();

        }
*/

    }



    private class GetDataTask extends AsyncTask<Void, Void, OpenWeatherDaily> {

        @Override
        protected OpenWeatherDaily doInBackground(Void... params) {

            OpenWeatherDaily result = null;


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
                Reader r = Utils.Url2BufferedReader("http://api.openweathermap.org/data/2.5/weather?q=Sevilla,Spain&appid=741270e5e731e6a7be7513db5a7c4011&lang=es&units=metric");
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

                String url_image = URL_BASE_IMG_WEATHER + OpenWeatherDaily.getWeather().get(0).getIcon() + EXTENSION_IMG_WEATHER;

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




    private class PuenteBuscar extends AsyncTask<String , Void, Lugares>{

        public PuenteBuscar(String s) {
        }

        @Override
        protected Lugares doInBackground(String... strings) {
            Gson gson = new Gson();
            Lugares lug = null;
            try {
                URL url = new URL(strings[0]);
                BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream()));

                lug = gson.fromJson(bf, Lugares.class);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return lug;
        }
    }



    public String crearURL(String ciudad){
        String url = "https://maps.googleapis.com/maps/api/place/autocomplete/json?input="+ciudad.replace(" ", "")+"&types=(cities)&language=es_ES&key=AIzaSyDIy9WgHDi3pu0cLAslfHwgyMlqDgjzSUg";
        return url;
    }




}
