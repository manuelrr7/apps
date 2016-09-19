package com.example.mrrodriguez.trianadvisor.interfaces;

import com.example.mrrodriguez.trianadvisor.models.Usuario;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Query;

/**
 * Created by manuel on 14/12/2015.
 */
public interface TdatosUsuarioAPI {

    @Headers({
            "X-Parse-Application-Id: Usqpw9Za6WcJEWQGtjra1JqNWimf1SMPsVwQ2yWy",
            "X-Parse-REST-API-Key: 4sZHPDkPA4NlZAAIVBVzGXIpLk59IpMwKHX4TTqR",
    })
    @GET("/1/users/me")
    Call<Usuario> obtenerDatos(@Header("X-Parse-Session-Token") String header);

}
