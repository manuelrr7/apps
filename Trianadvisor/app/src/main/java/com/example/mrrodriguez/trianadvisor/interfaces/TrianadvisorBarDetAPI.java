package com.example.mrrodriguez.trianadvisor.interfaces;


import com.example.mrrodriguez.trianadvisor.models.Results;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;


/**
 * Created by manuel on 15/12/2015.
 */
public interface TrianadvisorBarDetAPI {

    @Headers({
            "X-Parse-Application-Id: Usqpw9Za6WcJEWQGtjra1JqNWimf1SMPsVwQ2yWy",
            "X-Parse-REST-API-Key: 4sZHPDkPA4NlZAAIVBVzGXIpLk59IpMwKHX4TTqR",
    })
    @GET("/1/classes/sitio/{objectId}")
    Call<Results> mostrarUnBar(@Path("objectId") String objectId);
}
