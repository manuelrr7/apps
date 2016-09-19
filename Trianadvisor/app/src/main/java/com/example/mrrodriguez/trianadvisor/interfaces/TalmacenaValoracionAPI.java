package com.example.mrrodriguez.trianadvisor.interfaces;

import com.example.mrrodriguez.trianadvisor.models.ActuValoracion;
import com.example.mrrodriguez.trianadvisor.models.Actualizado;
import com.example.mrrodriguez.trianadvisor.models.NuevaValoracion;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by manuel on 14/12/2015.
 */
public interface TalmacenaValoracionAPI {

   /** @Headers({
            "X-Parse-Application-Id: Usqpw9Za6WcJEWQGtjra1JqNWimf1SMPsVwQ2yWy",
            "X-Parse-REST-API-Key: 4sZHPDkPA4NlZAAIVBVzGXIpLk59IpMwKHX4TTqR",
    })*/
   @Headers("{Content-Type: application/json}")
   @POST("/1/classes/valoracion")
   Call<NuevaValoracion> almacenarValoracion(@Body NuevaValoracion resultValoracion);

}
