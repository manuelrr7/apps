package com.example.manuel.miniproyecto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;


import com.example.manuel.miniproyecto.PojoG.Predictions;
import com.example.manuel.miniproyecto.PojoG.Region;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;


public class BuscarInicioActivity extends AppCompatActivity {

    //CLAVE LUISMI: AIzaSyCAfWAIEXkY9aNcEPhR6z0s2pFFiojQjqg
    //Mi clave: AIzaSyD8CPZ6CasOYaWLIJd2CJxROXwelLZaLGM

    AutoCompleteTextView ciudad;
    FloatingActionButton fab_buscar;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("mispreferencias", Context.MODE_APPEND);
        final SharedPreferences.Editor editor = prefs.edit();



        if(prefs.getBoolean("registrado", false)){
            this.finish();
            Intent i = new Intent(BuscarInicioActivity.this,MainActivity.class);
            String c = prefs.getString("ciudad_defecto",null);
            i.putExtra("titulo",c.replace(" ","").replace("España","Spain"));
            startActivity(i);
            prefs.edit().clear().commit();
        }else {

            setContentView(R.layout.activity_buscar_inicio);
            ciudad = (AutoCompleteTextView) findViewById(R.id.editCiudad);
            fab_buscar = (FloatingActionButton) findViewById(R.id.fab);

            ciudad.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String c = ciudad.getText().toString();
                    new GooglePlaceTask().execute(c);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            fab_buscar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ciudad.getText().toString().isEmpty()) {
                        Snackbar bar = Snackbar.make(v, "Debe buscar alguna ciudad", Snackbar.LENGTH_LONG)
                                .setAction("Action", null);
                        bar.show();

                    } else {
                        String c = ciudad.getText().toString().replace(" ", "").replace("España","Spain");
                        Intent i = new Intent(BuscarInicioActivity.this, MainActivity.class);

                        editor.putBoolean("registrado",true);

                        editor.putString("ciudad_defecto", c);
                        editor.apply();
                        //new GcmRegistrationAsyncTask(BuscarInicioActivity.this).execute(c);

                        Utils.listadoCiudadesFav.add(c);
                        Utils.guardarArray(BuscarInicioActivity.this);

                        i.putExtra("titulo", c);
                        startActivity(i);
                        ciudad.setText("");

                        BuscarInicioActivity.this.finish();

                    }
                }
            });
        }
    }

    //ASYNTASK QUE REALIZA LA CONSULTA AL API DE GOOGLE PLACES

    class GooglePlaceTask extends AsyncTask<String, Void, List<Predictions>> {

        @Override
        protected List<Predictions> doInBackground(String... params) {
            List<Predictions> result = null;
            URL url = null;

            try {
                if(params[0] != null) {

                    url = new URL(UrlApis.URL_GOOGLE_PLACES_BASE() + params[0].replace(" ","").trim()
                            + UrlApis.URL_GOOGLE_PLACES_APPEND());

                    BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()));
                    Gson gson = new Gson();
                    Region region = gson.fromJson(r, Region.class);
                    result = Arrays.asList(region.getPredictions());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(List<Predictions> predictionses) {
            super.onPostExecute(predictionses);
            if (predictionses != null) {
                //ciudad.setAdapter(new GoogleplacesAdapter(BuscarInicioActivity.this, android.R.layout.simple_list_item_1,predictionses));
                ciudad.setAdapter(new GooglePersonalizadoAdapter(BuscarInicioActivity.this,predictionses));
                ciudad.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //String str = (String) parent.getItemAtPosition(position);
                        Predictions str = (Predictions) parent.getItemAtPosition(position);
                        ciudad.setText(str.getDescription());
                    }
                });

            }
        }
    }
}