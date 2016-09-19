package com.example.mrrodriguez.trianadvisor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by manuel on 14/12/2015.
 */
public class Results
{

    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;


    @SerializedName("nombre")
    @Expose
    private String nombre;


    @SerializedName("direccion")
    @Expose
    private String direccion;


    @SerializedName("categoria")
    @Expose
    private String categoria;


    @SerializedName("objectId")
    @Expose
    private String objectId;


    @SerializedName("createdAt")
    @Expose
    private String createdAt;


    @SerializedName("coordenadas")
    @Expose
    private Coordenadas coordenadas;


    @SerializedName("telefono")
    @Expose
    private String telefono;


    @SerializedName("descripcion")
    @Expose
    private String descripcion;


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

    public String getDireccion ()
    {
        return direccion;
    }

    public void setDireccion (String direccion)
    {
        this.direccion = direccion;
    }

    public String getCategoria ()
    {
        return categoria;
    }

    public void setCategoria (String categoria)
    {
        this.categoria = categoria;
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

    public Coordenadas getCoordenadas ()
    {
        return coordenadas;
    }

    public void setCoordenadas (Coordenadas coordenadas)
    {
        this.coordenadas = coordenadas;
    }

    public String getTelefono ()
    {
        return telefono;
    }

    public void setTelefono (String telefono)
    {
        this.telefono = telefono;
    }

    public String getDescripcion ()
    {
        return descripcion;
    }

    public void setDescripcion (String descripcion)
    {
        this.descripcion = descripcion;
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
        return "Class Results [updatedAt = "+updatedAt+", nombre = "+nombre+", direccion = "+direccion+", categoria = "+categoria+", objectId = "+objectId+", createdAt = "+createdAt+", coordenadas = "+coordenadas+", telefono = "+telefono+", descripcion = "+descripcion+", foto = "+foto+"]";
    }
}
