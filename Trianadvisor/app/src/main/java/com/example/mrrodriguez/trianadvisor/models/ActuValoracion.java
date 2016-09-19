package com.example.mrrodriguez.trianadvisor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActuValoracion {

    @SerializedName("valoracion")
    @Expose
    private float valoracion;

    /**
     * No args constructor for use in serialization
     *
     */
    public ActuValoracion() {
    }

    /**
     *
     * @param valoracion
     */
    public ActuValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    /**
     *
     * @return
     * The valoracion
     */
    public float getValoracion() {
        return valoracion;
    }

    /**
     *
     * @param valoracion
     * The valoracion
     */
    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

}
