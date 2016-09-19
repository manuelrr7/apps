package com.example.mrrodriguez.trianadvisor.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manuel on 18/12/2015.
 */

public class Valoracion {

    @SerializedName("results")
    @Expose
    private List<ResultsValoraciones> results = new ArrayList<ResultsValoraciones>();

    /**
     * No args constructor for use in serialization
     */
    public Valoracion() {
    }

    /**
     * @param results
     */
    public Valoracion(List<ResultsValoraciones> results) {
        this.results = results;
    }

    /**
     * @return The results
     */
    public List<ResultsValoraciones> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<ResultsValoraciones> results) {
        this.results = results;
    }

}