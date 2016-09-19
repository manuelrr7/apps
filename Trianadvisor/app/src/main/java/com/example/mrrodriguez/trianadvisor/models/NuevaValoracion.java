package com.example.mrrodriguez.trianadvisor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class NuevaValoracion {

    @SerializedName("sitio")
    @Expose
    private SitioValoraciones sitio;
    @SerializedName("usuario")
    @Expose
    private UsuarioValoracion usuario;
    @SerializedName("valoracion")
    @Expose
    private float valoracion;

    /**
     * No args constructor for use in serialization
     */
    public NuevaValoracion() {
    }

    /**
     * @param sitio
     * @param valoracion
     * @param usuario
     */
    public NuevaValoracion(SitioValoraciones sitio, UsuarioValoracion usuario, float valoracion) {
        this.sitio = sitio;
        this.usuario = usuario;
        this.valoracion = valoracion;
    }

    /**
     * @return The sitio
     */
    public SitioValoraciones getSitio() {
        return sitio;
    }

    /**
     * @param sitio The sitio
     */
    public void setSitio(SitioValoraciones sitio) {
        this.sitio = sitio;
    }

    /**
     * @return The usuario
     */
    public UsuarioValoracion getUsuario() {
        return usuario;
    }

    /**
     * @param usuario The usuario
     */
    public void setUsuario(UsuarioValoracion usuario) {
        this.usuario = usuario;
    }

    /**
     * @return The valoracion
     */
    public float getValoracion() {
        return valoracion;
    }

    /**
     * @param valoracion The valoracion
     */
    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

}