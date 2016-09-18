package com.example.manuel.miniproyecto;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by manuel on 15/11/2015.
 */
public class Favorito extends AppCompatActivity{

    TextView txtCiu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favoritos);

        txtCiu =(TextView) findViewById(R.id.txtCiudadf);
        cargarDatos();
    }


    public void cargarDatos(){


        SharedPreferences prefs = getSharedPreferences("MISFAVORITOS", Context.MODE_PRIVATE);

        txtCiu.setText(prefs.getString("Ciudad",""));
    }




}
