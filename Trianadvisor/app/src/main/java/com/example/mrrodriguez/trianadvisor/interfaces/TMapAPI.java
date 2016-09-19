package com.example.mrrodriguez.trianadvisor.interfaces;

/**
 * Created by manuel on 20/12/2015.
 */

import com.example.mrrodriguez.trianadvisor.models.Bar;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

/**
 * Created by rcarretero on 14/12/2015.
 */
public interface TMapAPI {
    @Headers({
            "X-Parse-Application-Id: Usqpw9Za6WcJEWQGtjra1JqNWimf1SMPsVwQ2yWy",
            "X-Parse-REST-API-Key: 4sZHPDkPA4NlZAAIVBVzGXIpLk59IpMwKHX4TTqR",
    })
    @GET("/1/classes/sitio")
    Call<Bar> listBarMap(@Query("latitude")Double latitud,@Query("longitude") Double longitud);
}
