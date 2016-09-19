package com.example.mrrodriguez.trianadvisor.interfaces;

import com.example.mrrodriguez.trianadvisor.models.NuevaValoracion;
import com.example.mrrodriguez.trianadvisor.models.NuevoComentario;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by manuel on 14/12/2015.
 */
public interface TalmacenaComentarioAPI {

   /** @Headers({
            "X-Parse-Application-Id: Usqpw9Za6WcJEWQGtjra1JqNWimf1SMPsVwQ2yWy",
            "X-Parse-REST-API-Key: 4sZHPDkPA4NlZAAIVBVzGXIpLk59IpMwKHX4TTqR",
    })*/
   @Headers("{Content-Type: application/json}")
   @POST("/1/classes/comentario")
   Call<NuevoComentario> almacenarComentario(@Body NuevoComentario resultComentario);

}
