package com.example.mrrodriguez.trianadvisor.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by manuel on 18/12/2015.
 */
public class ResultsValoraciones {

    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    @SerializedName("sitio")
    @Expose
    private SitioValoraciones sitio;

    @SerializedName("valoracion")
    @Expose
    private String valoracion;

    @SerializedName("usuario")
    @Expose
    private UsuarioValoracion usuario;

    @SerializedName("objectId")
    @Expose
    private String objectId;

    @SerializedName("createdAt")
    @Expose
    private String createdAt;

    public String getUpdatedAt ()
    {
        return updatedAt;
    }

    public void setUpdatedAt (String updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public SitioValoraciones getSitio ()
    {
        return sitio;
    }

    public void setSitio (SitioValoraciones sitio)
    {
        this.sitio = sitio;
    }

    public String getValoracion ()
    {
        return valoracion;
    }

    public void setValoracion (String valoracion)
    {
        this.valoracion = valoracion;
    }

    public UsuarioValoracion getUsuario ()
    {
        return usuario;
    }

    public void setUsuario (UsuarioValoracion usuario)
    {
        this.usuario = usuario;
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

    @Override
    public String toString()
    {
        return "ClassPojo [updatedAt = "+updatedAt+", sitio = "+sitio+", valoracion = "+valoracion+", usuario = "+usuario+", objectId = "+objectId+", createdAt = "+createdAt+"]";
    }
}