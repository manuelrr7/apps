package com.example.mrrodriguez.trianadvisor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.mrrodriguez.trianadvisor.interfaces.TMapAPI;
import com.example.mrrodriguez.trianadvisor.interfaces.TactualizaValoracionAPI;
import com.example.mrrodriguez.trianadvisor.interfaces.TalmacenaComentarioAPI;
import com.example.mrrodriguez.trianadvisor.interfaces.TalmacenaValoracionAPI;
import com.example.mrrodriguez.trianadvisor.interfaces.TcompValoracionAPI;
import com.example.mrrodriguez.trianadvisor.interfaces.TdatosUsuarioAPI;
import com.example.mrrodriguez.trianadvisor.models.ActuValoracion;
import com.example.mrrodriguez.trianadvisor.models.Actualizado;
import com.example.mrrodriguez.trianadvisor.models.NuevaValoracion;
import com.example.mrrodriguez.trianadvisor.models.NuevoComentario;
import com.example.mrrodriguez.trianadvisor.models.ParaActualizar;
import com.example.mrrodriguez.trianadvisor.models.SitioValoraciones;
import com.example.mrrodriguez.trianadvisor.models.Usuario;
import com.example.mrrodriguez.trianadvisor.models.UsuarioValoracion;
import com.example.mrrodriguez.trianadvisor.models.Valoracion;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class ComentarioValoracionActivity extends AppCompatActivity {

    Button enviar;
    EditText editNuevoComentario;
    RatingBar rbValoracion;
    String sessionToken;
    String obj_id_usuario;
    String obj_id_sitio;
    UsuarioValoracion usuario;
    SitioValoraciones sitio;
    SharedPreferences prefs;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_comentario_valoracion);


            enviar = (Button) findViewById(R.id.btnEnviarComentario);
            editNuevoComentario = (EditText) findViewById(R.id.editTextComentario);
            rbValoracion = (RatingBar) findViewById(R.id.ratingBar);


            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                obj_id_sitio = bundle.getString("objectId");
            }

            prefs =getSharedPreferences("MiToken", Context.MODE_PRIVATE);
            sessionToken = prefs.getString("token","token");
            Log.i("VAL_SESSION_TOKEN", sessionToken);

            new ObtenerMisDatosTask().execute(sessionToken);

            rbValoracion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, final float rating, boolean fromUser) {

                    //Se crean los objetos que tendrá el cuerpo de la petición para la valoración.
                    usuario = new UsuarioValoracion("Pointer", "_User", obj_id_usuario);
                    sitio = new SitioValoraciones("Pointer", "sitio", obj_id_sitio);

                    float valoracion = rating;

                    Log.i("VAL_OBJECT_ID_USER", "" + usuario.getObjectId());
                    Log.i("VAL_OBJECT_ID_SITIO", "" + sitio.getObjectId());

                    final NuevaValoracion nueva_val = new NuevaValoracion();
                    nueva_val.setSitio(sitio);
                    nueva_val.setUsuario(usuario);
                    nueva_val.setValoracion(valoracion);

                    //JSON que hará de consulta para comprobar si se ha valorado un sitio.
                    //Esta cadena se le pasará encodeada al asyntasck que se ejecuta en la siguiente linea.
                    String JSON_BASE = "{\"sitio\": { \"__type\": \"Pointer\", \"className\": \"sitio\", \"objectId\": \"" + obj_id_sitio + "\" }, \"usuario\": {\"__type\": \"Pointer\", \"className\": \"_User\", \"objectId\": \"" + obj_id_usuario + "\"} }";

                    new ComprobarSiHeValorado() {

                        @Override
                        protected void onPostExecute(Valoracion valoracion) {
                            super.onPostExecute(valoracion);



                            if (valoracion.getResults().size() != 0) {

                                ActuValoracion actuValoracion = new ActuValoracion();
                                actuValoracion.setValoracion(rating);


                                Log.i("VAL_ID_VALORACION", valoracion.getResults().get(0).getObjectId());

                                ParaActualizar paraActualizar = new ParaActualizar();
                                paraActualizar.setActuValoracion(actuValoracion);
                                paraActualizar.setObjectId(valoracion.getResults().get(0).getObjectId());

                                new ActualizarValoracion().execute(paraActualizar);

                            } else {
                                new AlmacenarValoracion().execute(nueva_val);
                            }
                        }

                    }.execute(encodearCadena(JSON_BASE));

                }
            });

            enviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    usuario = new UsuarioValoracion("Pointer", "_User", obj_id_usuario);
                    sitio = new SitioValoraciones("Pointer", "sitio", obj_id_sitio);
                    String contenido_comentario = editNuevoComentario.getText().toString();

                    if (!contenido_comentario.isEmpty()) {
                        NuevoComentario nuevoComentario = new NuevoComentario();
                        nuevoComentario.setSitio(sitio);
                        nuevoComentario.setUsuario(usuario);
                        nuevoComentario.setComentario(contenido_comentario);

                        new AlmacenarComentario().execute(nuevoComentario);

                    } else {
                        Toast.makeText(ComentarioValoracionActivity.this, "Debe rellenar los campos", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


        private class ActualizarValoracion extends AsyncTask<ParaActualizar, Void, Actualizado> {
            @Override
            protected Actualizado doInBackground(ParaActualizar... params) {

                if (params != null) {
                    ActuValoracion actuValoracion = params[0].getActuValoracion();
                    String object = params[0].getObjectId();
                    String url ="https://api.parse.com";

                    Retrofit retrofit = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create(new Gson()))
                            .baseUrl(url)
                            .build();

                    TactualizaValoracionAPI service =retrofit.create(TactualizaValoracionAPI.class);
                    Call<Actualizado> call = service.actualizarValoracion(actuValoracion, object);

                    try {
                        call.execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Actualizado actualizado) {
                super.onPostExecute(actualizado);

                Log.i("VALORACIÓN ACTUALIZADA", "VALORACIÓN ACTUALIZADA");
            }
        }

        private class ComprobarSiHeValorado extends AsyncTask<String, Void, Valoracion> {
            @Override
            protected Valoracion doInBackground(String... params) {
                if (params != null) {

                    String url ="https://api.parse.com";

                    Retrofit retrofit = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create(new Gson()))
                            .baseUrl(url)
                            .build();

                    TcompValoracionAPI service =retrofit.create(TcompValoracionAPI.class);

                    Call<Valoracion> nuevaValoracionCall = service.comprobarSiSeHaValorado(params[0]);
                    Response<Valoracion> res = null;

                    try {
                        res = nuevaValoracionCall.execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert res != null;
                    if (res.code() == 200 || res.code() == 201) {
                        return res.body();
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            }
        }

        private class AlmacenarValoracion extends AsyncTask<NuevaValoracion, Void, Void> {
            @Override
            protected Void doInBackground(NuevaValoracion... params) {
                if (params != null) {
                    String url ="https://api.parse.com";

                    Retrofit retrofit = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create(new Gson()))
                            .baseUrl(url)
                            .build();

                    TalmacenaValoracionAPI service =retrofit.create(TalmacenaValoracionAPI.class);
                    Call<NuevaValoracion> call = service.almacenarValoracion(params[0]);
                    Response<NuevaValoracion> res = null;
                    try {
                        res = call.execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                } else {
                    return null;
                }
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.i("VALORACIÓN ALMACENADA","VALORACIÓN ALMACENADA");
            }
        }

        private class AlmacenarComentario extends AsyncTask<NuevoComentario, Void, String> {

            @Override
            protected String doInBackground(NuevoComentario... params) {

                String msg_ok = "Su opinión ha sido almacenada";
                String msg_error = "Error interno, estamos intentando solucionarlo";
                Log.i("COMENTARIO_ASYNTASCK", params[0].getComentario());
                String url ="https://api.parse.com";

                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create(new Gson()))
                        .baseUrl(url)
                        .build();

                TalmacenaComentarioAPI service =retrofit.create(TalmacenaComentarioAPI.class);
                Call<NuevoComentario> nuevoComentarioCall = service.almacenarComentario(params[0]);
                Response<NuevoComentario> nuevoComentarioResponse = null;
                try {
                    nuevoComentarioResponse = nuevoComentarioCall.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert nuevoComentarioResponse != null;
                if (nuevoComentarioResponse.code() == 200 || nuevoComentarioResponse.code() == 201) {
                    return msg_ok;
                } else {
                    return msg_error;
                }

            }

            @Override
            protected void onPostExecute(String msg) {
                super.onPostExecute(msg);
                Intent i = new Intent(ComentarioValoracionActivity.this, BarScrollActivity.class);
                i.putExtra("objectId", obj_id_sitio);
                startActivity(i);
                ComentarioValoracionActivity.this.finish();
                Toast.makeText(ComentarioValoracionActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        }

        private class ObtenerMisDatosTask extends AsyncTask<String, Void, com.example.mrrodriguez.trianadvisor.models.Usuario> {
            @Override
            protected com.example.mrrodriguez.trianadvisor.models.Usuario doInBackground(String... params) {

                if (params != null) {

                    String url ="https://api.parse.com";

                    Retrofit retrofit = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create(new Gson()))
                            .baseUrl(url)
                            .build();

                    TdatosUsuarioAPI service =retrofit.create(TdatosUsuarioAPI.class);
                    Call<com.example.mrrodriguez.trianadvisor.models.Usuario> usuarioCall = service.obtenerDatos(params[0]);
                    Response<com.example.mrrodriguez.trianadvisor.models.Usuario> response = null;
                    try {
                        response = usuarioCall.execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert response != null;

                    if (response.code() == 200 || response.code() == 201) {
                        return response.body();
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(com.example.mrrodriguez.trianadvisor.models.Usuario usuario) {
                super.onPostExecute(usuario);
                if (usuario != null) {
                    obj_id_usuario = usuario.getObjectId();
                }
            }
        }

        @Override
        public void onBackPressed() {
            super.onBackPressed();
            Intent i = new Intent(ComentarioValoracionActivity.this, BarScrollActivity.class);
            i.putExtra("objectId", obj_id_sitio);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }

public static String encodearCadena(String cadena){
    String cadena_encodeada = "";
    try {
        cadena_encodeada = URLEncoder.encode(cadena, "UTF-8");
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    return cadena_encodeada;
}
    }
