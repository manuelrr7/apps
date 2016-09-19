package com.example.mrrodriguez.trianadvisor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Actualizado {

    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    /**
     * No args constructor for use in serialization
     *
     */
    public Actualizado() {
    }

    /**
     *
     * @param updatedAt
     */
    public Actualizado(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     * @return
     * The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     * The updatedAt
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public String toString() {
        return "Actualizado{" +
                "updatedAt='" + updatedAt + '\'' +
                '}';
    }
}