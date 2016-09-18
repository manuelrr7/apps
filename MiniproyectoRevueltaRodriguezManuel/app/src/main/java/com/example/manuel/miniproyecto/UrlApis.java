package com.example.manuel.miniproyecto;

/**
 * @author Manuel on 24/12/2015.
 */
public class UrlApis {

    /**URL API GOOGLE**/

    public static String KEY_GOOGLE(){
        return "AIzaSyCAfWAIEXkY9aNcEPhR6z0s2pFFiojQjqg";
    }

    public static String URL_GOOGLE_PLACES_BASE(){
        return "https://maps.googleapis.com/maps/api/place/autocomplete/json?input=";
    }

    public static String URL_GOOGLE_PLACES_APPEND(){
        return "&types=(cities)&language=es_ES&key="+UrlApis.KEY_GOOGLE();
    }


    public static String KEY_APIWEATHER(){

        return "3bcfcde9b7438aa7696f020ed75f5673";
    }

    public static String URL_BASE_REQUEST(){
        return "http://api.openweathermap.org/data/2.5/weather?q=";
    }

    public static String URL_BASE_APPEND(){
        return "&units=metric&appid="+UrlApis.KEY_APIWEATHER()+"&lang=es";
    }

    public static String URL_BASE_FIVEDAYS(){
        return "http://api.openweathermap.org/data/2.5/forecast?q=";
    }

    public static String URL_BASE_IMG_WEATHER(){
        return "http://openweathermap.org/img/w/";
    }

    public static String EXTENSION_IMG_WEATHER(){
        return ".png";
    }
}
