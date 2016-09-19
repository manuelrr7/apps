package com.example.mrrodriguez.trianadvisor;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mrrodriguez.trianadvisor.interfaces.TalmacenaComentarioAPI;
import com.example.mrrodriguez.trianadvisor.interfaces.TrianadvisorBarDetAPI;
import com.example.mrrodriguez.trianadvisor.interfaces.TrianadvisorRatingAPI;
import com.example.mrrodriguez.trianadvisor.interfaces.TverComentariosAPI;
import com.example.mrrodriguez.trianadvisor.models.Comentario;
import com.example.mrrodriguez.trianadvisor.models.Results;
import com.example.mrrodriguez.trianadvisor.models.ResultsValoraciones;
import com.example.mrrodriguez.trianadvisor.models.Valoracion;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class BarScrollActivity extends AppCompatActivity {


    TextView direc,cate,desc;
    RatingBar valoracion;
    String objectId;
    CollapsingToolbarLayout tb;
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    ImageView imageViewToolbar;
    List<ValoracionPojoAdapter> lista_valoraciones;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_scroll);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        new BarScrollTask().execute();
        new ValoracionesTask().execute();
        //new ObtenerComentariosTask().execute();

        direc = (TextView) findViewById(R.id.textViewNombreDetalle);
        cate = (TextView)findViewById(R.id.textViewCategoria);
        desc = (TextView)findViewById(R.id.textViewDescripcionDetalle);
        valoracion = (RatingBar)findViewById(R.id.ratingBarDetalles);
        tb = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);

        imageViewToolbar =(ImageView) appBarLayout.findViewById(R.id.imageViewToolbar);

        //Inicializo los elementos del recyler que contendrá las valoraciones
        recyclerView = (RecyclerView) findViewById(R.id.recycler_valoraciones);
        recyclerView.setHasFixedSize(true);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Ejecutar AsyncTask

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BarScrollActivity.this, ComentarioValoracionActivity.class);
                startActivity(i);
            }
        });
    }

    private class BarScrollTask extends AsyncTask<Void, Void, Results> {


        @Override
        protected Results doInBackground(Void... params) {

            String url = "https://api.parse.com";
            //Recogida del objectId que le pasamos al intent en BarAdapter
            objectId = getIntent().getExtras().getString("objectId");
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .baseUrl(url)
                    .build();

            TrianadvisorBarDetAPI service =
                    retrofit.create(TrianadvisorBarDetAPI.class);

            Call<Results> contacts = service.mostrarUnBar(objectId);

            Response<Results> result = null;

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

        @Override
        protected void onPostExecute(Results bar) {
            //super.onPostExecute(bares);
            setSupportActionBar(toolbar);
            toolbar.setTitle(bar.getNombre());

            if (bar.getNombre() != null){
                objectId = bar.getObjectId();
                direc.setText(bar.getNombre());
                cate.setText(bar.getCategoria());
                desc.setText(bar.getDescripcion());


                Picasso.with(BarScrollActivity.this).load(bar.getFoto().getUrl()).fit().into(imageViewToolbar);
                //Url base  a la que se la pasa el objectId del sitio para obtener su valoraciones y comentarios.
                String URL_BASE = "{\"sitio\": { \"__type\": \"Pointer\", \"className\": \"sitio\", " +
                        "\"objectId\": \""+objectId+"\" } }";

                String url_comentarios = "";
                try {
                    url_comentarios = URLEncoder.encode(URL_BASE, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                //Ejecuto el asyntasck que devuelve los comentarios del sitio.
                new ObtenerComentariosTask().execute(url_comentarios);
            }



            Log.i("BAR", String.valueOf(bar));
        }



    }

    //Asynctask de valoraciones

    private class ValoracionesTask extends AsyncTask<Void, Void, Valoracion> {


        @Override
        protected Valoracion doInBackground(Void... params) {

            String url = "https://api.parse.com";
            //Recogida del objectId que le pasamos al intent en BarAdapter
            objectId = getIntent().getExtras().getString("objectId");
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .baseUrl(url)
                    .build();

            TrianadvisorRatingAPI service =
                    retrofit.create(TrianadvisorRatingAPI.class);

            String consulta="{\"sitio\": { \"__type\": \"Pointer\", \"className\": \"sitio\", \"objectId\": \""+objectId+"\" } }";

            try {
                consulta = URLEncoder.encode(consulta, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


            Call<Valoracion> contacts = service.listValoracion(consulta);

            Response<Valoracion> result = null;

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

        @Override
        protected void onPostExecute(Valoracion val) {
            //super.onPostExecute(bares);

            float media = 0;
            for (ResultsValoraciones v : val.getResults()) {
                media = media + Float.parseFloat(v.getValoracion());

            }

            media = media / val.getResults().size();
            Log.i("valoracion", String.valueOf(media));
            if(val.getResults().size() == 0){

            }
            Log.i("valoracion1", String.valueOf(media));
            valoracion.setRating(media);


        }

    }



    //AsyncTask que obtiene todos los comentarios de un sitio en concreto a través de su objectId
    private class ObtenerComentariosTask extends AsyncTask<String, Void, Comentario> {

        @Override
        protected Comentario doInBackground(String... params) {
            if (params != null) {
                String url ="https://api.parse.com";

                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create(new Gson()))
                        .baseUrl(url)
                        .build();

                TverComentariosAPI service =retrofit.create(TverComentariosAPI.class);
                Call<Comentario> comentarioCall = service.cargarComentariosUnSitio(params[0]);
                Response<Comentario> comentarioResponse = null;

                try {
                    comentarioResponse = comentarioCall.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert comentarioResponse != null;

                if(comentarioResponse.code() == 200 || comentarioResponse.code() == 201){
                    return comentarioResponse.body();
                }else{
                    return null;
                }
            } else {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Comentario comentario) {
            super.onPostExecute(comentario);



            if(comentario!=null) {

                lista_valoraciones = new ArrayList<>();

                for (int i = 0; i < comentario.getResults().size(); i++) {
                    if (comentario.getResults() != null) {

                        String nom_user_comentario = comentario.getResults().get(i).getUsuario().getNombre();
                        String coment = comentario.getResults().get(i).getComentario();
                        String foto = "";
                        if(comentario.getResults().get(i).getUsuario().getFoto().getUrl()  != null){
                            foto = comentario.getResults().get(i).getUsuario().getFoto().getUrl();
                        }else{
                            foto ="";
                        }
                        String fecha = comentario.getResults().get(i).getCreatedAt();
                        lista_valoraciones.add(new ValoracionPojoAdapter(foto, nom_user_comentario, coment, fecha));
                    }
                }
                adapter = new ValoracionAdapter(lista_valoraciones);
                recyclerView.setAdapter(adapter);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(BarScrollActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        this.finish();
    }

}
