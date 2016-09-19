package com.example.mrrodriguez.trianadvisor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by manuel on 14/12/2015.
 */
public class Foto
{
    @SerializedName("__type")
    @Expose
    private String __type;


    @SerializedName("name")
    @Expose
    private String name;


    @SerializedName("url")
    @Expose
    private String url;

    public String get__type ()
    {
        return __type;
    }

    public void set__type (String __type)
    {
        this.__type = __type;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "Class Foto [__type = "+__type+", name = "+name+", url = "+url+"]";
    }
}