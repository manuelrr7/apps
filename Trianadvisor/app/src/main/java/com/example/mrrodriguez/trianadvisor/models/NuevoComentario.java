package com.example.mrrodriguez.trianadvisor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class NuevoComentario {

    @SerializedName("sitio")
    @Expose
    private SitioValoraciones sitio;
    @SerializedName("usuario")
    @Expose
    private UsuarioValoracion usuario;
    @SerializedName("comentario")
    @Expose
    private String comentario;

    /**
     * No args constructor for use in serialization
     *
     */
    public NuevoComentario() {
    }

    /**
     *
     * @param sitio
     * @param usuario
     * @param comentario
     */
    public NuevoComentario(SitioValoraciones sitio, UsuarioValoracion usuario, String comentario) {
        this.sitio = sitio;
        this.usuario = usuario;
        this.comentario = comentario;
    }

    /**
     *
     * @return
     * The sitio
     */
    public SitioValoraciones getSitio() {
        return sitio;
    }

    /**
     *
     * @param sitio
     * The sitio
     */
    public void setSitio(SitioValoraciones sitio) {
        this.sitio = sitio;
    }

    /**
     *
     * @return
     * The usuario
     */
    public UsuarioValoracion getUsuario() {
        return usuario;
    }

    /**
     *
     * @param usuario
     * The usuario
     */
    public void setUsuario(UsuarioValoracion usuario) {
        this.usuario = usuario;
    }

    /**
     *
     * @return
     * The comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     *
     * @param comentario
     * The comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}