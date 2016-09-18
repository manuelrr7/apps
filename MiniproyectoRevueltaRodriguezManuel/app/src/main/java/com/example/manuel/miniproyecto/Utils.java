package com.example.manuel.miniproyecto;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luismi on 04/11/2015.
 */
public class Utils {




    public static List<String> listadoCiudadesFav= new ArrayList<String>();

    public static SharedPreferences sharedPreferences;


    /*
        Método que devuelve el bufferedreader asociado a una URL.
     */

    public static BufferedReader Url2BufferedReader(String url) throws IOException {

        //return new BufferedReader(new InputStreamReader((new URL(url)).openStream()));
        URL Url = new URL(url);

        InputStream is = Url.openStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        return br;

    }


    /*
        Método que devuelve el contenido de una URL como una cadena de caracteres
     */

    public static String getStringContentFromUrl(String url) throws IOException {

        StringBuilder sb = new StringBuilder();

        BufferedReader br =  Url2BufferedReader(url);

        String line;

        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        br.close();

        return sb.toString();

    }




    public static boolean guardarArray(Context mContext) {
        //sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        sharedPreferences = mContext.getSharedPreferences("mispreferencias", Context.MODE_APPEND);
        SharedPreferences.Editor editar = sharedPreferences.edit();
        editar.putInt("arrayCiudadesSize", listadoCiudadesFav.size());

        for(int i=0;i<listadoCiudadesFav.size();i++) {
            editar.remove("Posicion_" + i);
            editar.putString("Posicion_" + i, listadoCiudadesFav.get(i));
        }
        return editar.commit();
    }



    public static void cargarArray(Context mContext){
        //sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        sharedPreferences = mContext.getSharedPreferences("mispreferencias", Context.MODE_APPEND);
        listadoCiudadesFav.clear();
        int size = sharedPreferences.getInt("arrayCiudadesSize", 0);
        for(int i=0;i<size;i++)
        {
            listadoCiudadesFav.add(sharedPreferences.getString("Posicion_" + i, null));
        }
    }


}
