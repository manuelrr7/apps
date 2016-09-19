package com.example.mrrodriguez.trianadvisor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrrodriguez.trianadvisor.interfaces.TloginAPI;
import com.example.mrrodriguez.trianadvisor.models.Usuario;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class EntradaActivity extends AppCompatActivity {

    EditText usuario,pass;
    Button buttonResg;
    String usu,pas, foto, nombre, token, tokeen;
    Context Context;
    TextView nom;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada);

        usuario = (EditText) findViewById(R.id.editTextUser);
        pass = (EditText) findViewById(R.id.editTextContra);
        buttonResg = (Button) findViewById(R.id.buttonEntrar);


        usu= usuario.getText().toString();
        pas=pass.getText().toString();


        buttonResg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new ResgisterTask().execute();


                Toast.makeText(EntradaActivity.this, "Usuario registrado correctamente", Toast.LENGTH_LONG);


            }
        });

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



    public class ResgisterTask extends AsyncTask<Void, Void, Usuario>{


        @Override
        protected Usuario doInBackground(Void... params) {


            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .baseUrl("https://api.parse.com")
                    .build();

            TloginAPI service = retrofit.create(TloginAPI.class);

            Call<Usuario> contacts = service.usuariolist(usu,pas);

            Response<Usuario> result = null;

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
        protected void onPostExecute(Usuario usuario) {

            Log.i("LOGIN", "Logueado");
            super.onPostExecute(usuario);



            Intent i = new Intent(EntradaActivity.this, MainActivity.class);

            nombre = usuario.getNombre();
            foto = usuario.getFoto().getUrl();
            token = usuario.getSessionToken();


            i.putExtra("foto", foto);
            i.putExtra("nombre", nombre);
            i.putExtra("token", token);

            prefs =getSharedPreferences("MiToken", Context.MODE_PRIVATE);
            prefs.getString("token", "token");
             editor = prefs.edit();
            editor.putString("token", "token");
            editor.commit();


            Toast.makeText(EntradaActivity.this, "Usuario registrado correctamente", Toast.LENGTH_LONG);
            startActivity(i);


        }
    }


}
