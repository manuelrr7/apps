package com.example.mrrodriguez.trianadvisor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by manuel on 14/12/2015.
 */
public class Usuario
{
    @SerializedName("updateAt")
    @Expose
    private String updatedAt;


    @SerializedName("nombre")
    @Expose
    private String nombre;

    
    @SerializedName("sessionToken")
    @Expose
    private String sessionToken;


    @SerializedName("username")
    @Expose
    private String username;


    @SerializedName("email")
    @Expose
    private String email;


    @SerializedName("objectId")
    @Expose
    private String objectId;


    @SerializedName("createdAt")
    @Expose
    private String createdAt;


    @SerializedName("foto")
    @Expose
    private Foto foto;


    public String getUpdatedAt ()
    {
        return updatedAt;
    }

    public void setUpdatedAt (String updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public String getNombre ()
    {
        return nombre;
    }

    public void setNombre (String nombre)
    {
        this.nombre = nombre;
    }

    public String getSessionToken ()
    {
        return sessionToken;
    }

    public void setSessionToken (String sessionToken)
    {
        this.sessionToken = sessionToken;
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getObjectId ()
    {
        return objectId;
    }

    public void setObjectId (String objectId)
    {
        this.objectId = objectId;
    }

    public String getCreatedAt ()
    {
        return createdAt;
    }

    public void setCreatedAt (String createdAt)
    {
        this.createdAt = createdAt;
    }

    public Foto getFoto ()
    {
        return foto;
    }

    public void setFoto (Foto foto)
    {
        this.foto = foto;
    }

    @Override
    public String toString()
    {
        return "Class Usuario [updatedAt = "+updatedAt+", nombre = "+nombre+", sessionToken = "+sessionToken+", username = "+username+", email = "+email+", objectId = "+objectId+", createdAt = "+createdAt+", foto = "+foto+"]";
    }
}
