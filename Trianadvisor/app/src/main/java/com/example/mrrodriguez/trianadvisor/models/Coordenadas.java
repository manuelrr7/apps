package com.example.mrrodriguez.trianadvisor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by manuel on 14/12/2015.
 */
public class Coordenadas
{
    @SerializedName("__type")
    @Expose
    private String __type;


    @SerializedName("longitude")
    @Expose
    private String longitude;


    @SerializedName("latitude")
    @Expose
    private String latitude;

    public String get__type ()
    {
        return __type;
    }

    public void set__type (String __type)
    {
        this.__type = __type;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    @Override
    public String toString()
    {
        return "Class Coordenadas [__type = "+__type+", longitude = "+longitude+", latitude = "+latitude+"]";
    }
}
