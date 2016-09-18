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

import com.example.manuel.miniproyecto.Model.OpenWeatherDaily;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Favorito> listaFavoritos;


    Context contextoFavoritos;

    public FavoritosFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favoritos, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        Utils.cargarArray(getActivity());
        listaFavoritos = new ArrayList<Favorito>();


        for (int i = 0; i < Utils.listadoCiudadesFav.size(); i++) {
           new GetFavoritosTask().execute(Utils.listadoCiudadesFav.get(i));
        }

        return v;
    }


    private class GetFavoritosTask extends AsyncTask<String, Void, OpenWeatherDaily> {

        @Override
        protected OpenWeatherDaily doInBackground(String... params) {

            if(params!=null) {

                String URL_REQUEST = UrlApis.URL_BASE_REQUEST() + params[0].replace(" ", "%20").replace("España", "Spain") + UrlApis.URL_BASE_APPEND();
                OpenWeatherDaily result = null;
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

                try {
                    Reader r = Utils.Url2BufferedReader(URL_REQUEST);
                    result = gson.fromJson(r, OpenWeatherDaily.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }

            return null;
        }

        @Override
        protected void onPostExecute(OpenWeatherDaily openWeatherDaily) {

            if (openWeatherDaily != null) {

                String nombre_completo = openWeatherDaily.getName();

                listaFavoritos.add(new Favorito(nombre_completo));

                mAdapter = new FavoritoAdapter(listaFavoritos);
                mRecyclerView.setAdapter(mAdapter);


            } else {
                Toast.makeText(contextoFavoritos, "Error en la descarga y procesamiento de la información", Toast.LENGTH_LONG).show();
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

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}